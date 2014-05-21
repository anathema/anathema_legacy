package net.sf.anathema.hero.charms.display.special;

import net.sf.anathema.hero.traits.display.TraitPresenter;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.framework.value.IntValueView;
import net.sf.anathema.hero.charms.model.special.oxbody.OxBodyCategory;
import net.sf.anathema.hero.charms.model.special.oxbody.OxBodyTechniqueSpecials;
import net.sf.anathema.platform.tree.display.CategorizedSpecialNodeView;

public class OxBodyTechniquePresenter {

  private final CategorizedSpecialNodeView view;
  private final OxBodyTechniqueSpecials model;
  private final Resources resources;

  public OxBodyTechniquePresenter(Resources resources, CategorizedSpecialNodeView view, OxBodyTechniqueSpecials model) {
    this.resources = resources;
    this.view = view;
    this.model = model;
  }

  public void initPresentation() {
    for (OxBodyCategory category : model.getCategories()) {
      String label = resources.getString("OxBodyTechnique." + category.getId());
      IntValueView display = view.addCategory(label, category.getMaximalValue(), category.getCurrentValue());
      new TraitPresenter(category, display).initPresentation();
    }
  }
}
