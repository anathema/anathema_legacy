package net.sf.anathema.character.equipment.item;

import java.awt.Component;

import net.disy.commons.swing.action.SmartAction;
import net.sf.anathema.character.equipment.item.model.IEquipmentDatabaseManagement;
import net.sf.anathema.character.equipment.item.view.IEquipmentDatabaseView;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.resources.IResources;

public class RemoveEquipmentTemplateAction extends SmartAction {
  private final IEquipmentDatabaseManagement model;
  private final IEquipmentDatabaseView view;
  private final IResources resources;

  public RemoveEquipmentTemplateAction(
      IResources resources,
      IEquipmentDatabaseManagement model,
      IEquipmentDatabaseView view) {
    super(new BasicUi(resources).getClearIcon());
    this.resources = resources;
    this.model = model;
    this.view = view;
    view.getTemplateListView().addObjectSelectionChangedListener(new IObjectValueChangedListener<String>() {
      public void valueChanged(String newValue) {
        updateEnabled(newValue);
      }
    });
    updateEnabled(view.getTemplateListView().getSelectedObject());
  }

  private void updateEnabled(String string) {
    setEnabled(string != null);
  }

  @Override
  protected void execute(Component parentComponent) {
    DeleteItemsVetor vetor = new DeleteItemsVetor(parentComponent, resources);
    if (vetor.vetos()) {
      return;
    }
    model.getDatabase().deleteTemplate(view.getTemplateListView().getSelectedObject());
    model.getTemplateEditModel().setNewTemplate();
  }
}