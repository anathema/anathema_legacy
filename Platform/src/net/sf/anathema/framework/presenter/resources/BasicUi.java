package net.sf.anathema.framework.presenter.resources;

import javax.swing.Icon;

import net.sf.anathema.lib.resources.IResources;

public class BasicUi {

  private final IResources resources;

  public BasicUi(IResources resources) {
    this.resources = resources;
  }

  public Icon getMediumEditIcon() {
    return resources.getImageIcon("Recycle20.png"); //$NON-NLS-1$
  }

  public Icon getMediumSearchIcon() {
    return resources.getImageIcon("Search20.gif"); //$NON-NLS-1$
  }

  public Icon getMediumBrowseIcon() {
    return resources.getImageIcon("Folder20.gif"); //$NON-NLS-1$
  }

  public Icon getMediumRemoveIcon() {
    return resources.getImageIcon("tools/RedX20.png"); //$NON-NLS-1$
  }

  public Icon getSmallRemoveIcon() {
    return resources.getImageIcon("tools/RedX16.png"); //$NON-NLS-1$
  }

  public Icon getMediumAddIcon() {
    return resources.getImageIcon("Green+20.png"); //$NON-NLS-1$
  }

  public Icon getSmallAddIcon() {
    return resources.getImageIcon("Green+16.png"); //$NON-NLS-1$
  }

  public Icon getDoubleLeftArrowIcon() {
    return resources.getImageIcon("GreenArrowLeft20.png"); //$NON-NLS-1$
  }

  public Icon getDoubleRightArrowIcon() {
    return resources.getImageIcon("GreenArrowRight20.png"); //$NON-NLS-1$
  }

  public Icon getSingleUpArrowIcon() {
    return resources.getImageIcon("GreenSingleArrowUp20.png"); //$NON-NLS-1$
  }

  public Icon getSingleDownArrowIcon() {
    return resources.getImageIcon("GreenSingleArrowDown20.png"); //$NON-NLS-1$
  }

  public Icon getDoubleUpArrowIcon() {
    return resources.getImageIcon("GreenArrowUp20.png"); //$NON-NLS-1$
  }

  public Icon getDoubleDownArrowIcon() {
    return resources.getImageIcon("GreenArrowDown20.png"); //$NON-NLS-1$
  }

  public Icon getMediumClearIcon() {
    return resources.getImageIcon("RemoveAll20.png"); //$NON-NLS-1$
  }

  public Icon getReplaceToLeftIcon() {
    return resources.getImageIcon("GreenArrowLeftWithRedX20.png"); //$NON-NLS-1$
  }

  public Icon getMediumLockIcon() {
    return resources.getImageIcon("Lock20.png"); //$NON-NLS-1$
  }
}