package net.sf.anathema.hero.charms.model;

import com.google.common.collect.Lists;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.lib.util.Identifier;

import java.util.List;

public class CharacterCharmTypes extends AbstractCharmTypes {

  private CharacterCharmModel model;

  public CharacterCharmTypes(CharacterCharmModel model) {
    this.model = model;
  }

  @Override
  protected List<Identifier> getCurrentCharacterTypes() {
    boolean alienCharms = model.isAllowedAlienCharms();
    CharacterType[] characterTypes = model.getCharmConfiguration().getCharacterTypes(alienCharms);
    return Lists.<Identifier>newArrayList(characterTypes);
  }
}
