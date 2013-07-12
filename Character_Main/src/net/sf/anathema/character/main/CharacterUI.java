package net.sf.anathema.character.main;

import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.ui.AbstractUI;

import javax.swing.Icon;

public class CharacterUI extends AbstractUI {

  public RelativePath getCharacterDescriptionTabIcon() {
    return new RelativePath("icons/TabDescription16.png");
  }

  public Icon getLinkIcon() {
    return getIcon(getLinkIconPath());
  }

  public RelativePath getLinkIconPath() {
    return new RelativePath("icons/ButtonLink16b.png");
  }

  public RelativePath getRandomThresholdNameIconPath() {
    return new RelativePath("icons/ButtonRandomNameB16.png");
  }

  public Icon getSmallTypeIcon(CharacterType characterType) {
    return getIcon(getSmallTypeIconPath(characterType));
  }

  public RelativePath getSmallTypeIconPath(CharacterType characterType) {
    return new RelativePath("icons/" + characterType.getId() + "Icon16.png");
  }

  public RelativePath getLargeTypeIconPath(CharacterType characterType) {
    return new RelativePath("icons/" + characterType.getId() + "Icon100.png");
  }

  public Icon getMediumBallResource(CharacterType characterType) {
    return getIcon(getMediumBallPath(characterType));
  }

  public RelativePath getMediumBallPath(CharacterType characterType) {
    return new RelativePath("icons/Border" + characterType.getId() + "Button16.png");
  }

  public Icon getUnselectedBallResource() {
    return getIcon(new RelativePath("icons/BorderUnselectedButton16.png"));
  }

  public Icon getUnselectableBallResource() {
    return getIcon(new RelativePath("icons/BorderUnselectableButton16.png"));
  }

  public RelativePath getRandomRealmNameIconPath() {
    return new RelativePath("icons/ButtonRandomNameC16.png");
  }

  public RelativePath getCancelComboEditIconPath() {
    return new RelativePath("icons/ButtonUndo16.png");
  }

  public RelativePath getFinalizeIconPath() {
    return new RelativePath("icons/ButtonCheck16.png");
  }
}