def call(Map scriptParams) {
    def UserName = scriptParams.UserName
    def Password = scriptParams.Password
    def ComputerName = scriptParams.ComputerName
    def SourcePath = scriptParams.Sourcepath

    def powerShellScript = """
        Param(
            [string]$UserName,
            [string]$Password,
            [string]$ComputerName,
            [string]$SourcePath
        )

        \$secStringPassword = ConvertTo-SecureString ${Password} -AsPlainText -Force
        \$credObject = New-Object System.Management.Automation.PSCredential (${UserName}, ${secStringPassword})

        Invoke-Command -ComputerName ${ComputerName} -Credential ${credObject} -ScriptBlock {
        param(${SourcePath})
        (Get-ChildItem -Path ${SourcePath} -Recurse -File | Measure-Object).Count
        } -ArgumentList ${SourcePath}

        def result = powershell(returnStdout: true, script: powerShellScript)