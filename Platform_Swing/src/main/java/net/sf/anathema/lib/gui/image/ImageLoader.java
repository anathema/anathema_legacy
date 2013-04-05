package net.sf.anathema.lib.gui.image;

import org.apache.commons.io.IOUtils;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageLoader {

  public static Image getMemoryImageWithoutCaching(InputStream inputStream) throws IOException {
    try {
      return createMemoryImage(readImage(inputStream));
    } catch (LoadingException e) {
      throw new IOException("Loading: " + e.getMessage());
    }
  }

  private static Image readImage(InputStream inputStream) throws IOException {
    BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    IOUtils.copy(bufferedInputStream, byteArrayOutputStream);
    return Toolkit.getDefaultToolkit().createImage(byteArrayOutputStream.toByteArray());
  }

  private static Image createMemoryImage(Image image) {
    int h;
    int w;
    DimensionGetter dimensionGetter = new DimensionGetter(image);
    try {
      w = dimensionGetter.getWidth();
      h = dimensionGetter.getHeight();
    } catch (Exception e) {
      throw new LoadingException("image missing or corrupted", e);
    }
    int[] pixels = new int[w * h];
    PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, w, h, pixels, 0, w);
    try {
      pixelGrabber.grabPixels();
    } catch (InterruptedException e) {
      throw new LoadingException("interrupted waiting for pixels!", e);
    }
    if ((pixelGrabber.getStatus() & ImageObserver.ABORT) != 0) {
      throw new LoadingException("image fetch aborted or errored");
    }
    MemoryImageSource memoryImageSource = new MemoryImageSource(w, h, pixels, 0, w);
    return Toolkit.getDefaultToolkit().createImage(memoryImageSource);
  }
}