package com.mode.TestNG;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

public class ScreenShot {
	
	
  @Test
  public void f() {
	  
	  File backup_screenshot = new File("/SeleniumScripts/backup_screens");
	  try {
		FileUtils.cleanDirectory(backup_screenshot);
	} catch (IOException e) {
		
		e.printStackTrace();
	}
  }
}
