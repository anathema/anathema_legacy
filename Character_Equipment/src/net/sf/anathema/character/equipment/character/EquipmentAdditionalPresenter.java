package net.sf.anathema.character.equipment.character;

import java.awt.Component;

import net.disy.commons.swing.action.SmartAction;
import net.sf.anathema.character.equipment.character.model.IEquipmentObject;
import net.sf.anathema.character.equipment.character.model.IEquipmentObjectCollection;
import net.sf.anathema.character.equipment.character.view.IEquipmentAdditionalView;
import net.sf.anathema.character.equipment.character.view.IEquipmentObjectView;
import net.sf.anathema.lib.control.collection.CollectionAdapter;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
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
    final IListObjectSelectionView<IEquipmentObject> equipmentPickList = view.getEquipmentObjectPickList();
    equipmentPickList.setCellRenderer(new EquipmentObjectCellRenderer());
    equipmentPickList.setObjects(model.getAvailableObjects());
    final SmartAction addAction = new SmartAction("Add") {
      @Override
      protected void execute(Component parentComponent) {
        IEquipmentObject selectedObject = equipmentPickList.getSelectedObject();
        model.addEquipmentObject(selectedObject);
      }
    };
    equipmentPickList.addObjectSelectionChangedListener(new IObjectValueChangedListener<IEquipmentObject>() {
      public void valueChanged(IEquipmentObject newValue) {
        addAction.setEnabled(view.getEquipmentObjectPickList().isObjectSelected());
      }
    });
    model.addEquipmentObjectListener(new CollectionAdapter<IEquipmentObject>() {
      @Override
      public void itemAdded(IEquipmentObject item) {
        initEquipmentObjectPresentation(item);
      }
    });
    addAction.setEnabled(view.getEquipmentObjectPickList().isObjectSelected());
    view.setSelectButtonAction(addAction);
  }

  private void initEquipmentObjectPresentation(IEquipmentObject selectedObject) {
    IEquipmentObjectView objectView = view.addEquipmentObjectView();
    IEquipmentStringBuilder resourceBuilder = new EquipmentStringBuilder(resources);
    new EquipmentObjectPresenter(selectedObject, objectView, resourceBuilder).initPresentation();
  }
}