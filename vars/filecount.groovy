def call() {
    writeFile file: 'filecount.ps1', text: "${libraryResource 'CommonScripts/BuildFileCount.ps1'}"
    bat "powershell -ExecutionPolicy ByPass -File filecount.ps1"
}
