package net.sf.anathema.framework.presenter.action;

import net.sf.anathema.lib.gui.ui.IObjectUi;
import net.sf.anathema.lib.gui.ui.ObjectUiListCellRenderer;
import net.sf.anathema.lib.message.BasicMessage;
import net.sf.anathema.lib.message.IBasicMessage;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.wizard.selection.IObjectSelectionProperties;

import javax.swing.ListCellRenderer;

public class LoadItemWizardProperties implements IObjectSelectionProperties {

  private final IResources resources;
  private final ListCellRenderer renderer;

  public LoadItemWizardProperties(IResources resources, IObjectUi<Object> ui) {
    this.resources = resources;
    this.renderer = new ObjectUiListCellRenderer(ui);
  }

  @Override
  public ListCellRenderer getCellRenderer() {
    return renderer;
  }

  @Override
  public IBasicMessage getSelectMessage() {
    return new BasicMessage(resources.getString("AnathemaPersistence.LoadAction.Message.Default")); //$NON-NLS-1$
  }

  @Override
  public String getSelectionTitle() {
    return resources.getString("AnathemaPersistence.LoadMenu.Name"); //$NON-NLS-1$
  }
}