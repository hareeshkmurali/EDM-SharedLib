def call(String BuildNumber, String UserName, String Password, String ComputerName, String SiteName, String SourcePath, String DirectoryPath) {
    writeFile file: 'backup.ps1', text: "${libraryResource 'CommonScripts/demo.ps1'}"
    bat "powershell -ExecutionPolicy ByPass -File backup.ps1 $BuildNumber $UserName \"$Password\" $ComputerName $SiteName $SourcePath $DirectoryPath"
}
