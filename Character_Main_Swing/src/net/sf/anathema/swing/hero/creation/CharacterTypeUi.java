package net.sf.anathema.swing.hero.creation;

import net.sf.anathema.character.main.type.ICharacterType;
import net.sf.anathema.lib.resources.Resources;

public class CharacterTypeUi {

  private Resources resources;

  public CharacterTypeUi(Resources resources) {
    this.resources = resources;
  }

  public String getLabel(ICharacterType type) {
    return resources.getString("CharacterGenerator.NewCharacter." + type.getId() + ".Name");
  }
}
