$workspace = $env:WorkSpace
$buildpath = $env:BuildPath
$buildFileCount = (Get-ChildItem -Path "$workspace\\$buildpath" -Recurse -File | Measure-Object).Count
Write-Output $buildFileCount