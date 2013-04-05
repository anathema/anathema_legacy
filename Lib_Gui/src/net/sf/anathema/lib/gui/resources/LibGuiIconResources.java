package net.sf.anathema.lib.gui.resources;

import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.icon.ImageProvider;

import javax.swing.Icon;

public class LibGuiIconResources implements IIconResources {

  public static final Icon COPY = new ImageProvider().getImageIcon(new RelativePath("icons/copy.gif"));
}