package net.sf.anathema.character.equipment.impl.character;

import net.sf.anathema.character.equipment.character.EquipmentAdditionalPresenter;
import net.sf.anathema.character.equipment.character.model.IEquipmentAdditionalModel;
import net.sf.anathema.character.equipment.impl.character.view.EquipmentAdditionalView;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.resources.IResources;

public class EquipmentAdditionalViewFactory implements IAdditionalViewFactory {

  public IView createView(IAdditionalModel model, IResources resources, ICharacterType type) {
    IEquipmentAdditionalModel equipmentModel = (IEquipmentAdditionalModel) model;
    EquipmentAdditionalView view = new EquipmentAdditionalView();
    new EquipmentAdditionalPresenter(resources, equipmentModel, view).initPresentation();
    return view;
  }
}