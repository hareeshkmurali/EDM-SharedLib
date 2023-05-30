$Password = $env:IISPASWD
$uname = $env:UserName
$CompName = $env:ComputerName
$WSName = $env:SiteName
$weburl = $env:WebUrl
$workspace = $env:WorkSpace
$buildpath = $env:BuildPath

msdeploy -verb:sync -source:recycleApp -dest:recycleApp="$WSName",computerName="$weburl",recycleMode="StopAppPool",authType="basic",userName="$uname",password="$Password" -allowUntrusted=true
msdeploy -verb=sync -source:contentPath="$workspace\$buildpath" -dest:contentPath="$WSName",computerName="$weburl",userName="$uname",password="$Password",authType="basic" -allowUntrusted=true -enableRule:DoNotDeleteRule -skip:objectName=filePath,absolutePath=".*\\.csv$" -skip:objectName=filePath,absolutePath=".*\\.pdf$" -skip:objectName=filePath,absolutePath=".*\\.xls$" -skip:objectName=filePath,absolutePath=".*\\.xlsx$" -skip:objectName=filePath,absolutePath="$WSName\\Web\.config$",skipAction=Update 
msdeploy -verb:sync -source:recycleApp -dest:recycleApp="$WSName",computerName="$weburl",recycleMode="StartAppPool",authType="basic",userName="$uname",password="$Password" -allowUntrusted=true




