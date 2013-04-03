package net.sf.anathema.lib.gui.dialog;

import net.sf.anathema.lib.gui.icon.ImageProvider;
import net.sf.anathema.lib.gui.resources.IIconResources;

import javax.swing.Icon;

public class DialogIconResources implements IIconResources {

  public static final Icon DIALOG_HEADER_ICON_BACKGROUND = getImageIcon("icons/dialog_header_icon_background.gif");

  private static Icon getImageIcon(String relativePath) {
    return new ImageProvider(".").getImageIcon(DialogIconResources.class, relativePath);
  }
}