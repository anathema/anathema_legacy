package net.sf.anathema.demo.platform.item;

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
    return new IdentificateSelectCellRenderer("ItemType.", resources);
  }

  public String getItemSelectionTitle() {
    return resources.getString("NewItemWizard.SelectItemHeader"); //$NON-NLS-1$
  }

  public IBasicMessage getSelectMessage() {
    return new BasicMessage(resources.getString("NewItemWizard.SelectItemMessage")); //$NON-NLS-1$
  }
}