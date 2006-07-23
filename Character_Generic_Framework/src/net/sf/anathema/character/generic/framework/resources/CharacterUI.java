package net.sf.anathema.character.generic.framework.resources;

import javax.swing.Icon;

import net.sf.anathema.lib.resources.AbstractUI;
import net.sf.anathema.lib.resources.IResources;

public class CharacterUI extends AbstractUI {

  public CharacterUI(IResources resources) {
    super(resources);
  }

  public Icon getCharacterDescriptionTabIcon() {
    return getIcon("CharacterTabIcon.png"); //$NON-NLS-1$
  }

  public Icon getMediumLockIcon() {
    return getIcon("Lock20.png"); //$NON-NLS-1$
  }

  public Icon getNewCharacterToolBarIcon() {
    return getIcon("toolbar/TaskBarNew24.png"); //$NON-NLS-1$
  }

  public Icon getLoadCharacterToolBarIcon() {
    return getIcon("toolbar/TaskBarOpen24.png"); //$NON-NLS-1$
  }

  public Icon getRandomRealmNameIcon() {
    return getIcon("util/question-mark-purple.gif"); //$NON-NLS-1$
  }

  public Icon getRandomThresholdNameIcon() {
    return getIcon("util/question-mark.gif"); //$NON-NLS-1$
  }

  public Icon getEditComboIcon() {
    return getIcon("Recycle20.png"); //$NON-NLS-1$
  }

  public Icon getCancelComboEditIcon() {
    return getIcon("CancelEdit20.png"); //$NON-NLS-1$
  }
}