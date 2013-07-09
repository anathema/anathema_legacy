package net.sf.anathema.hero.charms.display.special;

import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.library.trait.presenter.TraitPresenter;
import net.sf.anathema.character.main.magic.model.charm.special.IMultiLearnableCharmConfiguration;
import net.sf.anathema.framework.value.IntValueView;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.platform.tree.presenter.view.CategorizedSpecialNodeView;

public class MultiLearnableCharmPresenter implements Presenter {

  private final CategorizedSpecialNodeView view;
  private final IMultiLearnableCharmConfiguration model;
  private final Resources resources;

  public MultiLearnableCharmPresenter(Resources resources, CategorizedSpecialNodeView view, IMultiLearnableCharmConfiguration model) {
    this.resources = resources;
    this.view = view;
    this.model = model;
  }

  @Override
  public void initPresentation() {
    String label = resources.getString("MultiLearnableCharm.Label");
    Trait category = model.getCategory();
    IntValueView display = view.addCategory(label, category.getMaximalValue(), category.getCurrentValue());
    new TraitPresenter(category, display).initPresentation();
  }
}