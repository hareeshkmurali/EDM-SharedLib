// myScript.groovy

def call(Map scriptParams) {
    def buildNumber = scriptParams.BuildNumber
    def userName = scriptParams.UserName
    def password = scriptParams.Password
    def computerName = scriptParams.ComputerName
    def siteName = scriptParams.SiteName
    def sourcePath = scriptParams.SourcePath
    def directoryPath = scriptParams.DirectoryPath

    // PowerShell script content
    def powerShellScript = """
        Param(
            [string]$$BuildNumber,
            [string]$$UserName,
            [string]$$Password,
            [string]$$ComputerName,
            [string]$$SiteName,
            [string]$$SourcePath,
            [string]$$DirectoryPath
        )

        \$ErrorActionPreference = "Stop"
        \$secStringPassword = ConvertTo-SecureString \$Password -AsPlainText -Force
        \$credObject = New-Object System.Management.Automation.PSCredential (\$UserName, \$secStringPassword)

        Invoke-Command -ComputerName \$ComputerName -Credential \$credObject -ScriptBlock {
            param($$BuildNumber, $$SiteName, $$SourcePath, $$DirectoryPath)

            \$Files = Get-ChildItem -Path $$DirectoryPath -File
            \$FilesSorted = \$Files | Sort-Object -Property CreationTime -Descending
            \$FilesToKeep = \$FilesSorted | Select-Object -First 5
            \$FilesSorted | Where-Object { $$.FullName -notin \$FilesToKeep.FullName } | Remove-Item -Force

            Import-Module WebAdministration
            \$appPool = Get-Item "IIS:\\Sites\\$$SiteName" | Select-Object -ExpandProperty applicationPool
            Restart-WebAppPool \$appPool

            \$source="$$SourcePath"
            \$dest="$$DirectoryPath\\Archive-$$BuildNumber.zip"
            Add-Type -assembly "system.io.compression.filesystem"
            [io.compression.zipfile]::CreateFromDirectory(\$source, \$dest)
        } -ArgumentList $$BuildNumber, $$SiteName, $$SourcePath, $$DirectoryPath
    """

    // Execute the PowerShell script
    def result = powershell(returnStdout: true, script: powerShellScript)

    // Handle the script execution result if needed
    // ...
}
