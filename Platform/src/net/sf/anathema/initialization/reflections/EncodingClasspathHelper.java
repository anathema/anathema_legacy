package net.sf.anathema.initialization.reflections;

import com.google.common.collect.Sets;
import net.sf.anathema.lib.logging.Logger;
import sun.net.www.ParseUtil;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Set;

public class EncodingClasspathHelper {
  private static final Logger logger = Logger.getLogger(EncodingClasspathHelper.class);

  public static Set<URL> forPackage(String name, ClassLoader... classLoaders) {
    Set<URL> result = Sets.newHashSet();
    String resourceName = name.replace(".", "/");
    for (ClassLoader classLoader : classLoaders) {
      try {
        Enumeration<URL> urls = classLoader.getResources(resourceName);
        while (urls.hasMoreElements()) {
          URL url = urls.nextElement();
          URI uri = url.toURI();
          String uriPath = uri.getPath();

          int index = uriPath.lastIndexOf(resourceName);
          if (index != -1) {
            String partialPath = uriPath.substring(0, index);
            URI trimmedUri = new URI(uri.getScheme(), uri.getAuthority(), partialPath, uri.getQuery(), uri.getFragment());
            result.add(trimmedUri.toURL());
          }
        }
      } catch (IOException e) {
        logger.error("Could not resolve URL.", e);
      } catch (URISyntaxException e) {
        logger.error("URL could not be converted to URI.", e);
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
