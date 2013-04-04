package net.sf.anathema.character.generic.framework.resources;

import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.gui.ui.AbstractUI;

import javax.swing.Icon;

public class CharacterUI extends AbstractUI {
  public Icon getCharacterDescriptionTabIcon() {
    return getIcon("icons/TabDescription16.png"); //$NON-NLS-1$
  }

  public Icon getLinkIcon() {
    return getIcon("icons/ButtonLink16b.png"); //$NON-NLS-1$
  }

  public Icon getRandomRealmNameIcon() {
    return getIcon("icons/ButtonRandomNameC16.png"); //$NON-NLS-1$
  }

  public Icon getRandomThresholdNameIcon() {
    return getIcon("icons/ButtonRandomNameB16.png"); //$NON-NLS-1$
  }

  public Icon getCancelComboEditIcon() {
    return getIcon("icons/ButtonUndo16.png"); //$NON-NLS-1$
  }

  public Icon getFinalizeIcon() {
    return getIcon("icons/ButtonCheck16.png"); //$NON-NLS-1$
  }

  public Icon getFinalizeXPIcon() {
    return getIcon("icons/ButtonCheckXP16.png"); //$NON-NLS-1$
  }

  public Icon getSmallTypeIcon(ICharacterType characterType) {
    return getIcon(getSmallTypeIconPath(characterType)); //$NON-NLS-1$
  }

  public String getSmallTypeIconPath(ICharacterType characterType) {
    return "icons/" + characterType.getId() + "Icon16.png";
  }

  public String getLargeTypeIconPath(ICharacterType characterType) {
    return "icons/"+characterType.getId() + "Icon100.png";
  }

  public Icon getMediumBallResource(ICharacterType characterType) {
    return getIcon("icons/Border" + characterType.getId() + "Button16.png"); //$NON-NLS-1$ //$NON-NLS-2$
  }

  public Icon getUnselectedBallResource() {
    return getIcon("icons/BorderUnselectedButton16.png"); //$NON-NLS-1$
  }

  public Icon getUnselectableBallResource() {
    return getIcon("icons/BorderUnselectableButton16.png"); //$NON-NLS-1$
  }
}