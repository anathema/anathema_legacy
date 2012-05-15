package net.sf.anathema.lib.resources;

import javax.swing.Icon;
import java.awt.Image;

public interface IAnathemaImageProvider {

  Image getImage(Class<?> requestor, String relativePath);

  Icon getImageIcon(Class<?> requestor, String relativePath);
}