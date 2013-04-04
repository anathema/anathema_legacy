package net.sf.anathema.character.generic.framework.resources;

import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.gui.ui.AbstractUI;

import javax.swing.Icon;

public class CharacterUI extends AbstractUI {

  public Icon getLinkIcon() {
    return getIcon("icons/ButtonLink16b.png");
  }

  public Icon getRandomRealmNameIcon() {
    return getIcon("icons/ButtonRandomNameC16.png");
  }

  public Icon getRandomThresholdNameIcon() {
    return getIcon("icons/ButtonRandomNameB16.png");
  }

  public Icon getCancelComboEditIcon() {
    return getIcon("icons/ButtonUndo16.png");
  }

  public Icon getFinalizeIcon() {
    return getIcon("icons/ButtonCheck16.png");
  }

  public Icon getFinalizeXPIcon() {
    return getIcon("icons/ButtonCheckXP16.png");
  }

  public Icon getSmallTypeIcon(ICharacterType characterType) {
    return getIcon(getSmallTypeIconPath(characterType));
  }

  public String getSmallTypeIconPath(ICharacterType characterType) {
    return "icons/" + characterType.getId() + "Icon16.png";
  }

  public String getLargeTypeIconPath(ICharacterType characterType) {
    return "icons/"+characterType.getId() + "Icon100.png";
  }

  public Icon getMediumBallResource(ICharacterType characterType) {
    return getIcon("icons/Border" + characterType.getId() + "Button16.png");
  }

  public Icon getUnselectedBallResource() {
    return getIcon("icons/BorderUnselectedButton16.png");
  }

  public Icon getUnselectableBallResource() {
    return getIcon("icons/BorderUnselectableButton16.png");
  }
}