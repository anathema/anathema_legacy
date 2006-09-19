package net.sf.anathema.character.equipment.item;

import java.awt.Component;

import net.disy.commons.core.message.Message;
import net.disy.commons.swing.action.SmartAction;
import net.sf.anathema.character.equipment.item.model.IEquipmentDatabaseManagement;
import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateEditModel;
import net.sf.anathema.character.equipment.template.IEquipmentTemplate;
import net.sf.anathema.framework.message.MessageUtilities;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.resources.IResources;

public final class SaveEquipmentTemplateAction extends SmartAction {
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
    super("save");
    this.model = model;
    model.getTemplateEditModel().getDescription().getName().addTextChangedListener(stringChangeListener);
    model.getTemplateEditModel().getDescription().getContent().addTextChangedListener(stringChangeListener);
    model.getTemplateEditModel().addStatsChangeListener(changeListener);
    model.getTemplateEditModel().addMagicalMaterialChangeListener(changeListener);
    updateEnabled();
  }

  private void updateEnabled() {
    setEnabled(model.getTemplateEditModel().isDirty());
  }

  @Override
  protected void execute(Component parentComponent) {
    // TODO Speichern des Templates im ProgressMonitor
    try {
      IEquipmentTemplateEditModel editModel = model.getTemplateEditModel();
      IEquipmentTemplate saveTemplate = editModel.createTemplate();
      String editTemplateId = editModel.getEditTemplateId();
      if (editTemplateId != null) {
        model.getDatabase().updateTemplate(editTemplateId, saveTemplate);
      }
      else {
        model.getDatabase().saveTemplate(saveTemplate);
      }
      editModel.setEditTemplate(saveTemplate.getName());
    }
    catch (PersistenceException e) {
      Message message = new Message("Error occured saving equipment template", e);
      MessageUtilities.indicateMessage(SaveEquipmentTemplateAction.class, parentComponent, message);
    }
  }
}