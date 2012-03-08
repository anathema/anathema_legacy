package net.sf.anathema.character.presenter.charm;

import net.sf.anathema.charmtree.presenter.CharmTypes;
import net.sf.anathema.lib.util.IIdentificate;

import java.util.ArrayList;
import java.util.List;

import static net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities.MARTIAL_ARTS;

public abstract class AbstractCharmTypes implements CharmTypes {
  
  @Override
  public IIdentificate[] getCurrentCharmTypes() {
    List<IIdentificate> types = new ArrayList<IIdentificate>();
    types.addAll(getCurrentCharacterTypes());
    types.addAll(getAdditionalCharmTypes());
    types.add(MARTIAL_ARTS);
    return types.toArray(new IIdentificate[types.size()]);
  }

  protected abstract List<IIdentificate> getCurrentCharacterTypes();
  
  protected abstract List<IIdentificate> getAdditionalCharmTypes();
}
