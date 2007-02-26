package net.sf.anathema.character.equipment.item;

import java.awt.Component;

import net.disy.commons.swing.action.SmartAction;
import net.sf.anathema.character.equipment.item.model.IEquipmentDatabaseManagement;
import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateEditModel;
import net.sf.anathema.character.equipment.template.IEquipmentTemplate;
import net.sf.anathema.framework.presenter.resources.PlatformUI;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.resources.IResources;

public final class SaveEquipmentTemplateAction extends SmartAction {
  private final IResources resources;
  private final IEquipmentDatabaseManagement model;
  private final IChangeListener changeListener = new IChangeListener() {
    public void changeOccured() {
      updateEnabled();
    }
  };
  private final IObjectValueChangedListener<String> stringChangeListener = new IObjectValueChangedListener<String>() {
    public void valueChanged(String newValue) {
      updateEnabled();
    }
  };

  public SaveEquipmentTemplateAction(IResources resources, IEquipmentDatabaseManagement model) {
    super(new PlatformUI(resources).getSaveIcon());
    this.resources = resources;
    this.model = model;
    model.getTemplateEditModel().getDescription().getName().addTextChangedListener(stringChangeListener);
    model.getTemplateEditModel().getDescription().getContent().addTextChangedListener(stringChangeListener);
    model.getTemplateEditModel().addStatsChangeListener(changeListener);
    model.getTemplateEditModel().addCompositionChangeListener(changeListener);
    model.getTemplateEditModel().addMagicalMaterialChangeListener(changeListener);
    updateEnabled();
  }

  private void updateEnabled() {
    setEnabled(!model.getTemplateEditModel().getDescription().getName().isEmpty()
        && model.getTemplateEditModel().isDirty());
  }

  @Override
  protected void execute(Component parentComponent) {
    IEquipmentTemplateEditModel editModel = model.getTemplateEditModel();
    IEquipmentTemplate saveTemplate = editModel.createTemplate();
    String editTemplateId = editModel.getEditTemplateId();
    if (!saveTemplate.getName().equals(editTemplateId)) {
      IEquipmentTemplate existingTemplate = model.getDatabase().loadTemplate(saveTemplate.getName());
      if (existingTemplate != null) {
        if (new OverwriteItemsVetor(parentComponent, resources).vetos()) {
          return;
        }
        model.getDatabase().deleteTemplate(existingTemplate.getName());
      }
    }
    if (editTemplateId != null) {
      model.getDatabase().updateTemplate(editTemplateId, saveTemplate);
    }
    else {
      model.getDatabase().saveTemplate(saveTemplate);
    }
    editModel.setEditTemplate(saveTemplate.getName());
  }
}