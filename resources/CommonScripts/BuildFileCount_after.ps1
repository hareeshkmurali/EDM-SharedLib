$workspace = $env:WorkSpace
$buildpath = $env:BuildPath
$buildFileCount = (Get-ChildItem -Path "$workspace\\$buildpath" -Recurse -File | Measure-Object).Count
Write-Output "Build File Count after removal of PDF, PDB, CSV, XLS, XLSX Files : $buildFileCount"
