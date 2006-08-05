package net.sf.anathema.framework.item.repository.creation;

import javax.swing.ListCellRenderer;

import net.disy.commons.core.message.BasicMessage;
import net.disy.commons.core.message.IBasicMessage;
import net.sf.anathema.framework.view.IdentificateSelectCellRenderer;
import net.sf.anathema.lib.resources.IResources;

public class SelectedItemTypeProperties {

  private final IResources resources;

  public SelectedItemTypeProperties(IResources resources) {
    this.resources = resources;
  }

  public ListCellRenderer getCellRenderer() {
    return new IdentificateSelectCellRenderer("ItemType.", ".PrintName", resources); //$NON-NLS-1$ //$NON-NLS-2$
  }

  public String getItemSelectionTitle() {
    return resources.getString("AnathemaPersistence.NewWizard.SelectItem.Title"); //$NON-NLS-1$
  }

  public IBasicMessage getSelectMessage() {
    return new BasicMessage(resources.getString("AnathemaPersistence.NewWizard.SelectItem.Message")); //$NON-NLS-1$
  }
}