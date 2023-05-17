def call(Map scriptParams) {
    def workspace = scriptParams.workspace
    def buildPath = scriptParams.buildPath

    def powerShellScript = """
        Param(
            [string]$$workspace,
            [string]$$buildPath
        )

        \$buildFileCount = (Get-ChildItem -Path "${Workspace}\\${BuildPath}" -Recurse -File | Measure-Object).Count
        Write-Output ${buildFileCount}

        def result = powershell(returnStdout: true, script: powerShellScript)