package net.sf.anathema;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collection;

public class EasyLoader extends URLClassLoader {

  public EasyLoader(File file) throws MalformedURLException {
    super(getURLs(file));
  }

  private static URL[] getURLs(File file) throws MalformedURLException {
    if (file.isDirectory()) {
      return allJarFilesInDirectory(file);
    }
    return singleFile(file);
  }

  private static URL[] allJarFilesInDirectory(File folder) {
    Collection<URL> urls = new ArrayList<URL>();
    for (File file : folder.listFiles()) {
      urls.add(fileToUrl(file));
    }
    return urls.toArray(new URL[urls.size()]);
  }

  private static URL[] singleFile(File file) throws MalformedURLException {
    return new URL[]{fileToUrl(file)};
  }

  private static URL fileToUrl(File input) {
    try {
      return input.toURI().toURL();
    } catch (MalformedURLException e) {
      throw new RuntimeException("Could not load all files.", e);
    } catch (NullPointerException e) {
      throw new RuntimeException("Could not load all files.", e);
    }
  }
}