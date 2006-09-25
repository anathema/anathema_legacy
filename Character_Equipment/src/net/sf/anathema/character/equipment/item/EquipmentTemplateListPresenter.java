package net.sf.anathema.character.equipment.item;

import net.sf.anathema.character.equipment.item.model.IEquipmentDatabaseManagement;
import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateEditModel;
import net.sf.anathema.character.equipment.item.view.IEquipmentDatabaseView;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.gui.wizard.workflow.ICondition;
import net.sf.anathema.lib.resources.IResources;

public class EquipmentTemplateListPresenter implements IPresenter {

  private final class EquipmentTemplateLoadListener implements IObjectValueChangedListener<String> {
    public void valueChanged(String newValue) {
      if (newValue == null) {
        return;
      }
      IEquipmentTemplateEditModel editModel = model.getTemplateEditModel();
      editModel.setEditTemplate(newValue);
    }
  }

  private final IResources resources;
  private final IEquipmentDatabaseView view;
  private final IEquipmentDatabaseManagement model;

  public EquipmentTemplateListPresenter(
      IResources resources,
      IEquipmentDatabaseManagement model,
      IEquipmentDatabaseView view) {
    this.resources = resources;
    this.model = model;
    this.view = view;
  }

  public void initPresentation() {
    view.setTemplateListHeader("Available Templates");
    model.getDatabase().addAvailableTemplateChangeListener(new IChangeListener() {
      public void changeOccured() {
        updateAvailableTemplates();
      }
    });
    updateAvailableTemplates();
    view.getTemplateListView().addSelectionVetor(new DiscardChangesVetor(new ICondition() {
      public boolean isFullfilled() {
        final IEquipmentTemplateEditModel editModel = model.getTemplateEditModel();
        return editModel.isDirty();
      }
    }, view.getTemplateListView().getComponent()));
    view.getTemplateListView().addObjectSelectionChangedListener(new EquipmentTemplateLoadListener());
  }

  private void updateAvailableTemplates() {
    view.getTemplateListView().setObjects(model.getDatabase().getAllAvailableTemplateIds());
  }
}