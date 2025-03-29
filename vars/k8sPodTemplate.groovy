#!/usr/bin/env groovy

import org.shin.example.jenkins.K8sPodTemplateBuilder

def call(Map params = [:]) {
    def builder = new K8sPodTemplateBuilder()

    String name = params.get('name', 'default')
    String label = params.get('label', "${name}-${UUID.randomUUID().toString()}")
    String cloud = params.get('cloud', 'kubernetes')

    // Pod 템플릿 생성
    def podYaml = builder.buildPodTemplate(params)

    return [
            cloud: cloud,
            label: label,
            yaml: podYaml
    ]
}
