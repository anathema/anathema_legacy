package net.sf.anathema.character.generic.framework.resources;

import javax.swing.Icon;

import net.sf.anathema.lib.resources.AbstractUI;
import net.sf.anathema.lib.resources.IResources;

public class CharacterUI extends AbstractUI {

  public CharacterUI(IResources resources) {
    super(resources);
  }

  public Icon getCharacterDescriptionTabIcon() {
    return getIcon("TabDescription16.png"); //$NON-NLS-1$
  }

  public Icon getLinkIcon() {
    return getIcon("ButtonLink16b.png"); //$NON-NLS-1$
  }

  public Icon getLoadCharacterToolBarIcon() {
    return getIcon("toolbar/TaskBarOpen24.png"); //$NON-NLS-1$
  }

  public Icon getRandomRealmNameIcon() {
    return getIcon("ButtonRandomNameC16.png"); //$NON-NLS-1$
  }

  public Icon getRandomThresholdNameIcon() {
    return getIcon("ButtonRandomNameB16.png"); //$NON-NLS-1$
  }

  public Icon getEditComboIcon() {
    return getIcon("ButtonEdit16.png"); //$NON-NLS-1$
  }

  public Icon getCancelComboEditIcon() {
    return getIcon("ButtonUndo16.png"); //$NON-NLS-1$
  }

  public Icon getFinalizeIcon() {
    return getIcon("ButtonCheck16.png"); //$NON-NLS-1$
  }
}