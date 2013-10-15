package net.sf.anathema.character.main;

import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.lib.file.RelativePath;

public class CharacterUI {

  public RelativePath getCharacterDescriptionTabIcon() {
    return new RelativePath("icons/TabDescription16.png");
  }

  public RelativePath getLinkIconPath() {
    return new RelativePath("icons/ButtonLink16b.png");
  }

  public RelativePath getRandomThresholdNameIconPath() {
    return new RelativePath("icons/ButtonRandomNameB16.png");
  }

  public RelativePath getSmallTypeIconPath(CharacterType characterType) {
    return new RelativePath("icons/" + characterType.getId() + "Icon16.png");
  }

  public RelativePath getLargeTypeIconPath(CharacterType characterType) {
    return new RelativePath("icons/" + characterType.getId() + "Icon100.png");
  }

  public RelativePath getMediumBallPath(CharacterType characterType) {
    return new RelativePath("icons/Border" + characterType.getId() + "Button16.png");
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