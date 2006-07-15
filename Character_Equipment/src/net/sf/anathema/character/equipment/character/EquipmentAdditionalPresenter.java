package net.sf.anathema.character.equipment.character;

import net.sf.anathema.character.equipment.character.model.IEquipmentObject;
import net.sf.anathema.character.equipment.character.model.IEquipmentObjectCollection;
import net.sf.anathema.character.equipment.character.view.IEquipmentAdditionalView;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.gui.selection.IListObjectSelectionView;
import net.sf.anathema.lib.resources.IResources;

public class EquipmentAdditionalPresenter implements IPresenter {

  private final IResources resources;
  private final IEquipmentObjectCollection model;
  private final IEquipmentAdditionalView view;

  public EquipmentAdditionalPresenter(
      IResources resources,
      IEquipmentObjectCollection model,
      IEquipmentAdditionalView view) {
    this.resources = resources;
    this.model = model;
    this.view = view;
  }

  public void initPresentation() {
    IListObjectSelectionView<IEquipmentObject> equipmentPickList = view.getEquipmentObjectPickList();
    equipmentPickList.setCellRenderer(new EquipmentObjectCellRenderer());
    equipmentPickList.setObjects(model.getAvailableObjects());
  }
}