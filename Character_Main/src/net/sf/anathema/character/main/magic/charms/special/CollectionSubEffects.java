package net.sf.anathema.character.main.magic.charms.special;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import net.sf.anathema.character.main.magic.charms.special.ISubeffect;
import net.sf.anathema.character.main.magic.charms.special.SubEffects;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CollectionSubEffects implements SubEffects {

  private final List<ISubeffect> effects = new ArrayList<>();

  public void add(ISubeffect effect) {
    effects.add(effect);
  }

  @Override
  public ISubeffect[] getEffects() {
    return effects.toArray(new ISubeffect[effects.size()]);
  }

  @Override
  public ISubeffect getById(final String id) {
    return Iterables.find(effects, new Predicate<ISubeffect>() {
      @Override
      public boolean apply(ISubeffect input) {
        return input.getId().equals(id);
      }
    });
  }

  @Override
  public Iterator<ISubeffect> iterator() {
    return effects.iterator();
  }
}