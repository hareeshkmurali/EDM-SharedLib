def call() {
    
    writeFile file: 'filecount.ps1', text: "${libraryResource 'CommonScripts/BuildFileCount.ps1'}"
    def powershellScript = "powershell -ExecutionPolicy ByPass -File filecount.ps1"
    def scriptOutput = bat(returnStdout: true, script: powershellScript)
    return scriptOutput
}
