package net.sf.anathema.lib.gui.dialog;

import net.sf.anathema.lib.gui.image.ImageProvider;
import net.sf.anathema.lib.gui.resources.IIconResources;

import javax.swing.Icon;

public class DialogIconResources implements IIconResources {

  public static final Icon DIALOG_HEADER_ICON_BACKGROUND = getImageIcon("dialog_header_icon_background.gif"); //$NON-NLS-1$
  public static final Icon DIALOG_HELP = getImageIcon("dialog_help.gif"); //$NON-NLS-1$

  private static Icon getImageIcon(final String relativePath) {
    return new ImageProvider("net/sf/anathema/lib/gui/dialog/icons").getImageIcon(relativePath); //$NON-NLS-1$
  }
}