def call() {
    writeFile file: 'apppool.ps1', text: "${libraryResource 'CommonScripts/Restore-AppPool.ps1'}"
    bat "powershell -ExecutionPolicy ByPass -File apppool.ps1"
}
