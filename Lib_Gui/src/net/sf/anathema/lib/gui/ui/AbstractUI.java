package net.sf.anathema.lib.gui.ui;

import net.sf.anathema.lib.gui.icon.ImageProvider;

import javax.swing.Icon;

public abstract class AbstractUI {

  private final ImageProvider imageProvider = new ImageProvider(".");

  protected final Icon getIcon(String path) {
    return imageProvider.getImageIcon(this.getClass(), path);
  }
}