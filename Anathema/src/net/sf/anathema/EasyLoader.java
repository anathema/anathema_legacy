package net.sf.anathema;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class EasyLoader extends URLClassLoader {

  public EasyLoader(Path path, URL... additionalURLs) throws MalformedURLException {
    super(concat(getURLs(path), additionalURLs));
  }

  public EasyLoader(URL... URLs) {
    super(URLs);
  }

  private static URL[] concat(URL[] urls1, URL[] urls2) {
    URL[] result = Arrays.copyOf(urls1, urls1.length + urls2.length);
    System.arraycopy(urls2, 0, result, urls1.length, urls2.length);
    return result;
  }

  private static URL[] getURLs(Path path) throws MalformedURLException {
    if (Files.isDirectory(path)) {
      return allJarFilesInDirectory(path);
    }
    return singleFile(path);
  }

  private static URL[] allJarFilesInDirectory(Path folder) {
    Collection<URL> urls = new ArrayList<>();
    try (DirectoryStream<Path> stream = Files.newDirectoryStream(folder, new FilesOnly())) {
      for (Path file : stream) {
        urls.add(fileToUrl(file));
      }
      return urls.toArray(new URL[urls.size()]);
    } catch (IOException | DirectoryIteratorException x) {
      throw new RuntimeException("Could not read library folder.", x);
    }
  }

  private static URL[] singleFile(Path file) {
    return new URL[]{fileToUrl(file)};
  }

  private static URL fileToUrl(Path input) {
    try {
      return input.toUri().toURL();
    } catch (MalformedURLException | NullPointerException e) {
      throw new RuntimeException("Could not load all files.", e);
    }
  }
}