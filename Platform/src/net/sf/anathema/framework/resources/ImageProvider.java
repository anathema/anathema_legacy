package net.sf.anathema.framework.resources;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.ImageIcon;

import net.disy.commons.core.util.Ensure;
import net.disy.commons.swing.image.ImageLoader;
import net.disy.commons.swing.image.ImageLoadingException;
import net.sf.anathema.lib.resources.IAnathemaImageProvider;

public class ImageProvider implements IAnathemaImageProvider {

  private final String rootPath;

  public ImageProvider(String rootPath) {
    Ensure.ensureNotNull("RootPath is null.", rootPath); //$NON-NLS-1$
    this.rootPath = rootPath;
  }

  public Image getImage(Class< ? > requestor, String relativePath) {
    InputStream inputStream = getInputStream(requestor, relativePath);
    return loadImage(inputStream);
  }

  private InputStream getInputStream(Class< ? > requestor, String relativePath) {
    Ensure.ensureNotNull("RelativePath to image is null.", relativePath); //$NON-NLS-1$
    String resourceName = rootPath + "/" + relativePath; //$NON-NLS-1$
    InputStream inputStream = requestor.getClassLoader().getResourceAsStream(resourceName);
    if (inputStream == null) {
      throw new ImageLoadingException("Cannot find image resource: " + resourceName); //$NON-NLS-1$
    }
    return inputStream;
  }

  private Image loadImage(InputStream inputStream) {
    try {
      return ImageLoader.getMemoryImageWithoutCaching(inputStream);
    }
    catch (IOException e) {
      throw new ImageLoadingException("Cannot open image: " + e.getMessage()); //$NON-NLS-1$
    }
  }

  public ImageIcon getImageIcon(Class< ? > requestor, String relativePath) {
    Image image = getImage(requestor, relativePath);
    return image == null ? null : new ImageIcon(image);
  }
}