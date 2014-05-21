package net.sf.anathema.hero.charms.display.model;

import com.google.common.collect.Lists;
import net.sf.anathema.character.framework.type.CharacterType;
import net.sf.anathema.lib.util.Identifier;

import java.util.List;

public class CharacterCharmTypes extends AbstractCharmTypes {

  private CharmDisplayModel model;

  public CharacterCharmTypes(CharmDisplayModel model) {
    this.model = model;
  }

  @Override
  protected List<Identifier> getCurrentCharacterTypes() {
    boolean alienCharms = model.isAllowedAlienCharms();
    CharacterType[] characterTypes = model.getCharmModel().getCharacterTypes(alienCharms);
    return Lists.<Identifier>newArrayList(characterTypes);
  }
}
