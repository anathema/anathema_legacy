package net.sf.anathema.character.main.magic.model.charm.special;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;

import java.util.Iterator;

import static net.sf.anathema.lib.lang.ArrayUtilities.getFirst;

public class ArraySubEffects implements SubEffects {
  private final SubEffect[] subeffects;

  public ArraySubEffects(SubEffect[] subeffects) {
    this.subeffects = subeffects;
  }

  @Override
  public SubEffect[] getEffects() {
    return subeffects;
  }

  @Override
  public SubEffect getById(final String id) {
    return getFirst(subeffects, new Predicate<SubEffect>() {
      @Override
      public boolean apply(SubEffect input) {
        return input.getId().equals(id);
      }
    });
  }

  @Override
  public Iterator<SubEffect> iterator() {
    return Lists.newArrayList(subeffects).iterator();
  }
}