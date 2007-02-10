package net.sf.anathema.character.presenter.charm;

import net.sf.anathema.character.library.trait.presenter.TraitPresenter;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.character.model.charm.special.IMultiLearnableCharmConfiguration;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.platform.svgtree.presenter.view.ISVGCategorizedSpecialNodeView;

public class MultiLearnableCharmPresenter implements IPresenter {

  private final ISVGCategorizedSpecialNodeView view;
  private final IMultiLearnableCharmConfiguration model;
  private final IResources resources;

  public MultiLearnableCharmPresenter(
      IResources resources,
      ISVGCategorizedSpecialNodeView view,
      IMultiLearnableCharmConfiguration model) {
    this.resources = resources;
    this.view = view;
    this.model = model;
  }

  public void initPresentation() {
    String label = resources.getString("MultiLearnableCharm.Label"); //$NON-NLS-1$
    IDefaultTrait category = model.getCategory();
    IIntValueView display = view.addCategory(label, category.getMaximalValue(), category.getCurrentValue());
    new TraitPresenter(category, display).initPresentation();
  }
}