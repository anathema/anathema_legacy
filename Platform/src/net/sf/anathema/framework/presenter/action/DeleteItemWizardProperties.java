package net.sf.anathema.framework.presenter.action;

import javax.swing.ListCellRenderer;

import net.disy.commons.core.message.BasicMessage;
import net.disy.commons.core.message.IBasicMessage;
import net.disy.commons.swing.ui.IObjectUi;
import net.disy.commons.swing.ui.ObjectUiListCellRenderer;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.wizard.selection.IObjectSelectionProperties;

public class DeleteItemWizardProperties implements IObjectSelectionProperties {

  private final IResources resources;
  private final ListCellRenderer renderer;

  public DeleteItemWizardProperties(IResources resources, IObjectUi objectUi) {
    this.resources = resources;
    this.renderer = new ObjectUiListCellRenderer(objectUi);
  }

  public ListCellRenderer getCellRenderer() {
    return renderer;
  }

  public IBasicMessage getSelectMessage() {
    return new BasicMessage(resources.getString("AnathemaPersistence.DeleteAction.Message.Default")); //$NON-NLS-1$
  }

  public String getSelectionTitle() {
    return resources.getString("AnathemaPersistence.DeleteMenu.Name"); //$NON-NLS-1$
  }
}