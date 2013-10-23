package net.sf.anathema.hero.charms.display.special;

import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.library.trait.presenter.TraitPresenter;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.framework.value.IntValueView;
import net.sf.anathema.hero.charms.model.special.multilearn.MultiLearnCharmSpecials;
import net.sf.anathema.platform.tree.display.CategorizedSpecialNodeView;

public class MultiLearnableCharmPresenter {

  private final CategorizedSpecialNodeView view;
  private final MultiLearnCharmSpecials model;
  private final Resources resources;

  public MultiLearnableCharmPresenter(Resources resources, CategorizedSpecialNodeView view, MultiLearnCharmSpecials model) {
    this.resources = resources;
    this.view = view;
    this.model = model;
  }

  public void initPresentation() {
    String label = resources.getString("MultiLearnableCharm.Label");
    Trait category = model.getCategory();
    IntValueView display = view.addCategory(label, category.getMaximalValue(), category.getCurrentValue());
    new TraitPresenter(category, display).initPresentation();
  }
}