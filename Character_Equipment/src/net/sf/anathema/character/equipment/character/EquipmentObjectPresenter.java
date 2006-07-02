package net.sf.anathema.character.equipment.character;

import net.sf.anathema.character.equipment.character.model.IEquipmentObject;
import net.sf.anathema.character.equipment.character.view.IEquipmentObjectView;
import net.sf.anathema.character.generic.equipment.weapon.IEquipment;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.resources.IResources;

public class EquipmentObjectPresenter implements IPresenter {

  private final IResources resources;
  private final IEquipmentObject model;
  private final IEquipmentObjectView view;
  private final IEquipmentStringBuilder stringBuilder;

  public EquipmentObjectPresenter(IResources resources, IEquipmentObject model, IEquipmentObjectView view, IEquipmentStringBuilder stringBuilder) {
    this.resources = resources;
    this.model = model;
    this.view = view;
    this.stringBuilder = stringBuilder;
  }

  public void initPresentation() {
    view.setItemTitle(model.getName());
    String description = model.getDescription();
    if (description != null) {
      view.setItemDescription(description);
    }
    for (IEquipment equipment : model.getEquipments()) {
      view.addStats(createEquipmentDescription(equipment));
    }
  }

  private String createEquipmentDescription(IEquipment equipment) {
    return stringBuilder.createString(equipment);
  }
}