package net.sf.anathema.lib.gui.image;

import javax.swing.Icon;
import java.awt.Image;

public interface IImageProvider {

  Image getImage(String relativePath);

  Icon getImageIcon(String relativePath);
}