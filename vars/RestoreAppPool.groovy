def call(Map scriptParams) {
    def userName = scriptParams.UserName
    def password = scriptParams.Password
    def computerName = scriptParams.ComputerName
    def siteName = scriptParams.SiteName

    // PowerShell script content
    def powerShellScript = """
        Param(
            [string]$$UserName,
            [string]$$Password,
            [string]$$ComputerName,
            [string]$$SiteName
        )
    
    \$secStringPassword = ConvertTo-SecureString ${Password} -AsPlainText -Force
    \$credObject = New-Object System.Management.Automation.PSCredential (${UserName}, ${secStringPassword})
    
    Invoke-Command -ComputerName ${ComputerName} -Credential $credObject -ScriptBlock {
        param($SiteName)
        
        Import-Module WebAdministration
        \$appPool = Get-Item "IIS:\\Sites\\$SiteName" | Select-Object -ExpandProperty applicationPool
        Start-WebAppPool $appPool
    } -ArgumentList $SiteName
    """

    def result = powershell(returnStdout: true, script: powerShellScript)
}
