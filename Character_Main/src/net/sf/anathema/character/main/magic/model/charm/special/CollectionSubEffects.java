package net.sf.anathema.character.main.magic.model.charm.special;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CollectionSubEffects implements SubEffects {

  private final List<SubEffect2> effects = new ArrayList<>();

  public void add(SubEffect2 effect) {
    effects.add(effect);
  }

  @Override
  public SubEffect2[] getEffects() {
    return effects.toArray(new SubEffect2[effects.size()]);
  }

  @Override
  public SubEffect2 getById(final String id) {
    return Iterables.find(effects, new Predicate<SubEffect2>() {
      @Override
      public boolean apply(SubEffect2 input) {
        return input.getId().equals(id);
      }
    });
  }

  @Override
  public Iterator<SubEffect2> iterator() {
    return effects.iterator();
  }
}