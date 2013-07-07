package net.sf.anathema.character.main.presenter.magic;

import com.google.common.base.Predicate;
import net.sf.anathema.character.main.magic.model.charm.special.ISpecialCharm;

public interface SpecialCharmList {
  void add(ISpecialCharm charm);

  void showViews();

  void setVisibilityPredicate(Predicate<String> predicate);
}
