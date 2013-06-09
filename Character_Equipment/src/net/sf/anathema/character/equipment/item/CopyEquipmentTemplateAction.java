package net.sf.anathema.character.equipment.item;

import net.sf.anathema.character.equipment.item.model.IEquipmentDatabaseManagement;
import net.sf.anathema.character.equipment.item.view.EquipmentNavigation;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.data.Condition;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.resources.Resources;

public class CopyEquipmentTemplateAction {
  private final IEquipmentDatabaseManagement model;
  private final Resources resources;

  public CopyEquipmentTemplateAction(Resources resources, IEquipmentDatabaseManagement model) {
    this.resources = resources;
    this.model = model;
  }

  public void addToolTo(EquipmentNavigation view) {
    final Tool copyTool = view.addEditTemplateTool();
    copyTool.setIcon(new RelativePath("icons/ButtonDuplicate24.png"));
    copyTool.setTooltip(resources.getString("Equipment.Creation.Item.CopyActionTooltip"));
    copyTool.enable();
    copyTool.setCommand(new CopyEquipmentItem(copyTool));
    model.getTemplateEditModel().getDescription().getName().addTextChangedListener(new EnableToolOnChange(copyTool));
  }

  private static class EnableToolOnChange implements ObjectValueListener<String> {
    private final Tool copyTool;

    public EnableToolOnChange(Tool copyTool) {
      this.copyTool = copyTool;
    }

    @Override
    public void valueChanged(String newValue) {
      copyTool.enable();
    }
  }

  private class CopyEquipmentItem implements Command {
    private final Tool copyTool;

    public CopyEquipmentItem(Tool copyTool) {
      this.copyTool = copyTool;
    }

    @SuppressWarnings({"RedundantStringConstructorCall", "StatementWithEmptyBody"})
    @Override
    public void execute() {
      DiscardChangesVetor vetor = new DiscardChangesVetor(resources, new Condition() {
        @Override
        public boolean isFulfilled() {
          return model.getTemplateEditModel().isDirty();
        }
      });
      if (vetor.vetos()) {
        return;
      }
      String salt;
      for (salt = new String(); model.getDatabase().loadTemplate(model.getTemplateEditModel().createTemplate().getName() + salt) != null; salt += " copy")
        ;
      model.getTemplateEditModel().copyNewTemplate(salt);
      model.getDatabase().saveTemplate(model.getTemplateEditModel().createTemplate());
      copyTool.disable();
    }
  }
}