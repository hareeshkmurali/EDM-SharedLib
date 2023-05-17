def call() {
    def buildProject(String vsToolName, String workspacePath, String projectPath, String configuration, String platform) {
        def command = "\"${tool vsToolName}\" ${workspacePath}\\${projectPath} /p:Configuration=${configuration} /p:Platform=${platform} /p:DeployOnBuild=true /p:DeployDefaultTarget=WebPublish /p:WebPublishMethod=FileSystem /p:DeleteExistingFiles=True /p:publishUrl=./deploy/EDMSSupport"

        bat(command)
    }

    return this
}