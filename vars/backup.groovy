def call(String BuildNumber, String UserName, String Password, String ComputerName, String SiteName, String SourcePath, String DirectoryPath) {
    println Password
    writeFile file: 'backup.ps1', text: "${libraryResource 'CommonScripts/Backup-and-Restart-IIS.ps1'}"
    bat "powershell -ExecutionPolicy ByPass -File backup.ps1 $BuildNumber $UserName $temppass $ComputerName $SiteName $SourcePath $DirectoryPath"
}
