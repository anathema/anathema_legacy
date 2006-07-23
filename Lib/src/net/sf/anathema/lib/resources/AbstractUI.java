package net.sf.anathema.lib.resources;

import javax.swing.Icon;


public abstract class AbstractUI {

  private final IResources resources;

  public AbstractUI(IResources resources) {
    this.resources = resources;
  }
  
  protected final Icon getIcon(String path){
    return resources.getImageIcon(path);
  }
}