def call(String UserName, def Password, String ComputerName, String SiteName) {
    writeFile file: 'apppool.ps1', text: "${libraryResource 'CommonScripts/Restore-AppPool.ps1'}"
    def Pass = "Ba>j%6mTKbc"
    bat "powershell -ExecutionPolicy ByPass -File apppool.ps1 $UserName \"$Pass\" $ComputerName $SiteName"
}
