package net.sf.anathema.lib.gui.icon;

import java.awt.Image;
import java.awt.image.ImageObserver;

public class DimensionGetter implements ImageObserver {
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
        throw new RuntimeException("error while loading image.");
      }
    }
  }
}
