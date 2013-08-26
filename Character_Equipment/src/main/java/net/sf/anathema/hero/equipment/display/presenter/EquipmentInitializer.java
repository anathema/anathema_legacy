package net.sf.anathema.hero.equipment.display.presenter;

import net.sf.anathema.character.main.view.SectionView;
import net.sf.anathema.framework.model.ApplicationModel;
import net.sf.anathema.hero.display.HeroModelGroup;
import net.sf.anathema.hero.display.presenter.HeroModelInitializer;
import net.sf.anathema.hero.display.presenter.RegisteredInitializer;
import net.sf.anathema.hero.equipment.EquipmentModel;
import net.sf.anathema.hero.equipment.EquipmentModelFetcher;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.Resources;

@RegisteredInitializer(HeroModelGroup.Miscellaneous)
@Weight(weight = 200)
public class EquipmentInitializer implements HeroModelInitializer {
  @SuppressWarnings("UnusedParameters")
  public EquipmentInitializer(ApplicationModel model) {
    //nothing to do
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero, Resources resources) {
    String viewName = resources.getString("AdditionalTemplateView.TabName.Equipment");
    EquipmentView view = sectionView.addView(viewName, EquipmentView.class);
    EquipmentModel equipmentModel = EquipmentModelFetcher.fetch(hero);
    new EquipmentPresenter(resources, equipmentModel, view).initPresentation();
  }
}
