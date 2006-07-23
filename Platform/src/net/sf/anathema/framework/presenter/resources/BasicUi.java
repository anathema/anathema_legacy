package net.sf.anathema.framework.presenter.resources;

import javax.swing.Icon;

import net.sf.anathema.lib.resources.AbstractUI;
import net.sf.anathema.lib.resources.IResources;

public class BasicUi extends AbstractUI {

  public BasicUi(IResources resources) {
    super(resources);
  }

  public Icon getMediumBrowseIcon() {
    return getIcon("Folder20.gif"); //$NON-NLS-1$
  }

  public Icon getMediumRemoveIcon() {
    return getIcon("tools/RedX20.png"); //$NON-NLS-1$
  }

  public Icon getSmallRemoveIcon() {
    return getIcon("tools/RedX16.png"); //$NON-NLS-1$
  }

  public Icon getMediumAddIcon() {
    return getIcon("Green+20.png"); //$NON-NLS-1$
  }

  public Icon getSmallAddIcon() {
    return getIcon("Green+16.png"); //$NON-NLS-1$
  }

  public Icon getDoubleLeftArrowIcon() {
    return getIcon("GreenArrowLeft20.png"); //$NON-NLS-1$
  }

  public Icon getDoubleRightArrowIcon() {
    return getIcon("GreenArrowRight20.png"); //$NON-NLS-1$
  }

  public Icon getSingleUpArrowIcon() {
    return getIcon("GreenSingleArrowUp20.png"); //$NON-NLS-1$
  }

  public Icon getSingleDownArrowIcon() {
    return getIcon("GreenSingleArrowDown20.png"); //$NON-NLS-1$
  }

  public Icon getDoubleUpArrowIcon() {
    return getIcon("GreenArrowUp20.png"); //$NON-NLS-1$
  }

  public Icon getDoubleDownArrowIcon() {
    return getIcon("GreenArrowDown20.png"); //$NON-NLS-1$
  }

  public Icon getMediumClearIcon() {
    return getIcon("RemoveAll20.png"); //$NON-NLS-1$
  }
}