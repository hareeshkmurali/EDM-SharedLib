def call() {
    writeFile file: 'webfilecount.ps1', text: "${libraryResource 'CommonScripts/WebFileCount.ps1'}"
    bat "powershell -ExecutionPolicy ByPass -File webfilecount.ps1"
}
