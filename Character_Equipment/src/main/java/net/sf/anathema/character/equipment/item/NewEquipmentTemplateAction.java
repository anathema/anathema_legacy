package net.sf.anathema.character.equipment.item;

import net.sf.anathema.character.equipment.item.model.IEquipmentDatabaseManagement;
import net.sf.anathema.character.equipment.item.view.EquipmentNavigation;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.interaction.Tool;

public class NewEquipmentTemplateAction {

  private final Resources resources;
  private final IEquipmentDatabaseManagement model;
  private StatsEditModel editModel;

  public NewEquipmentTemplateAction(Resources resources, IEquipmentDatabaseManagement model, StatsEditModel editModel) {
    this.resources = resources;
    this.model = model;
    this.editModel = editModel;
  }

  public void addToolTo(EquipmentNavigation view) {
    Tool newTool = view.addEditTemplateTool();
    newTool.setIcon(new BasicUi().getNewIconPathForTaskbar());
    newTool.setTooltip(resources.getString("Equipment.Creation.Item.NewActionTooltip"));
    newTool.setCommand(new NewEquipmentItem(model, view, resources, editModel));
  }
}