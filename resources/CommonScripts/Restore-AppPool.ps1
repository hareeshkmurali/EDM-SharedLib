Param(
    [string]$UserName,
    [string]$Password,
    [string]$ComputerName,
    [string]$SiteName
)

$secStringPassword = ConvertTo-SecureString $Password -AsPlainText -Force
$credObject = New-Object System.Management.Automation.PSCredential ($UserName, $secStringPassword)

Invoke-Command -ComputerName $ComputerName -Credential $credObject -ScriptBlock {
    param($SiteName)
    
    Import-Module WebAdministration
    $appPool = Get-Item "IIS:\\Sites\\$SiteName" | Select-Object -ExpandProperty applicationPool
    Start-WebAppPool $appPool
    
  

} -ArgumentList $SiteName


