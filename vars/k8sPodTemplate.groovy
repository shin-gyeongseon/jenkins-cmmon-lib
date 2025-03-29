#!/usr/bin/env groovy
import org.shin.example.jenkins.K8sPodTemplateBuilder

def call(Map params = [:]) {
    // 기본 템플릿은 항상 포함
    def resource = libraryResource('podTemplates/base.yaml')
    resource += libraryResource('podTemplates/kaniko.yaml')

    if (params.containsKey('kaniko')) {
        resource += libraryResource('podTemplates/kaniko.yaml')
    }
    if (params.containsKey('kubectl')) {
        resource += libraryResource('podTemplates/kubectl.yaml')
    }
    if (params.containsKey('argocd')) {
        resource += libraryResource('podTemplates/argocd.yaml')
    }

    return resource
}
