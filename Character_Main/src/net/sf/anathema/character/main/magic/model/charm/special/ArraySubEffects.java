package net.sf.anathema.character.main.magic.model.charm.special;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;

import java.util.Iterator;

import static net.sf.anathema.lib.lang.ArrayUtilities.getFirst;

public class ArraySubEffects implements SubEffects {
  private final SubEffect2[] subeffects;

  public ArraySubEffects(SubEffect2[] subeffects) {
    this.subeffects = subeffects;
  }

  @Override
  public SubEffect2[] getEffects() {
    return subeffects;
  }

  @Override
  public SubEffect2 getById(final String id) {
    return getFirst(subeffects, new Predicate<SubEffect2>() {
      @Override
      public boolean apply(SubEffect2 input) {
        return input.getId().equals(id);
      }
    });
  }

  @Override
  public Iterator<SubEffect2> iterator() {
    return Lists.newArrayList(subeffects).iterator();
  }
}