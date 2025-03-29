#!/usr/bin/env groovy
import org.shin.example.jenkins.K8sPodTemplateBuilder

def call(Map params = [:]) {
    // 기본 템플릿은 항상 포함
    def resource = libraryResource('podTemplates/base.yaml')

    // 1. Map 형태로 파라미터가 전달된 경우
    if (params instanceof Map) {
        // 기존 방식대로 키 존재 여부로 템플릿 추가
        if (params.containsKey('kaniko')) {
            resource = resource + '\n' + libraryResource('podTemplates/kaniko.yaml')
        }
        if (params.containsKey('kubectl')) {
            resource = resource + '\n' + libraryResource('podTemplates/kubectl.yaml')
        }
        if (params.containsKey('argocd')) {
            resource = resource + '\n' + libraryResource('podTemplates/argocd.yaml')
        }
    }

    return resource
}
