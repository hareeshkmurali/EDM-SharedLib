$uname = $env:UserName
$CompName = $env:ComputerName
$WSName = $env:SiteName
$secStringPassword = ConvertTo-SecureString $Password -AsPlainText -Force
$credObject = New-Object System.Management.Automation.PSCredential ($uname, $secStringPassword)

Invoke-Command -ComputerName $CompName -Credential $credObject -ScriptBlock {
    param($WSName)
    
    Import-Module WebAdministration
    $appPool = Get-Item "IIS:\\Sites\\$WSName" | Select-Object -ExpandProperty applicationPool
    Start-WebAppPool $appPool
    
  

} -ArgumentList $WSName

