package net.sf.anathema.lib.gui.icon;

import net.sf.anathema.lib.file.RelativePath;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

public class ImageProvider {

  public Image getImage(RelativePath relativePath) {
    InputStream inputStream = getInputStream(relativePath);
    return loadImage(inputStream);
  }

  private InputStream getInputStream(RelativePath relativePath) {
    InputStream inputStream = ImageProvider.class.getClassLoader().getResourceAsStream(relativePath.relativePath);
    if (inputStream == null) {
      throw new ImageLoadingException("Cannot find image resource at " + relativePath);
    }
    return inputStream;
  }

  private Image loadImage(InputStream inputStream) {
    try {
      return ImageLoader.getMemoryImageWithoutCaching(inputStream);
    } catch (IOException e) {
      throw new ImageLoadingException("Cannot open image: " + e.getMessage());
    }
  }
}