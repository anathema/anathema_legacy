package net.sf.anathema.lib.gui.image;

import net.sf.anathema.lib.io.IOUtilities;

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

  private static final class DimensionGetter implements ImageObserver {
    private final Image image;
    private boolean succeeded = false;

    DimensionGetter(Image image) {
      this.image = image;
    }

    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
      if ((infoflags & ImageObserver.ERROR) == ERROR) {
        synchronized (this) {
          notifyAll();
        }
        return false;
      }
      if (width != -1 && height != -1) {
        synchronized (this) {
          succeeded = true;
          notifyAll();
        }
        return false;
      }
      return true;
    }

    int getWidth() {
      ensureLoaded();
      return image.getWidth(this);
    }

    int getHeight() {
      ensureLoaded();
      return image.getHeight(this);
    }

    private void ensureLoaded() {
      synchronized (this) {
        if (image.getWidth(this) == -1 || image.getHeight(this) == -1) {
          try {
            wait();
          } catch (InterruptedException e) {
            // nothing to do
          }
        }
        if (!succeeded) {
          throw new RuntimeException("error while loading image."); //$NON-NLS-1$
        }
      }
    }

  }

  public static class LoadingException extends RuntimeException {
    private LoadingException(String message) {
      super(message);
    }

    private LoadingException(String message, Throwable cause) {
      super(message + " :" + cause.getMessage()); //$NON-NLS-1$
    }
  }

  public static Image getImageWithoutCaching(InputStream inputStream) throws IOException {
    return readImage(inputStream);
  }

  public static Image getMemoryImageWithoutCaching(InputStream inputStream) throws IOException {
    try {
      return createMemoryImage(readImage(inputStream));
    } catch (LoadingException e) {
      throw new IOException("Loading: " + e.getMessage()); //$NON-NLS-1$
    }
  }

  public static Image readImage(InputStream inputStream) throws IOException {
    BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    IOUtilities.copyStream(bufferedInputStream, byteArrayOutputStream);
    return Toolkit.getDefaultToolkit().createImage(byteArrayOutputStream.toByteArray());
  }

  public static Image createMemoryImage(Image image) {
    int h;
    int w;
    DimensionGetter dimensionGetter = new DimensionGetter(image);
    try {
      w = dimensionGetter.getWidth();
      h = dimensionGetter.getHeight();
    } catch (Exception e) {
      throw new LoadingException("image missing or corrupted", e); //$NON-NLS-1$
    }

    int[] pixels = new int[w * h];
    PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, w, h, pixels, 0, w);
    try {
      pixelGrabber.grabPixels();
    } catch (InterruptedException e) {
      throw new LoadingException("interrupted waiting for pixels!", e); //$NON-NLS-1$
    }
    if ((pixelGrabber.getStatus() & ImageObserver.ABORT) != 0) {
      throw new LoadingException("image fetch aborted or errored"); //$NON-NLS-1$
    }

    MemoryImageSource memoryImageSource = new MemoryImageSource(w, h, pixels, 0, w);

    return Toolkit.getDefaultToolkit().createImage(memoryImageSource);
  }

}