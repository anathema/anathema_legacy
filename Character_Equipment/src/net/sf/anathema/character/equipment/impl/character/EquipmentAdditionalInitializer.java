package net.sf.anathema.character.equipment.impl.character;

import net.sf.anathema.character.equipment.character.EquipmentAdditionalPresenter;
import net.sf.anathema.character.equipment.character.model.IEquipmentAdditionalModel;
import net.sf.anathema.character.equipment.character.view.IEquipmentAdditionalView;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalInitializer;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.view.SectionView;
import net.sf.anathema.lib.resources.Resources;

public class EquipmentAdditionalInitializer implements IAdditionalInitializer {

  @Override
  public void initialize(SectionView sectionView, ICharacter character, Resources resources, IAdditionalModel model) {
    String viewName = resources.getString("AdditionalTemplateView.TabName." + model.getTemplateId());
    IEquipmentAdditionalView view = sectionView.addView(viewName, IEquipmentAdditionalView.class, character.getCharacterType());
    IEquipmentAdditionalModel equipmentModel = (IEquipmentAdditionalModel) model;
    new EquipmentAdditionalPresenter(resources, equipmentModel, view).initPresentation();
  }
}