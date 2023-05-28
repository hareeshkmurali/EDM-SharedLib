def call(String UserName, String Password, String ComputerName, String SiteName) {
    writeFile file: 'apppool.ps1', text: "${libraryResource 'CommonScript/Restore-AppPool.ps1'}"
    bat "powershell -ExecutionPolicy ByPass -File apppool.ps1 $UserName $Password $ComputerName $SiteName"