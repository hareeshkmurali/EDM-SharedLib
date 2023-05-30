$uname = $env:UserName
$Password = $env:IISPASWD
$CompName = $env:ComputerName
$SrcName = $env:SourcePath

$secStringPassword = ConvertTo-SecureString $Password -AsPlainText -Force
$credObject = New-Object System.Management.Automation.PSCredential ($uname, $secStringPassword)

Invoke-Command -ComputerName $CompName -Credential $credObject -ScriptBlock {
  param($SrcName)
  (Get-ChildItem -Path $SrcName -Recurse -File | Measure-Object).Count
 } -ArgumentList $SrcName