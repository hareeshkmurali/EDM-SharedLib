def call(String userName, String password, String computerName, String siteName, String webUrl, String workspace, String buildPath) {
  def msdeployCommand = "msdeploy -verb:sync -source:recycleApp -dest:recycleApp=\"$siteName\",computerName=\"$webUrl\",recycleMode=\"StopAppPool\",authType=\"basic\",userName=\"$userName\",password=\"$password\" -allowUntrusted=true"
  bat msdeployCommand
  
  def msdeployCommand2 = "msdeploy -verb=sync -source:contentPath=\"$workspace\\$buildPath\" -dest:contentPath=\"$siteName\",computerName=\"$webUrl\",userName=\"$userName\",password=\"$password\",authType=\"basic\" -allowUntrusted=true -enableRule:DoNotDeleteRule -skip:objectName=filePath,absolutePath=\".*\\.csv$\" -skip:objectName=filePath,absolutePath=\".*\\.pdf$\" -skip:objectName=filePath,absolutePath=\".*\\.xls$\" -skip:objectName=filePath,absolutePath=\".*\\.xlsx$\" -skip:objectName=filePath,absolutePath=\"$siteName\\\\Web\\.config$\",skipAction=Update"
  bat msdeployCommand2
  
  def msdeployCommand3 = "msdeploy -verb:sync -source:recycleApp -dest:recycleApp=\"$siteName\",computerName=\"$webUrl\",recycleMode=\"StartAppPool\",authType=\"basic\",userName=\"$userName\",password=\"$password\" -allowUntrusted=true"
  bat msdeployCommand3
}
