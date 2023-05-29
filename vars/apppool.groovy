def call(String UserName, String Password, String ComputerName, String SiteName) {
    writeFile file: 'apppool.ps1', text: "${libraryResource 'CommonScripts/Restore-AppPool.ps1'}"
    env.Password = Password
    env.UserName = UserName
    env.ComputerName = ComputerName
    env.SiteName = SiteName
    bat 'powershell -ExecutionPolicy ByPass -File apppool.ps1 $UserName $Password $ComputerName $SiteName'
}
