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
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import net.disy.commons.core.io.IOUtilities;
import net.sf.anathema.lib.logging.Logger;


/**
 * ImageLoader stellt Methoden zur Verfügung, mit der Images geladen werden
 * koennen. Es wird eine Klasse uebergeben und relativ zu dieser ein Pfad, von
 * der das Bild geladen werden soll. Dies hat den Vorteil, dass der ImageLoader
 * Bilder auch aus JAR Dateien findet.
 * 
 * Der ImageLoader geht davon aus, dass sich die Bilder in einem
 * Unterverzeichnis "images" befinden!
 */
public final class ImageLoader {
  /**
   * Unterverzeichnis des Classpath(Codebase), in dem die IconImages gesucht
   * werden
   */
  private static final String IMAGE_DIR = "images/"; //$NON-NLS-1$

  // Change [Thu Sep 20 11:29:53 2001]behrens: cache eingefuegt
  private static Hashtable<String,Image> imageCache = new Hashtable<String,Image>();

  private static Hashtable<String,ImageIcon> imageIconCache = new Hashtable<String,ImageIcon>();

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

  public static Image getImageWithoutCaching(InputStream inputStream) throws IOException {
    return readImage(inputStream);
  }

  public static Image getImage(Class<?> relativeClass, String filename) {
    String cacheKey = computeCacheKey(relativeClass, filename);

    Image storedImage = imageCache.get(cacheKey);
    if (storedImage != null) {
      return storedImage;
    }

    Image rValue = null;
    InputStream is = relativeClass.getResourceAsStream(IMAGE_DIR + filename);
    if (is != null) {
      try {
        rValue = readImage(is);
        imageCache.put(cacheKey, rValue);
      }
      catch (IOException ioe) {
        logger.warn("Error loading: " + IMAGE_DIR + filename + " relative from: " + relativeClass); //$NON-NLS-1$ //$NON-NLS-2$
      }
    }
    else {
      logger.warn("Error loading: " + IMAGE_DIR + filename + " relative from: " + relativeClass); //$NON-NLS-1$ //$NON-NLS-2$
    }
    return rValue;
  }

  public static ImageIcon getImageIcon(Class<?> relativeClass, String filename) {
    Image image = getImage(relativeClass, filename);
    if (image == null) {
      return null;
    }
    return new ImageIcon(image);
  }

  public static ImageIcon getMemoryImageIcon(Class<?> relativeClass, String filename) {
    String cacheKey = computeCacheKey(relativeClass, filename);
    ImageIcon storedIcon = imageIconCache.get(cacheKey);
    if (storedIcon != null) {
      return storedIcon;
    }
    Image image = getMemoryImage(relativeClass, filename);
    ImageIcon icon = new ImageIcon(image);
    imageIconCache.put(cacheKey, icon);
    return icon;
  }

  public static Image getMemoryImage(Class<?> relativeClass, String filename) {
    try {
      return createMemoryImage(getImage(relativeClass, filename));
    }
    catch (LoadingException exception) {
      throw new RuntimeException(exception.getMessage());
    }
  }

  public void clearCache() {
    imageCache.clear();
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

  public static ImageIcon defaultGetImageIcon(Class<?> relativeClass, String filename) {
    String cacheKey = computeCacheKey(relativeClass, filename);
    ImageIcon icon = imageIconCache.get(cacheKey);
    if (icon == null) {
      icon = (ImageIcon) defaultMakeIcon(relativeClass, filename);
      if (icon != null) {
        imageIconCache.put(cacheKey, icon);
      }
    }
    return icon;
  }

  private static String computeCacheKey(Class<?> relativeClass, String filename) {
    String className = relativeClass.getName();
    int index = className.lastIndexOf('.');
    if (index == -1) {
      return filename;
    }
    return className.substring(0, index) + '/' + filename;
  }

  private static Icon defaultMakeIcon(Class<?> relativeClass, final String gifFile) {
    /*
     * Copy resource into a byte array. This is necessary because several
     * browsers consider Class.getResource a security risk because it can be
     * used to load additional classes. Class.getResourceAsStream just returns
     * raw bytes, which we can convert to an image.
     * 
     * If this method fails, try Thorwalds procedure. He is loading images via
     * Applet.getImage()
     */

    try {

      //DebugStream.println("trying getResourceAsStream(\"" + IMAGE_DIR + "\" +
      // \"" + gifFile + "\");");

      String fullFileName = IMAGE_DIR + gifFile;
      InputStream resource = relativeClass.getResourceAsStream(fullFileName);
      if (resource == null) {
        logger.error(relativeClass.getName() + ": " + fullFileName + " not found."); //$NON-NLS-1$ //$NON-NLS-2$
        return null;
      }
      BufferedInputStream in = new BufferedInputStream(resource);
      ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
      byte[] buffer = new byte[1024];
      int n;
      while ((n = in.read(buffer)) > 0) {
        out.write(buffer, 0, n);
      }
      in.close();
      out.flush();

      buffer = out.toByteArray();
      if (buffer.length == 0) {
        logger.error("error: " + gifFile + " is zero-length"); //$NON-NLS-1$ //$NON-NLS-2$
        return null;
      }
      return new ImageIcon(Toolkit.getDefaultToolkit().createImage(buffer));
    }
    catch (java.net.MalformedURLException mex) {
      logger.error(mex);
      return null;
    }
    catch (IOException iex) {
      logger.error(iex);
      return null;
    }
  }

  public static JButton createImageButton(
      Class<?> relativeClass,
      String label,
      String filename,
      String filename2) {
    JButton b = createImageButton(relativeClass, label, filename);
    b.setPressedIcon(getMemoryImageIcon(relativeClass, filename2));
    b.setBorder(BorderFactory.createEtchedBorder());
    //b.setToolTipText(toolTipText); // auskommentiert, weil Netscape keine
    // ToolTips anzeigt
    // und im Appletviewer trotz modalem Dialog
    // das Fenster wild nach vorne springt.

    return b;
  }

  public static JButton createImageButton(Class<?> relativeClass, String label, String filename) {
    return new JButton(label, getMemoryImageIcon(relativeClass, filename));
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
    private LoadingException(String message) {
      super(message);
    }

    private LoadingException(String message, Throwable cause) {
      super(message + " :" + cause.getMessage()); //$NON-NLS-1$
    }
  }
}