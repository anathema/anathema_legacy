package net.sf.anathema.character.equipment.impl.character;

import net.sf.anathema.character.equipment.character.EquipmentAdditionalPresenter;
import net.sf.anathema.character.equipment.character.model.IEquipmentAdditionalModel;
import net.sf.anathema.character.equipment.character.view.IEquipmentAdditionalView;
import net.sf.anathema.character.equipment.impl.character.view.EquipmentAdditionalView;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.lib.resources.Resources;

public class EquipmentAdditionalViewFactory implements IAdditionalViewFactory {

  @Override
  public void createView(IAdditionalModel model, Resources resources, ICharacterType type, Object view) {
    IEquipmentAdditionalModel equipmentModel = (IEquipmentAdditionalModel) model;
    IEquipmentAdditionalView equipmentView = (IEquipmentAdditionalView) view;
    new EquipmentAdditionalPresenter(resources, equipmentModel, equipmentView).initPresentation();
  }

  @Override
  public IView createView(IAdditionalModel model, Resources resources, ICharacterType characterType) {
    EquipmentAdditionalView equipmentView = new EquipmentAdditionalView();
    IEquipmentAdditionalModel equipmentModel = (IEquipmentAdditionalModel) model;
    new EquipmentAdditionalPresenter(resources, equipmentModel, equipmentView).initPresentation();
    return equipmentView;
  }

  @Override
  public Class getViewClass() {
    return IEquipmentAdditionalView.class;
  }
}