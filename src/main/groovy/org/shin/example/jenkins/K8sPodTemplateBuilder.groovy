package org.shin.example.jenkins

import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.LoaderOptions
import org.yaml.snakeyaml.constructor.SafeConstructor

class K8sPodTemplateBuilder {

    private final Yaml yamlParser

    K8sPodTemplateBuilder() {
        def options = new LoaderOptions()
        this.yamlParser = new Yaml(new SafeConstructor(options))
    }

    String temporaryString = "temporary String"

    /**
     * 병합된 Pod 템플릿 YAML 생성
     * @param params - Map 형태의 파라미터 (예: 컨테이너 이름 및 옵션)
     * @return 병합된 YAML 문자열
     */
    String buildPodTemplate(Map params) {
        List<String> templates = []

        templates.add(loadResource('podTemplates/base.yaml'))

        if (params.containsKey('kaniko')) {
            templates.add(loadResource('podTemplates/kaniko.yaml'))
        }

        if (params.containsKey('kubectl')) {
            templates.add(loadResource('podtemplates/kubectl.yaml'))
        }
        if (params.containsKey('argocd')) {
            templates.add(loadResource('podtemplates/argocd.yaml'))
        }

        // YAML 병합
        Map mergedYaml = mergeYamlTemplates(templates)
        return yamlParser.dump(mergedYaml)
    }

    /**
     * 리소스 파일 로드
     * @param resourcePath - 리소스 경로
     * @return YAML 문자열
     */
    private String loadResource(String resourcePath) {
        return libraryResource(resourcePath)
    }

    /**
     * 여러 YAML 템플릿 병합
     * @param yamlStrings - YAML 문자열 리스트
     * @return 병합된 YAML Map
     */
    private Map mergeYamlTemplates(List<String> yamlStrings) {
        Map mergedResult = [:]
        yamlStrings.each { yamlString ->
            Map parsedYaml = yamlParser.load(yamlString)
            mergeStructures(mergedResult, parsedYaml)
        }
        return mergedResult
    }

    /**
     * 두 개의 YAML 구조를 병합하는 재귀 메서드
     */
    private void mergeStructures(Map target, Map source) {
        source.each { key, value ->
            if (value instanceof Map && target[key] instanceof Map) {
                mergeStructures(target[key] as Map, value)
            } else if (value instanceof List && target[key] instanceof List) {
                target[key].addAll(value)
            } else {
                target[key] = value
            }
        }
    }
}
