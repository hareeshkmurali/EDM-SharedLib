Param(
    [string]$UserName,
    [string]$Password,
    [string]$ComputerName,
    [string]$SiteName,
    [string]$WebUrl,
    [string]$Workspace,
    [string]$BuildPath

)


msdeploy -verb:sync -source:recycleApp -dest:recycleApp="$SiteName",computerName="$WebUrl",recycleMode="StopAppPool",authType="basic",userName="$UserName",password="$Password" -allowUntrusted=true
msdeploy -verb=sync -source:contentPath="$Workspace\$BuildPath" -dest:contentPath="$SiteName",computerName="$WebUrl",userName="$UserName",password="$Password",authType="basic" -allowUntrusted=true -enableRule:DoNotDeleteRule -skip:objectName=filePath,absolutePath=".*\\.csv$" -skip:objectName=filePath,absolutePath=".*\\.pdf$" -skip:objectName=filePath,absolutePath=".*\\.xls$" -skip:objectName=filePath,absolutePath=".*\\.xlsx$" -skip:objectName=filePath,absolutePath="$SiteName\\Web\.config$",skipAction=Update 
msdeploy -verb:sync -source:recycleApp -dest:recycleApp="$SiteName",computerName="$WebUrl",recycleMode="StartAppPool",authType="basic",userName="$UserName",password="$Password" -allowUntrusted=true




