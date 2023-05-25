Param(
    [string]$BuildNumber,
    [string]$UserName,
    [string]$Password,
    [string]$ComputerName,
    [string]$ServiceName,
    [string]$SourcePath,
    [string]$DirectoryPath,
    [string]$BinPath
)

$secStringPassword = ConvertTo-SecureString $Password -AsPlainText -Force
$credObject = New-Object System.Management.Automation.PSCredential ($UserName, $secStringPassword)

Invoke-Command -ComputerName $ComputerName -Credential $credObject -ScriptBlock {
    param($BuildNumber, $ServiceName, $SourcePath, $DirectoryPath, $BinPath)

    $source="$DirectoryPath\\Archive-$($BuildNumber-1).zip"
    $dest="$DirectoryPath\\$ServiceName"
    Add-Type -assembly "system.io.compression.filesystem"
    [System.IO.Compression.ZipFile]::ExtractToDirectory($source, $dest)

    Copy-Item "$DirectoryPath\\$ServiceName\\*" -Destination "$SourcePath" -Recurse -force
    
    Remove-Item "$DirectoryPath\\$ServiceName" -Recurse -Force

    sc.exe stop $ServiceName
    sc.exe delete $ServiceName
    Start-Sleep -Seconds 25
    sc.exe create $ServiceName binpath= "$BinPath" start=auto
    sc.exe start $ServiceName
} -ArgumentList $BuildNumber, $ServiceName, $SourcePath, $DirectoryPath, $BinPath
