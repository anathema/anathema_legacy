package net.sf.anathema.character.presenter.magic;

import com.google.common.collect.Lists;
import net.sf.anathema.character.generic.template.magic.IUniqueCharmType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.util.Identified;

import java.util.ArrayList;
import java.util.List;

public class CharacterCharmTypes extends AbstractCharmTypes {

  private CharacterCharmModel model;

  public CharacterCharmTypes(CharacterCharmModel model) {
    this.model = model;
  }

  @Override
  protected List<Identified> getCurrentCharacterTypes() {
    boolean alienCharms = model.isAllowedAlienCharms();
    ICharacterType[] characterTypes = model.getCharmConfiguration().getCharacterTypes(alienCharms);
    return Lists.<Identified>newArrayList(characterTypes);
  }

  @Override
  protected List<Identified> getAdditionalCharmTypes() {
    List<Identified> typeIds = new ArrayList<Identified>();
    if (model.hasUniqueCharmType()) {
      IUniqueCharmType uniqueType = model.getUniqueCharmType();
      typeIds.add(uniqueType.getId());
    }
    return typeIds;
  }
}
