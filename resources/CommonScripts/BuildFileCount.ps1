param (
    [string]$WorkSpace,
    [string]$BuildPath
)

$buildFileCount = (Get-ChildItem -Path "$Workspace\\$BuildPath" -Recurse -File | Measure-Object).Count
Write-Output $buildFileCount