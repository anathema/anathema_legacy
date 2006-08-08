package net.sf.anathema.character.presenter.charm;

import net.sf.anathema.character.generic.framework.magic.view.IMultiLearnableCharmView;
import net.sf.anathema.character.library.trait.IModifiableTrait;
import net.sf.anathema.character.library.trait.presenter.AbstractTraitPresenter;
import net.sf.anathema.character.model.charm.special.IMultiLearnableCharmConfiguration;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.resources.IResources;

public class MultiLearnableCharmPresenter extends AbstractTraitPresenter {

  private final IMultiLearnableCharmView view;
  private final IMultiLearnableCharmConfiguration model;
  private final IResources resources;

  public MultiLearnableCharmPresenter(
      IResources resources,
      IMultiLearnableCharmView view,
      IMultiLearnableCharmConfiguration model) {
    this.resources = resources;
    this.view = view;
    this.model = model;
  }

  public void init() {
    String label = resources.getString("MultiLearnableCharm.Label"); //$NON-NLS-1$
    IModifiableTrait category = model.getCategory();
    IIntValueView display = view.addCategory(label, category.getMaximalValue(), category.getCurrentValue());
    addModelValueListener(category, display);
    addViewValueListener(display, category);
  }
}