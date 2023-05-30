Param(
    [Parameter(Mandatory=$true)]
    [string]$UserName,
    [Parameter(Mandatory=$true)]
    [string]$Pass,
    [Parameter(Mandatory=$true)]
    [string]$ComputerName,
    [Parameter(Mandatory=$true)]
    [string]$SiteName
)
# $Password = $Password.Trim('"')
Write-Output $Pass
$Pass1 = "Ba>j%6mTKbc"
if ($Pass -eq $Pass1) {
    Write-Host "Strings are equal."
} else {
    Write-Host "Strings are not equal."
}
$secStringPassword = ConvertTo-SecureString $Password -AsPlainText -Force
$credObject = New-Object System.Management.Automation.PSCredential ($UserName, $secStringPassword)

Invoke-Command -ComputerName $ComputerName -Credential $credObject -ScriptBlock {
    param($SiteName)
    
    Import-Module WebAdministration
    $appPool = Get-Item "IIS:\\Sites\\$SiteName" | Select-Object -ExpandProperty applicationPool
    Start-WebAppPool $appPool
    
  

} -ArgumentList $SiteName


