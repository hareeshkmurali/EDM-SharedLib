Param(
    [string]$UserName,
    [string]$Password,
    [string]$ComputerName,
    [string]$ServiceName,
    [string]$BinPath
)

$secStringPassword = ConvertTo-SecureString $Password -AsPlainText -Force
$credObject = New-Object System.Management.Automation.PSCredential ($UserName, $secStringPassword)

Invoke-Command -ComputerName $ComputerName -Credential $credObject -ScriptBlock {
    param($ServiceName, $BinPath)

    sc.exe stop $ServiceName
    sc.exe delete $ServiceName
    Start-Sleep -Seconds 25
    sc.exe create $ServiceName binpath= "$BinPath" start=auto
    sc.exe start $ServiceName

} -ArgumentList $ServiceName, $BinPath


