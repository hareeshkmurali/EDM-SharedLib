def runPowerShellScript(String BuildNumber, String UserName, String Password, String ComputerName, String SiteName, String SourcePath, String DirectoryPath) {
    def scriptPath = libraryResource('CommonScripts/Backup-and-Restart-IIS.ps1')

    bat "powershell.exe -File ${scriptPath} -BuildNumber '${BuildNumber}' -UserName '${UserName}' -Password '${Password}' -ComputerName '${ComputerName}' -SiteName '${SiteName}' -SourcePath '${SourcePath}' -DirectoryPath '${DirectoryPath}'"
}
