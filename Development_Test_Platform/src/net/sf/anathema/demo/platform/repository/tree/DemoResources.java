package net.sf.anathema.demo.platform.repository.tree;

import java.awt.Image;

import javax.swing.Icon;

import net.sf.anathema.lib.resources.IResources;

public class DemoResources implements IResources {

  public boolean supportsKey(String key) {
    return true;
  }

  public String getString(String key) {
    return key.substring(1);
  }

  public String getString(String key, Object[] arguments) {
    return null;
  }

  public Image getImage(String relativePath) {
    return null;
  }

  public Image getAnimatedImage(String relativePath) {
    return null;
  }

  public Icon getImageIcon(String relativePath) {
    return null;
  }

  public Icon getAnimatedImageIcon(String relativePath) {
    return null;
  }
}