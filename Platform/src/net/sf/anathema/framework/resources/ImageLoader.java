package net.sf.anathema.framework.resources;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import net.disy.commons.core.io.IOUtilities;
import net.sf.anathema.lib.logging.Logger;

/**
 * ImageLoader stellt Methoden zur Verf�gung, mit der Images geladen werden
 * koennen. Es wird eine Klasse uebergeben und relativ zu dieser ein Pfad, von
 * der das Bild geladen werden soll. Dies hat den Vorteil, dass der ImageLoader
 * Bilder auch aus JAR Dateien findet.
 * 
 * Der ImageLoader geht davon aus, dass sich die Bilder in einem
 * Unterverzeichnis "images" befinden!
 */
public final class ImageLoader {
  private final static Logger logger = Logger.getLogger(ImageLoader.class);

  private ImageLoader() {
    // nothing to do
  }

  public static Image getMemoryImageWithoutCaching(InputStream inputStream) throws IOException {
    try {
      return createMemoryImage(readImage(inputStream));
    }
    catch (LoadingException e) {
      logger.debug(e);
      throw new IOException("LoadingException: " + e.getMessage()); //$NON-NLS-1$
    }
  }

  public static Image readImage(InputStream inputStream) throws IOException {
    BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    IOUtilities.copyStream(bufferedInputStream, byteArrayOutputStream);
    return Toolkit.getDefaultToolkit().createImage(byteArrayOutputStream.toByteArray());
  }

  private static Image createMemoryImage(Image image) throws LoadingException {
    int h;
    int w;
    DimensionGetter dimensionGetter = new DimensionGetter(image);
    try {
      w = dimensionGetter.getWidth();
      h = dimensionGetter.getHeight();
    }
    catch (Exception e) {
      throw new LoadingException("image missing or corrupted", e); //$NON-NLS-1$
    }
    int[] pixels = new int[w * h];
    PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, w, h, pixels, 0, w);
    try {
      pixelGrabber.grabPixels();
    }
    catch (InterruptedException e) {
      throw new LoadingException("interrupted waiting for pixels!", e); //$NON-NLS-1$
    }
    if ((pixelGrabber.getStatus() & ImageObserver.ABORT) != 0) {
      throw new LoadingException("image fetch aborted or errored"); //$NON-NLS-1$
    }
    MemoryImageSource memoryImageSource = new MemoryImageSource(w, h, pixels, 0, w);
    return Toolkit.getDefaultToolkit().createImage(memoryImageSource);
  }

  private static final class DimensionGetter implements ImageObserver {
    private Image image;
    private boolean succeeded = false;

    DimensionGetter(Image image) {
      this.image = image;
    }

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
          }
          catch (InterruptedException e) {
            // nothing to do
          }
        }
        if (!succeeded) {
          throw new RuntimeException("error while loading image."); //$NON-NLS-1$
        }
      }
    }
  }

  private static class LoadingException extends Exception {
    private static final long serialVersionUID = 3049020079587338246L;

    private LoadingException(String message) {
      super(message);
    }

    private LoadingException(String message, Throwable cause) {
      super(message + " :" + cause.getMessage()); //$NON-NLS-1$
    }
  }
}