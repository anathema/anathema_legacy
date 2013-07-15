package net.sf.anathema.framework.resources;

import net.sf.anathema.initialization.reflections.ExternalResourceFile;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.resources.FileStringProvider;
import net.sf.anathema.lib.resources.InternalResourceFile;
import net.sf.anathema.lib.resources.NullStringProvider;
import net.sf.anathema.lib.resources.ResourceFile;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.resources.StringProvider;
import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.util.Locale;

public class StringProviderFactory {
  private static final Logger logger = Logger.getLogger(StringProviderFactory.class);
  private final Locale locale;

  public StringProviderFactory(Locale locale) {
    this.locale = locale;
  }

  public Resources create(ResourceFile resource) {
    if (resource instanceof InternalResourceFile) {
      return createInternalProvider(resource);
    }
    if (resource instanceof ExternalResourceFile) {
      return createExternalProvider(resource);
    }
    throw new RuntimeException("Unknown resource type: " + resource.getFileName());
  }

  private Resources createExternalProvider(ResourceFile resource) {
    try {
      String fileBase = FilenameUtils.removeExtension(resource.getFileName());
      return new FileStringProvider(fileBase, locale);
    } catch (IOException e) {
      logger.warn("Could not load properties from file system.", e);
      return new NullStringProvider();
    }
  }

  private Resources createInternalProvider(ResourceFile resourceFile) {
    String bundle = toBundleName(resourceFile.getFileName());
    return new StringProvider(bundle, locale);
  }

  private String toBundleName(String input) {
    String resourceName = input.replace("/", ".").replace(".properties", "");
    boolean isInternationalizationFile = resourceName.matches(".*_..");
    if (isInternationalizationFile) {
      return resourceName.substring(0, resourceName.lastIndexOf("_"));
    }
    return resourceName;
  }
}