def call() {
    writeFile file: 'filecount_before.ps1', text: "${libraryResource 'CommonScripts/BuildFileCount_before.ps1'}"
    bat "powershell -ExecutionPolicy ByPass -File filecount_before.ps1"
}
