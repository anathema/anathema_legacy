package net.sf.anathema;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;

public class PluginRepositoryCollector {
  private final StringBuilder builder = new StringBuilder();

  public String getPluginRepositories() throws IOException {
    addLaunchDirectory();
    addPluginsFolder();
    addClassPath();
    return allLocations();
  }

  private void addLaunchDirectory() {
    addResource(getFilePath(".")); //$NON-NLS-1$
  }

  private void addPluginsFolder() {
    addResource(getFilePath("./plugins"));
  }
  

  private void addClassPath() throws IOException {
    Enumeration<URL> systemResources = ClassLoader.getSystemResources("."); //$NON-NLS-1$
    while (systemResources.hasMoreElements()) {
    	addResource(getNativePath(systemResources.nextElement()));
    }
  }
  
  private String getNativePath(URL url) {
	  try {
		  return url.toURI().getPath();
	  } catch (URISyntaxException e) {
		  return url.getPath();
	  }
  }

  private String allLocations() {
    return builder.toString();
  }

  private void addResource(String resource) {
    addSeparatorIfNecessary();
    if (resource.equals("/")) {
      return;
    }
    builder.append(resource);
  }

  private void addSeparatorIfNecessary() {
    if (!builder.toString().endsWith(",")) {
      builder.append(",");
    }
  }

  private String getFilePath(String string) {
    return new File(string).toURI().getPath();
  }
}