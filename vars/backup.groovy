def call(String ComputerName, String SiteName, String SourcePath, String DirectoryPath) {
    writeFile file: 'backup.ps1', text: "${libraryResource 'CommonScripts/Backup-and-Restart-IIS.ps1'}"
    bat "powershell -ExecutionPolicy ByPass -File backup.ps1 $ComputerName $SiteName $SourcePath $DirectoryPath"
}
