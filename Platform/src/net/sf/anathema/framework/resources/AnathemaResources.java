package net.sf.anathema.framework.resources;

import java.awt.Image;
import java.io.IOException;
import java.util.Locale;

import javax.swing.Icon;

import net.sf.anathema.initialization.reflections.ExternalResourceFile;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.resources.InternalResourceFile;
import net.sf.anathema.lib.resources.DefaultStringProvider;
import net.sf.anathema.lib.resources.FileStringProvider;
import net.sf.anathema.lib.resources.IAnathemaImageProvider;
import net.sf.anathema.lib.resources.ResourceFile;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.resources.MultiSourceStringProvider;
import net.sf.anathema.lib.resources.StringProvider;
import org.apache.commons.io.FilenameUtils;

public class AnathemaResources implements IResources {

  private static final Logger logger = Logger.getLogger(AnathemaResources.class);
  private final IAnathemaImageProvider imageProvider = new ImageProvider("icons"); //$NON-NLS-1$
  private final MultiSourceStringProvider stringHandler = new MultiSourceStringProvider();

  public AnathemaResources() {
    try {
      stringHandler.add(new FileStringProvider("custom", getLocale())); //$NON-NLS-1$
      stringHandler.add(new DefaultStringProvider("Literal")); //$NON-NLS-1$
    } catch (IOException ioException) {
      logger.error("Error loading custom properties.", ioException); //$NON-NLS-1$
    }
  }

  public void addResourceBundle(String bundleName, ResourceFile resource) {
    if (resource instanceof InternalResourceFile) {
      stringHandler.add(new StringProvider(bundleName, getLocale())); //$NON-NLS-1$
    } else if (resource instanceof ExternalResourceFile) {
      try {
        stringHandler.add(new FileStringProvider(FilenameUtils.removeExtension(resource.getFileName()), getLocale()));
      } catch (IOException e) {
        logger.warn("Could not load properties from file system.", e);
      }
    }
  }

  @Override
  public boolean supportsKey(String key) {
    return stringHandler.supportsKey(key);
  }

  @Override
  public String getString(String key, Object... arguments) {
    return stringHandler.getString(key, arguments);
  }

  @Override
  public Image getImage(Class<?> requestor, String relativePath) {
    return imageProvider.getImage(requestor, relativePath);
  }

  @Override
  public Icon getImageIcon(Class<?> requestor, String relativePath) {
    return imageProvider.getImageIcon(requestor, relativePath);
  }

  private Locale getLocale() {
    return Locale.getDefault();
  }
}