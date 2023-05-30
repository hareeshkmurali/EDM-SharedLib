def call() {
    writeFile file: 'deploy.ps1', text: "${libraryResource 'CommonScripts/Deploy-to-IIS.ps1'}"
    bat "powershell -ExecutionPolicy ByPass -File deploy.ps1"
}