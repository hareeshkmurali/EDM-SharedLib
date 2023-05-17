def call() {
    def buildSolution(String vsToolName, String workspacePath, String solutionName, String configuration, String platform, boolean restorePackagesConfig) {
        def command = "\"${tool vsToolName}\" /t:Clean;restore ${workspacePath}\\${solutionName} /p:Configuration=${configuration} /p:Platform=\"${platform}\" /p:RestorePackagesConfig=${restorePackagesConfig}"

        bat(command)
    }

    return this
}