def call(def psw) {
    
    writeFile file: 'test1.ps1', text: "${libraryResource 'CommonScripts/test.ps1'}"
    println "---------"
    env.psw1 = ${psw}
    println "---------"
    bat "powershell -ExecutionPolicy ByPass -File test1.ps1 $psw1"
}
