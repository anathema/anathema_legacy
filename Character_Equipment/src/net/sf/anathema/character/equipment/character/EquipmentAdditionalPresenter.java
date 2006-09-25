package net.sf.anathema.character.equipment.character;

import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;

import net.disy.commons.swing.action.SmartAction;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.equipment.character.model.IEquipmentItemCollection;
import net.sf.anathema.character.equipment.character.view.IEquipmentAdditionalView;
import net.sf.anathema.character.equipment.character.view.IEquipmentObjectView;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.lib.control.collection.ICollectionListener;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.gui.selection.IListObjectSelectionView;
import net.sf.anathema.lib.resources.IResources;

public class EquipmentAdditionalPresenter implements IPresenter {

  private final IResources resources;
  private final IEquipmentItemCollection model;
  private final IEquipmentAdditionalView view;
  private final Map<IEquipmentItem, IEquipmentObjectView> viewsByItem = new HashMap<IEquipmentItem, IEquipmentObjectView>();

  public EquipmentAdditionalPresenter(
      IResources resources,
      IEquipmentItemCollection model,
      IEquipmentAdditionalView view) {
    this.resources = resources;
    this.model = model;
    this.view = view;
  }

  public void initPresentation() {
    for (IEquipmentItem item : model.getEquipmentItems()) {
      initEquipmentObjectPresentation(item);
    }
    final IListObjectSelectionView<String> equipmentTemplatePickList = view.getEquipmentTemplatePickList();
    model.addEquipmentObjectListener(new ICollectionListener<IEquipmentItem>() {
      public void itemAdded(IEquipmentItem item) {
        initEquipmentObjectPresentation(item);
      }

      public void itemRemoved(IEquipmentItem item) {
        IEquipmentObjectView objectView = viewsByItem.remove(item);
        view.removeEquipmentObjectView(objectView);
      }
    });
    equipmentTemplatePickList.setCellRenderer(new EquipmentObjectCellRenderer());
    equipmentTemplatePickList.setObjects(model.getAvailableTemplateIds());
    final SmartAction addAction = createTemplateAddAction(equipmentTemplatePickList);
    view.setSelectButtonAction(addAction);
  }

  private SmartAction createTemplateAddAction(final IListObjectSelectionView<String> equipmentTemplatePickList) {
    final SmartAction addAction = new SmartAction(resources.getString("AdditionalTemplateView.AddTemplate.Action.Name")) {
      @Override
      protected void execute(Component parentComponent) {
        model.addEquipmentObjectFor(equipmentTemplatePickList.getSelectedObject());
      }
    };
    equipmentTemplatePickList.addObjectSelectionChangedListener(new IObjectValueChangedListener<String>() {
      public void valueChanged(String newValue) {
        addAction.setEnabled(equipmentTemplatePickList.isObjectSelected());
      }
    });
    addAction.setEnabled(equipmentTemplatePickList.isObjectSelected());
    return addAction;
  }

  private void initEquipmentObjectPresentation(final IEquipmentItem selectedObject) {
    IEquipmentObjectView objectView = view.addEquipmentObjectView();
    IEquipmentStringBuilder resourceBuilder = new EquipmentStringBuilder(resources);
    Icon removeIcon = new BasicUi(resources).getRemoveIcon();
    viewsByItem.put(selectedObject, objectView);
    new EquipmentObjectPresenter(selectedObject, objectView, resourceBuilder).initPresentation();
    objectView.addAction(new SmartAction(resources.getString("EquipmentObject.Remove"), removeIcon) {
      @Override
      protected void execute(Component parentComponent) {
        model.removeItem(selectedObject);
      }
    });
  }
}