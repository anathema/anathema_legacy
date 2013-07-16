package net.sf.anathema.hero.charms.model.special;

import com.google.common.base.Predicate;
import net.sf.anathema.character.main.magic.charm.special.ISpecialCharm;

public interface SpecialCharmList {
  void add(ISpecialCharm charm);

  void showViews();

  void setVisibilityPredicate(Predicate<String> predicate);
}
