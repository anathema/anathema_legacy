package net.sf.anathema.character.equipment.impl.character;

import net.sf.anathema.character.equipment.character.EquipmentAdditionalPresenter;
import net.sf.anathema.character.equipment.character.model.IEquipmentAdditionalModel;
import net.sf.anathema.character.equipment.character.view.IEquipmentAdditionalView;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalInitializer;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.view.SectionView;
import net.sf.anathema.lib.resources.Resources;

public class EquipmentAdditionalInitializer implements IAdditionalInitializer {

  @Override
  public void initialize(IAdditionalModel model, Resources resources, ICharacterType type, SectionView sectionView) {
    String viewName = resources.getString("AdditionalTemplateView.TabName." + model.getTemplateId());
    IEquipmentAdditionalView view = sectionView.addView(viewName, IEquipmentAdditionalView.class, type);
    IEquipmentAdditionalModel equipmentModel = (IEquipmentAdditionalModel) model;
    new EquipmentAdditionalPresenter(resources, equipmentModel, view).initPresentation();
  }
}