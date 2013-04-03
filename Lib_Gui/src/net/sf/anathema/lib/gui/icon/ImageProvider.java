package net.sf.anathema.lib.gui.icon;

import com.google.common.base.Preconditions;
import net.sf.anathema.lib.gui.image.ImageLoader;
import net.sf.anathema.lib.gui.image.ImageLoadingException;
import net.sf.anathema.lib.resources.IImageProvider;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

public class ImageProvider implements IImageProvider {

  private final String rootPath;

  public ImageProvider(String rootPath) {
    Preconditions.checkNotNull(rootPath);
    this.rootPath = rootPath;
  }

  @Override
  public Image getImage(Class<?> requestor, String relativePath) {
    InputStream inputStream = getInputStream(requestor, relativePath);
    return loadImage(inputStream);
  }

  private InputStream getInputStream(Class<?> requestor, String relativePath) {
    Preconditions.checkNotNull(relativePath);
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
    } catch (IOException e) {
      throw new ImageLoadingException("Cannot open image: " + e.getMessage()); //$NON-NLS-1$
    }
  }

  @Override
  public ImageIcon getImageIcon(Class<?> requestor, String relativePath) {
    Image image = getImage(requestor, relativePath);
    return image == null ? null : new ImageIcon(image);
  }
}