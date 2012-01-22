package net.sf.anathema;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collection;

import static com.google.common.collect.Collections2.transform;
import static com.google.common.collect.Lists.newArrayList;

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
    ArrayList<File> files = newArrayList(folder.listFiles());
    Collection<URL> urls = transform(files, new ToURL());
    return urls.toArray(new URL[urls.size()]);
  }

  private static URL[] singleFile(File file) throws MalformedURLException {
    return new URL[]{new ToURL().apply(file)};
  }
}