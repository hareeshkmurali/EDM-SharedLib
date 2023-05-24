// myScript.groovy

def call(Map scriptParams) {
    def BuildNumber = scriptParams.BuildNumber
    def UserName = scriptParams.UserName
    def Password = scriptParams.Password
    def ComputerName = scriptParams.ComputerName
    def SiteName = scriptParams.SiteName
    def SourcePath = scriptParams.SourcePath
    def DirectoryPath = scriptParams.DirectoryPath

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
        \$secStringPassword = ConvertTo-SecureString ${Password} -AsPlainText -Force
        \$credObject = New-Object System.Management.Automation.PSCredential (${UserName}, ${secStringPassword})

        Invoke-Command -ComputerName ${ComputerName} -Credential ${credObject} -ScriptBlock {
            param(\$BuildNumber, \$SiteName, \$SourcePath, \$DirectoryPath)

            \$Files = Get-ChildItem -Path ${DirectoryPath} -File
            \$FilesSorted = ${Files} | Sort-Object -Property CreationTime -Descending
            \$FilesToKeep = ${FilesSorted} | Select-Object -First 5
            \$FilesSorted | Where-Object { \$.FullName -notin \$FilesToKeep.FullName } | Remove-Item -Force

            Import-Module WebAdministration
            \$appPool = Get-Item "IIS:\\Sites\\\$SiteName" | Select-Object -ExpandProperty applicationPool
            Restart-WebAppPool \$appPool

            \$source="\$SourcePath"
            \$dest="\$DirectoryPath\\Archive-\$BuildNumber.zip"
            Add-Type -assembly "system.io.compression.filesystem"
            [io.compression.zipfile]::CreateFromDirectory(\$source, \$dest)
        } -ArgumentList \$BuildNumber, \$SiteName, \$SourcePath, \$DirectoryPath
    """

    // Execute the PowerShell script
    def result = powershell(returnStdout: true, script: powerShellScript)

}
