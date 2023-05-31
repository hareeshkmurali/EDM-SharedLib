def call() {
    writeFile file: 'filecount_after.ps1', text: "${libraryResource 'CommonScripts/BuildFileCount_after.ps1'}"
    bat "powershell -ExecutionPolicy ByPass -File filecount_after.ps1"
}
