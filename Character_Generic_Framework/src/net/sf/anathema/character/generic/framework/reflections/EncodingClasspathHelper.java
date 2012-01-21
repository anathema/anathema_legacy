package net.sf.anathema.character.generic.framework.reflections;

import com.google.common.collect.Sets;
import sun.net.www.ParseUtil;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Set;

public class EncodingClasspathHelper {

  public static Set<URL> forPackage(String name, ClassLoader... classLoaders) {
    Set<URL> result = Sets.newHashSet();
    String resourceName = name.replace(".", "/");
    String encodedResourceName = createUrlCompareString(resourceName);
    for (ClassLoader classLoader : classLoaders) {
      try {
        Enumeration<URL> urls = classLoader.getResources(resourceName);
        while (urls.hasMoreElements()) {
          URL url = urls.nextElement();
          String urlCompareString = createUrlCompareString(url.toExternalForm());
          int index = urlCompareString.lastIndexOf(encodedResourceName);
          if (index != -1) {
            String partialUrl = urlCompareString.substring(0, index);
            if (partialUrl.contains("!")) {
              String jarUrl = deconstructUrlCompareString(partialUrl);
              result.add(new URL(jarUrl));
            }
            else{
              result.add(new URL(partialUrl));
            }
          }
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return result;
  }

  private static String deconstructUrlCompareString(String partialUrl) {
    return ParseUtil.decode(partialUrl);
  }

  private static String createUrlCompareString(String partialUrlsString) {
    return ParseUtil.encodePath(partialUrlsString);
  }
}