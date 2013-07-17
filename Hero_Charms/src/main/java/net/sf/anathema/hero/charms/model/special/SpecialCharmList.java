package net.sf.anathema.hero.charms.model.special;

import com.google.common.base.Predicate;

public interface SpecialCharmList {
  void add(ISpecialCharm charm);

  void showViews();

  void setVisibilityPredicate(Predicate<String> predicate);
}
