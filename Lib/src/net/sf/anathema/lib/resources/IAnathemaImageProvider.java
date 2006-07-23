package net.sf.anathema.lib.resources;

import java.awt.Image;

import javax.swing.Icon;

public interface IAnathemaImageProvider {

  public Image getImage(Class< ? > requestor, String relativePath);

  public Icon getImageIcon(Class< ? > requestor, String relativePath);
}