package net.sf.anathema.character.presenter.magic;

import net.sf.anathema.charmtree.presenter.CharmTypes;
import net.sf.anathema.lib.util.Identified;

import java.util.ArrayList;
import java.util.List;

import static net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities.MARTIAL_ARTS;

public abstract class AbstractCharmTypes implements CharmTypes {
  
  @Override
  public Identified[] getCurrentCharmTypes() {
    List<Identified> types = new ArrayList<>();
    types.addAll(getCurrentCharacterTypes());
    types.addAll(getAdditionalCharmTypes());
    types.add(MARTIAL_ARTS);
    return types.toArray(new Identified[types.size()]);
  }

  protected abstract List<Identified> getCurrentCharacterTypes();
  
  protected abstract List<Identified> getAdditionalCharmTypes();
}
