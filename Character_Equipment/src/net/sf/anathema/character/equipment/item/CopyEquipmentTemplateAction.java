package net.sf.anathema.character.equipment.item;

import java.awt.Component;

import net.disy.commons.swing.action.SmartAction;
import net.sf.anathema.character.equipment.item.model.IEquipmentDatabaseManagement;
import net.sf.anathema.character.equipment.item.view.IEquipmentDatabaseView;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.framework.presenter.resources.PlatformUI;
import net.sf.anathema.lib.gui.wizard.workflow.ICondition;
import net.sf.anathema.lib.resources.IResources;

public final class CopyEquipmentTemplateAction extends SmartAction {
  private static final long serialVersionUID = -2131791434827503585L;
  private final IEquipmentDatabaseManagement model;
  private final IResources resources;

  public CopyEquipmentTemplateAction(IResources resources, IEquipmentDatabaseManagement model) {
    super(new PlatformUI(resources).getCopyIcon());
    this.resources = resources;
    this.model = model;
    model.getTemplateEditModel().getDescription().getName().addTextChangedListener(new IObjectValueChangedListener<String>() {
      public void valueChanged(String newValue) {
        setEnabled(true);
      }
    });
    setEnabled(true);
    setToolTipText(resources.getString("Equipment.Creation.Item.CopyActionTooltip")); //$NON-NLS-1$
  }

  @Override
  protected void execute(Component parentComponent) {
    model.getTemplateEditModel().copyNewTemplate();
    setEnabled(false);
  }
  
}