package net.sf.anathema.framework.resources;

import java.awt.Image;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.Icon;

import net.disy.commons.swing.image.IImageProvider;
import net.disy.commons.swing.image.ImageProvider;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.resources.IStringResourceHandler;

public class AnathemaResources implements IAnathemaResources {

  private final Logger logger = Logger.getLogger(AnathemaResources.class);
  private final List<IStringResourceHandler> stringResourceHandlers = new ArrayList<IStringResourceHandler>();
  private final IImageProvider imageProvider = new ImageProvider("icons"); //$NON-NLS-1$

  public AnathemaResources() {
    try {
      addStringResourceHandler(new FileStringProvider("custom", getLocale())); //$NON-NLS-1$
    }
    catch (IOException ioException) {
      logger.error("Error loading custom properties.", ioException); //$NON-NLS-1$
    }
  }

  public void addStringResourceHandler(IStringResourceHandler handler) {
    stringResourceHandlers.add(handler);
  }

  public boolean supportsKey(String key) {
    for (IStringResourceHandler handler : stringResourceHandlers) {
      if (handler.supportsKey(key)) {
        return true;
      }
    }
    return false;
  }

  public String getString(String key) {
    for (IStringResourceHandler handler : stringResourceHandlers) {
      if (handler.supportsKey(key)) {
        return handler.getString(key);
      }
    }
    return "##" + key + "##"; //$NON-NLS-1$ //$NON-NLS-2$
  }

  public String getString(String key, Object[] arguments) {
    String formatPattern = getString(key);
    return MessageFormat.format(formatPattern, arguments);
  }

  public Image getImage(String relativePath) {
    return imageProvider.getImage(relativePath);
  }

  public Image getAnimatedImage(String relativePath) {
    return imageProvider.getAnimatedImage(relativePath);
  }

  public Icon getImageIcon(String relativePath) {
    return imageProvider.getImageIcon(relativePath);
  }

  public Icon getAnimatedImageIcon(String relativePath) {
    return imageProvider.getAnimatedImageIcon(relativePath);
  }

  public String getDefaultFrameTitle() {
    return getString("MainFrame.Title"); //$NON-NLS-1$
  }

  public Locale getLocale() {
    return Locale.getDefault();
  }

}