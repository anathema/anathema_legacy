package net.sf.anathema.character.equipment.impl.item.model;

import net.disy.commons.core.util.ObjectUtilities;
import net.sf.anathema.character.equipment.item.model.IEquipmentDatabase;
import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateEditModel;
import net.sf.anathema.character.equipment.template.IEquipmentTemplate;
import net.sf.anathema.framework.itemdata.model.IItemDescription;
import net.sf.anathema.framework.itemdata.model.ItemDescription;
import net.sf.anathema.framework.styledtext.model.ITextPart;

public class EquipmentTemplateEditModel implements IEquipmentTemplateEditModel {

  private final IItemDescription description = new ItemDescription();
  private final IEquipmentDatabase database;
  private IEquipmentTemplate editedTemplate;

  public EquipmentTemplateEditModel(IEquipmentDatabase database) {
    this.database = database;
  }

  public IItemDescription getDescription() {
    return description;
  }

  public void setEditTemplate(String templateId) {
    editedTemplate = templateId == null ? null : database.loadTemplate(templateId);
    if (editedTemplate != null) {
      getDescription().getName().setText(editedTemplate.getName());
      getDescription().getContent().setText(editedTemplate.getDescription());
    }
    else {
      getDescription().getName().setText(null);
      getDescription().getContent().setText(new ITextPart[0]);
    }
  }

  public boolean isDirty() {
    if (editedTemplate == null) {
      return !getDescription().getName().isEmpty() || !getDescription().getContent().isEmpty();
    }
    return !ObjectUtilities.equals(editedTemplate.getName(), getDescription().getName().getText())
        && !ObjectUtilities.equals(editedTemplate.getDescription(), getDescription().getContent().getText());
  }
}