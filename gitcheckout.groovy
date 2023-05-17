def call() {
    def gitCheckout(String branchName, String credentialsId, String repositoryUrl) {
        checkout([$class: 'GitSCM',
            branches: [[name: branchName]],
            doGenerateSubmoduleConfigurations: false,
            extensions: [],
            submoduleCfg: [],
            userRemoteConfigs: [[credentialsId: credentialsId, url: repositoryUrl]]
        ])
    }

    return this
}
