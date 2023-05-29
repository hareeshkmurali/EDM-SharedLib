def call(def psw) {
    
    writeFile file: 'test1.ps1', text: "${libraryResource 'CommonScripts/test.ps1'}"
    bat "powershell -ExecutionPolicy ByPass -File test1.ps1 $psw"
}
