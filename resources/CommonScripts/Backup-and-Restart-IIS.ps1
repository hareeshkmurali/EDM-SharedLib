Param(
    [Parameter(Mandatory=$true)]
    [string]$ComputerName,
    [Parameter(Mandatory=$true)]
    [string]$SiteName,
    [Parameter(Mandatory=$true)]
    [string]$SourcePath,
    [Parameter(Mandatory=$true)]
    [string]$DirectoryPath
)

$ErrorActionPreference = "Stop"
$BuildNumber = $env:BUILD_NUMBER
$Password = $env:IISPASWD
$uname = $env:UserName
$secStringPassword = ConvertTo-SecureString ${env:IISPASWD} -AsPlainText -Force
$credObject = New-Object System.Management.Automation.PSCredential (${env:UserName}, $secStringPassword)

Invoke-Command -ComputerName $ComputerName -Credential $credObject -ScriptBlock {
    param($BuildNumber, $SiteName, $SourcePath, $DirectoryPath)

    $Files = Get-ChildItem -Path $DirectoryPath -File
    $FilesSorted = $Files | Sort-Object -Property CreationTime -Descending
    $FilesToKeep = $FilesSorted | Select-Object -First 5
    $FilesSorted | Where-Object { $_.FullName -notin $FilesToKeep.FullName } | Remove-Item -Force
    
    Import-Module WebAdministration
    $appPool = Get-Item "IIS:\\Sites\\$SiteName" | Select-Object -ExpandProperty applicationPool
    Restart-WebAppPool $appPool

    $source="$SourcePath"
    $dest="$DirectoryPath\\Archive-$env:BUILD_NUMBER.zip"
    Add-Type -assembly "system.io.compression.filesystem"
    [io.compression.zipfile]::CreateFromDirectory($source, $dest)
} -ArgumentList $BuildNumber, $SiteName, $SourcePath, $DirectoryPath


