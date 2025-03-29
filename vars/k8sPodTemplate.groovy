#!/usr/bin/env groovy

def call(Map params = [:]) {
    def resource = libraryResource('podTemplates/base.yaml')
    return resource
}
