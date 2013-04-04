package net.sf.anathema.campaign.perspective;

import net.sf.anathema.lib.gui.ui.ObjectUi;
import net.sf.anathema.lib.gui.ui.ObjectUiListCellRenderer;
import net.sf.anathema.lib.message.BasicMessage;
import net.sf.anathema.lib.message.IBasicMessage;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.campaign.load.selection.IObjectSelectionProperties;

import javax.swing.ListCellRenderer;

public class LoadItemWizardProperties implements IObjectSelectionProperties {

  private final Resources resources;
  private final ListCellRenderer renderer;

  public LoadItemWizardProperties(Resources resources, ObjectUi<Object> ui) {
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