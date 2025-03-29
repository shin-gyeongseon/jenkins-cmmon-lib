podTemplate(
  agentContainer: 'maven',
  agentInjection: true,
  containers: [
    containerTemplate(name: 'kaniko', image: 'gcr.io/kaniko-project/executor:debug', command: '/busybox/cat', ttyEnabled: true)
  ]) {
    node(POD_LABEL) {
        stage('build docker image') {
            container('kaniko') {
                stage('Build a Maven project') {
                    sh 'pwd'
                    sh 'ls'
                }
            }
        }
    }
}