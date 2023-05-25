$param1=$args[0]
echo $param1
$userName='visionaire\\mohamed.saif'
$secStringPassword = ConvertTo-SecureString "$param1" -AsPlainText -Force
$credObject = New-Object System.Management.Automation.PSCredential ($userName, $secStringPassword)
Invoke-Command -ComputerName 10.2.3.23 -Credential $credObject -ScriptBlock {
           (Get-ChildItem -Path D:\\EDMS.Service\\ -Recurse -File | Measure-Object).Count
}