package net.sf.anathema.character.generic.framework.reflections;

import com.google.common.collect.Sets;
import sun.net.www.ParseUtil;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Set;

public class EncodingClasspathHelper {

  public static Set<URL> forPackage(String name, ClassLoader... classLoaders) {
    final Set<URL> result = Sets.newHashSet();

    final String resourceName = name.replace(".", "/");
    String encodedResourceName = createUrlCompareString(resourceName);

    for (ClassLoader classLoader : classLoaders) {
      try {
        final Enumeration<URL> urls = classLoader.getResources(resourceName);
        while (urls.hasMoreElements()) {
          final URL url = urls.nextElement();
          String urlCompareString = createUrlCompareString(url.toExternalForm());
          int index = urlCompareString.lastIndexOf(encodedResourceName);
          if (index != -1) {
            String partialUrl = urlCompareString.substring(0, index);
            result.add(new URL(ParseUtil.decode(partialUrl)));
          }
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return result;
  }

  public static Set<URL> forPackagesInIde(String name, ClassLoader... classLoaders) {
    final Set<URL> result = Sets.newHashSet();
    final String resourceName = name.replace(".", "/");
    String encodedResourceName = createUrlCompareString(resourceName);
    for (ClassLoader classLoader : classLoaders) {
      try {
        final Enumeration<URL> urls = classLoader.getResources(resourceName);
        while (urls.hasMoreElements()) {
          final URL url = urls.nextElement();
          String urlCompareString = createUrlCompareString(url.toExternalForm());
          int index = urlCompareString.lastIndexOf(encodedResourceName);
          if (index != -1) {
            result.add(new URL(urlCompareString.substring(0, index)));
          }
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return result;
  }

  private static String createUrlCompareString(String partialUrlsString) {
    return ParseUtil.encodePath(partialUrlsString);
  }
}
