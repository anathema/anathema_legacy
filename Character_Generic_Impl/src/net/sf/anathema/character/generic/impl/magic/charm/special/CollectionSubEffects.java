package net.sf.anathema.character.generic.impl.magic.charm.special;

import net.sf.anathema.character.generic.magic.charms.special.ISubeffect;
import net.sf.anathema.character.generic.magic.charms.special.SubEffects;

import java.util.ArrayList;
import java.util.List;

public class CollectionSubEffects implements SubEffects {

  private final List<ISubeffect> effects = new ArrayList<ISubeffect>();

  public void add(ISubeffect effect){
    effects.add(effect);
  }

  @Override
  public ISubeffect[] getEffects() {
    return effects.toArray(new ISubeffect[effects.size()]);
  }
}
