package net.sf.anathema.hero.equipment;

import net.sf.anathema.character.equipment.character.EquipmentPresenter;
import net.sf.anathema.character.equipment.character.view.EquipmentView;
import net.sf.anathema.character.main.type.ICharacterType;
import net.sf.anathema.character.main.presenter.initializers.HeroModelInitializer;
import net.sf.anathema.character.main.presenter.initializers.RegisteredInitializer;
import net.sf.anathema.character.main.view.SectionView;
import net.sf.anathema.framework.model.ApplicationModel;
import net.sf.anathema.hero.display.HeroModelGroup;
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
    ICharacterType characterType = hero.getTemplate().getTemplateType().getCharacterType();
    EquipmentView view = sectionView.addView(viewName, EquipmentView.class, characterType);
    EquipmentModel equipmentModel = hero.getModel(EquipmentModel.ID);
    new EquipmentPresenter(resources, equipmentModel, view).initPresentation();
  }
}
