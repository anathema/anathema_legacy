package net.sf.anathema.character.equipment.impl.character;

import net.sf.anathema.character.equipment.character.EquipmentAdditionalPresenter;
import net.sf.anathema.character.equipment.character.model.IEquipmentAdditionalModel;
import net.sf.anathema.character.equipment.character.view.IEquipmentAdditionalView;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalInitializer;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.resources.Resources;

public class EquipmentAdditionalInitializer implements IAdditionalInitializer {

  @Override
  public void initialize(IAdditionalModel model, Resources resources, ICharacterType type, Object view) {
    IEquipmentAdditionalModel equipmentModel = (IEquipmentAdditionalModel) model;
    IEquipmentAdditionalView equipmentView = (IEquipmentAdditionalView) view;
    new EquipmentAdditionalPresenter(resources, equipmentModel, equipmentView).initPresentation();
  }

  @Override
  public Class getViewClass() {
    return IEquipmentAdditionalView.class;
  }
}