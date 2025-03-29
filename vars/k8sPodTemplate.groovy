#!/usr/bin/env groovy
import org.shin.example.jenkins.K8sPodTemplateBuilder

def call(Map params = [:]) {
    // 기본 템플릿은 항상 포함
    def templates = []
    templates.add(libraryResource('podTemplates/base.yaml'))

    // 1. Map 형태로 파라미터가 전달된 경우
    if (params instanceof Map) {
        // 기존 방식대로 키 존재 여부로 템플릿 추가
        if (params.containsKey('kaniko')) {
            templates.add(libraryResource('podTemplates/kaniko.yaml'))
        }
        if (params.containsKey('kubectl')) {
            templates.add(libraryResource('podTemplates/kubectl.yaml'))
        }
        if (params.containsKey('argocd')) {
            templates.add(libraryResource('podTemplates/argocd.yaml'))
        }
    }

    def builder = new K8sPodTemplateBuilder()
    def final_template = builder.merge(templates)

    return final_template
}
