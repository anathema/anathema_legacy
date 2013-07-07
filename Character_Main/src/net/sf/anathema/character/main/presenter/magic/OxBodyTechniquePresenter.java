package net.sf.anathema.character.main.presenter.magic;

import net.sf.anathema.character.main.library.trait.presenter.TraitPresenter;
import net.sf.anathema.character.main.magic.charms.OxBodyCategory;
import net.sf.anathema.character.main.magic.charms.special.IOxBodyTechniqueConfiguration;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.platform.tree.presenter.view.CategorizedSpecialNodeView;

public class OxBodyTechniquePresenter implements Presenter {

  private final CategorizedSpecialNodeView view;
  private final IOxBodyTechniqueConfiguration model;
  private final Resources resources;

  public OxBodyTechniquePresenter(Resources resources, CategorizedSpecialNodeView view, IOxBodyTechniqueConfiguration model) {
    this.resources = resources;
    this.view = view;
    this.model = model;
  }

  @Override
  public void initPresentation() {
    for (OxBodyCategory category : model.getCategories()) {
      String label = resources.getString("OxBodyTechnique." + category.getId());
      IIntValueView display = view.addCategory(label, category.getMaximalValue(), category.getCurrentValue());
      new TraitPresenter(category, display).initPresentation();
    }
  }
}
