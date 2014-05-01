package net.sf.anathema.character.equipment.item;

import net.sf.anathema.character.equipment.item.model.IEquipmentDatabaseManagement;
import net.sf.anathema.character.equipment.item.view.EquipmentNavigation;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.file.RelativePath;

public class CopyEquipmentTemplateAction {
  private final IEquipmentDatabaseManagement model;
  private StatsEditModel editModel;
  private final Resources resources;

  public CopyEquipmentTemplateAction(Resources resources, IEquipmentDatabaseManagement model, StatsEditModel editModel) {
    this.resources = resources;
    this.model = model;
    this.editModel = editModel;
  }

  public void addToolTo(EquipmentNavigation view) {
    final Tool copyTool = view.addEditTemplateTool();
    copyTool.setIcon(new RelativePath("icons/ButtonDuplicate24.png"));
    copyTool.setTooltip(resources.getString("Equipment.Creation.Item.CopyActionTooltip"));
    copyTool.enable();
    copyTool.setCommand(new CopyEquipmentItem(copyTool, view, editModel));
    model.getTemplateEditModel().getDescription().getName().addTextChangedListener(new EnableToolOnChange(copyTool, model));
  }

  private static class EnableToolOnChange implements ObjectValueListener<String> {
    private final Tool copyTool;
    private IEquipmentDatabaseManagement model;

    public EnableToolOnChange(Tool copyTool, IEquipmentDatabaseManagement model) {
      this.copyTool = copyTool;
      this.model = model;
    }

    @Override
    public void valueChanged(String newValue) {
      if (!model.getTemplateEditModel().getDescription().getName().isEmpty()
              && !model.getTemplateEditModel().isDirty()) {
        copyTool.enable();
      } else {
        copyTool.disable();
      }
    }
  }

  private class CopyEquipmentItem implements Command {
    private final Tool copyTool;
    private EquipmentNavigation view;
    private StatsEditModel editModel;

    public CopyEquipmentItem(Tool copyTool, EquipmentNavigation view, StatsEditModel editModel) {
      this.copyTool = copyTool;
      this.view = view;
      this.editModel = editModel;
    }

    @SuppressWarnings({"RedundantStringConstructorCall", "StatementWithEmptyBody"})
    @Override
    public void execute() {
      DiscardChangesVetor vetor = new DiscardChangesVetor(model, view, resources);
      vetor.requestPermissionFor(new Command() {
        @Override
        public void execute() {
          String salt;
          for (salt = new String(); model.getDatabase().loadTemplate(model.getTemplateEditModel().createTemplate().getName() + salt) != null; salt += " copy")
            ;
          model.getTemplateEditModel().copyNewTemplate(salt);
          model.getDatabase().saveTemplate(model.getTemplateEditModel().createTemplate());
          editModel.clearStatsSelection();
          copyTool.disable();
        }
      });
    }
  }
}