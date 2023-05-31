$ErrorActionPreference = "Stop"
#$BuildNumber = $env:BUILD_NUMBER
$Password = $env:IISPASWD
$uname = $env:UserName
$CompName = $env:ComputerName
$WSName = $env:SiteName
$SrcName = $env:SourcePath
$DirPath = $env:DirectoryPath
$secStringPassword = ConvertTo-SecureString $Password -AsPlainText -Force
$credObject = New-Object System.Management.Automation.PSCredential ($uname, $secStringPassword)

Invoke-Command -ComputerName $CompName -Credential $credObject -ScriptBlock {
    param($env:BUILD_NUMBER, $WSName, $SrcName, $DirPath)

    $Files = Get-ChildItem -Path $DirPath -File
    $FilesSorted = $Files | Sort-Object -Property CreationTime -Descending
    $FilesToKeep = $FilesSorted | Select-Object -First 5
    $FilesSorted | Where-Object { $_.FullName -notin $FilesToKeep.FullName } | Remove-Item -Force
    
    Import-Module WebAdministration
    $appPool = Get-Item "IIS:\\Sites\\$WSName" | Select-Object -ExpandProperty applicationPool
    Restart-WebAppPool $appPool

    $source="$SrcName"
    $dest="$DirPath\\Archive-$($env.BUILD_NUMBER).zip"
    Add-Type -assembly "system.io.compression.filesystem"
    [io.compression.zipfile]::CreateFromDirectory($source, $dest)
} -ArgumentList $env:BUILD_NUMBER, $WSName, $SrcName, $DirPath


