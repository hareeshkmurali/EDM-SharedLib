def runPowerShellScript(String UserName, String Password, String ComputerName, String SiteName) {
    def scriptPath = libraryResource('CommonScripts/Restore-AppPool.ps1')

    bat "powershell.exe -File ${scriptPath} -UserName '${UserName}' -Password '${Password}' -ComputerName '${ComputerName}' -SiteName '${SiteName}'"
}
