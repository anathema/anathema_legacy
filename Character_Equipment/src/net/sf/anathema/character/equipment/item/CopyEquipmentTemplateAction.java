package net.sf.anathema.character.equipment.item;

import net.disy.commons.swing.action.SmartAction;
import net.sf.anathema.character.equipment.item.model.IEquipmentDatabaseManagement;
import net.sf.anathema.framework.presenter.resources.FileUi;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.wizard.workflow.ICondition;
import net.sf.anathema.lib.resources.IResources;

import java.awt.Component;

public final class CopyEquipmentTemplateAction extends SmartAction {
  private static final long serialVersionUID = -2131791434827503585L;
  private final IEquipmentDatabaseManagement model;
  private final IResources resources;

  public CopyEquipmentTemplateAction(IResources resources, IEquipmentDatabaseManagement model) {
    super(new FileUi(resources).getDuplicateFileIcon());
    this.resources = resources;
    this.model = model;
    model.getTemplateEditModel().getDescription().getName().addTextChangedListener(new IObjectValueChangedListener<String>() {
      @Override
      public void valueChanged(String newValue) {
        setEnabled(true);
      }
    });
    setEnabled(true);
    setToolTipText(resources.getString("Equipment.Creation.Item.CopyActionTooltip")); //$NON-NLS-1$  
  }

  @Override
  protected void execute(Component parentComponent) {
    
    DiscardChangesVetor vetor = new DiscardChangesVetor(resources, new ICondition() {
      @Override
      public boolean isFulfilled() {
        return model.getTemplateEditModel().isDirty();
      }
    }, parentComponent);
    if (vetor.vetos()) {
        return;
    }
    
    String salt;
    for( salt = new String(); model.getDatabase().loadTemplate( model.getTemplateEditModel().createTemplate().getName() + salt ) != null; salt += " copy" );

    model.getTemplateEditModel().copyNewTemplate( salt );
    model.getDatabase().saveTemplate(model.getTemplateEditModel().createTemplate());
    setEnabled(false);
  }
  
}