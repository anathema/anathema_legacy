package net.sf.anathema.framework.resources;

import net.sf.anathema.initialization.reflections.ExternalResourceFile;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.resources.FileStringProvider;
import net.sf.anathema.lib.resources.IStringResourceHandler;
import net.sf.anathema.lib.resources.InternalResourceFile;
import net.sf.anathema.lib.resources.NullStringProvider;
import net.sf.anathema.lib.resources.ResourceFile;
import net.sf.anathema.lib.resources.StringProvider;
import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.util.Locale;

public class StringProviderFactory {
  private static final Logger logger = Logger.getLogger(AnathemaResources.class);
  private final Locale locale;

  public StringProviderFactory(Locale locale) {
    this.locale = locale;
  }

  public IStringResourceHandler create(String bundleName, ResourceFile resource) {
    if (resource instanceof InternalResourceFile) {
      return createInternalProvider(bundleName);
    }
    if (resource instanceof ExternalResourceFile) {
      return createExternalProvider(resource);
    }
    throw new RuntimeException("Unknown resource type: " + resource.getFileName());
  }

  private IStringResourceHandler createExternalProvider(ResourceFile resource) {
    try {
      String fileBase = FilenameUtils.removeExtension(resource.getFileName());
      return new FileStringProvider(fileBase, locale);
    } catch (IOException e) {
      logger.warn("Could not load properties from file system.", e);
      return new NullStringProvider();
    }
  }

  private IStringResourceHandler createInternalProvider(String bundleName) {
    return new StringProvider(bundleName, locale); //$NON-NLS-1$
  }
}
