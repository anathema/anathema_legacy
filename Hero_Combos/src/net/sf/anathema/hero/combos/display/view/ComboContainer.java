package net.sf.anathema.hero.combos.display.view;

import net.sf.anathema.hero.combos.display.presenter.ComboView;

public interface ComboContainer {

  ComboView addView(String name, String description);
}