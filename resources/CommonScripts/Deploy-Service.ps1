Param(
    [string]$UserName,
    [string]$Password,
    [string]$ComputerName,
    [string]$ServiceName,
    [string]$SourcePath,
    [string]$WorkSpace,
    [string]$BuildPath,
    [string]$ConfigFile,
    [string]$BinPath
    
)

$ErrorActionPreference = "Stop"
$secStringPassword = ConvertTo-SecureString $Password -AsPlainText -Force
$credObject = New-Object System.Management.Automation.PSCredential ($UserName, $secStringPassword)
$Session = New-PSSession -Name Session1 -ComputerName $ComputerName -Credential $credObject
Get-ChildItem -Path "$WorkSpace\\$BuildPath" -Exclude "$ConfigFile" | Copy-Item -Destination "$SourcePath" -ToSession $session -Recurse -force
Invoke-Command -Session $Session -ScriptBlock {
    param($ServiceName, $BinPath)

    sc.exe create $ServiceName binpath= "$BinPath" start=auto
    sc.exe start $ServiceName
} -ArgumentList $ServiceName, $BinPath
