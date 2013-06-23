package net.sf.anathema.character.equipment.impl.character;

import net.sf.anathema.character.equipment.IEquipmentAdditionalModelTemplate;
import net.sf.anathema.character.equipment.character.EquipmentAdditionalPresenter;
import net.sf.anathema.character.equipment.character.model.IEquipmentAdditionalModel;
import net.sf.anathema.character.equipment.character.view.IEquipmentAdditionalView;
import net.sf.anathema.hero.display.HeroModelGroup;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.presenter.initializers.CharacterModelInitializer;
import net.sf.anathema.character.presenter.initializers.RegisteredInitializer;
import net.sf.anathema.character.view.SectionView;
import net.sf.anathema.framework.model.ApplicationModel;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.Resources;

@RegisteredInitializer(HeroModelGroup.Miscellaneous)
@Weight(weight = 200)
public class EquipmentAdditionalInitializer implements CharacterModelInitializer {
  @SuppressWarnings("UnusedParameters")
  public EquipmentAdditionalInitializer(ApplicationModel model) {
    //nothing to do
  }

  @Override
  public void initialize(SectionView sectionView, ICharacter character, Resources resources) {
    String viewName = resources.getString("AdditionalTemplateView.TabName.Equipment");
    IEquipmentAdditionalView view = sectionView.addView(viewName, IEquipmentAdditionalView.class, character.getTemplate().getTemplateType().getCharacterType());
    IEquipmentAdditionalModel equipmentModel = (IEquipmentAdditionalModel) character.getExtendedConfiguration().getAdditionalModel(IEquipmentAdditionalModelTemplate.ID);
    new EquipmentAdditionalPresenter(resources, equipmentModel, view).initPresentation();
  }
}
