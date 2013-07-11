package net.sf.anathema.hero.charms.model;

import net.sf.anathema.hero.charms.model.CharmTypes;
import net.sf.anathema.lib.util.Identifier;

import java.util.ArrayList;
import java.util.List;

import static net.sf.anathema.character.main.magic.model.charms.MartialArtsUtilities.MARTIAL_ARTS;

public abstract class AbstractCharmTypes implements CharmTypes {

  @Override
  public Identifier[] getCurrentCharmTypes() {
    List<Identifier> types = new ArrayList<>();
    types.addAll(getCurrentCharacterTypes());
    types.add(MARTIAL_ARTS);
    return types.toArray(new Identifier[types.size()]);
  }

  protected abstract List<Identifier> getCurrentCharacterTypes();
}