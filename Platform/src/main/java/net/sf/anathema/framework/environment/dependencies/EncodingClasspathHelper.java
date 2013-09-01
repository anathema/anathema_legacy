package net.sf.anathema.framework.environment.dependencies;

import com.google.common.collect.Sets;
import net.sf.anathema.lib.logging.Logger;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Set;

public class EncodingClasspathHelper {
  private static final Logger logger = Logger.getLogger(EncodingClasspathHelper.class);

  private static URL removeTrailingPath(URL url, String trailingPath) throws URISyntaxException, MalformedURLException {
    URI uri = url.toURI();
    String specificPart = uri.getSchemeSpecificPart();
    if (specificPart == null) {
      return null;
    }
    int index = specificPart.lastIndexOf(trailingPath);
    if (index < 0) {
      return null;
    }
    String partialPart = specificPart.substring(0, index);
    URI trimmedUri = new URI(uri.getScheme(), partialPart, uri.getFragment());
    return trimmedUri.toURL();
  }

  public static Set<URL> forPackage(String name, ClassLoader... classLoaders) {
    Set<URL> result = Sets.newHashSet();
    String resourceName = name.replace(".", "/");
    for (ClassLoader classLoader : classLoaders) {
      try {
        Enumeration<URL> urls = classLoader.getResources(resourceName);
        while (urls.hasMoreElements()) {
          URL url = urls.nextElement();
          URL trimmedUrl = removeTrailingPath(url, resourceName);
          if (trimmedUrl != null) {
            result.add(trimmedUrl);
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
}
