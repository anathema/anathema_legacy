package net.sf.anathema.character.presenter.magic;

import com.google.common.collect.Lists;
import net.sf.anathema.character.generic.type.ICharacterType;
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
    ICharacterType[] characterTypes = model.getCharmConfiguration().getCharacterTypes(alienCharms);
    return Lists.<Identifier>newArrayList(characterTypes);
  }
}
