package net.sf.anathema.lib.gui.image;

import com.google.common.base.Preconditions;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

public class ImageProvider implements IImageProvider {

  private final String rootPath;

  /**
   * class for loading images out of a central path
   * @param rootPath central path where all images are located
   */
  public ImageProvider(String rootPath) {
    Preconditions.checkNotNull(rootPath, "RootPath is null.");
    this.rootPath = rootPath;
  }

  /**
   * gets an image relative to root path
   * @param relativePath
   * @return Image or null, if image cannot be loaded and hasDummyImage is false
   */
  @Override
  public Image getImage(String relativePath) {
    return getImage(relativePath, false);
  }

  /**
   * @param relativePath
   * @param isAnimated
   * @return Image or null, if image cannot be loaded and hasDummyImage is false
   */
  private Image getImage(String relativePath, boolean isAnimated) {
    InputStream inputStream = getInputStream(relativePath);
    if (inputStream == null) {
      return null;
    }
    try {
      return loadImage(isAnimated, inputStream);
    }
    finally {
      org.apache.commons.io.IOUtils.closeQuietly(inputStream);
    }
  }

  private Image loadImage(boolean isAnimated, InputStream inputStream) {
    try {
      if (isAnimated) {
        return ImageLoader.getImageWithoutCaching(inputStream);
      }
      return ImageLoader.getMemoryImageWithoutCaching(inputStream);
    }
    catch (IOException e) {
      throw new ImageLoadingException("Cannot open image: " + e.getMessage()); //$NON-NLS-1$
    }
  }

  private InputStream getInputStream(String relativePath) {
    Preconditions.checkNotNull(relativePath, "RelativePath to image is null.");
    String resourceName = rootPath + "/" + relativePath; //$NON-NLS-1$
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourceName);
    if (inputStream == null) {
      throw new ImageLoadingException("Cannot find image resource: " + resourceName); //$NON-NLS-1$
    }
    return inputStream;
  }

  @Override
  public Icon getImageIcon(String relativePath) {
    Image image = getImage(relativePath);
    return image == null ? null : new ImageIcon(image);
  }

}