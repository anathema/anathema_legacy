package net.sf.anathema.lib.gui.dialog;

import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.icon.ImageProvider;
import net.sf.anathema.lib.gui.resources.IIconResources;

import javax.swing.Icon;

public class DialogIconResources implements IIconResources {

  public static final Icon DIALOG_HEADER_ICON_BACKGROUND = new ImageProvider().getImageIcon(new RelativePath("icons/dialog_header_icon_background.gif"));
}