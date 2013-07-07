package net.sf.anathema.character.main.presenter.magic;

import net.sf.anathema.character.main.charmtree.presenter.CharmTypes;
import net.sf.anathema.lib.util.Identifier;

import java.util.ArrayList;
import java.util.List;

import static net.sf.anathema.character.main.magic.MartialArtsUtilities.MARTIAL_ARTS;

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
