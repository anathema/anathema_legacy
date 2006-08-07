package net.sf.anathema.framework.item.repository.creation;

import javax.swing.ListCellRenderer;

import net.disy.commons.core.message.BasicMessage;
import net.disy.commons.core.message.IBasicMessage;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.lib.gui.list.LegalityCheckListCellRenderer;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.workflow.wizard.selection.ILegalityProvider;
import net.sf.anathema.lib.workflow.wizard.selection.IObjectSelectionProperties;

public class ItemTypeSelectionProperties implements IObjectSelectionProperties {

  private final IResources resources;
  private final ILegalityProvider<IItemType> provider;

  public ItemTypeSelectionProperties(IResources resources, ILegalityProvider<IItemType> provider) {
    this.resources = resources;
    this.provider = provider;
  }

  public ListCellRenderer getCellRenderer() {
    return new LegalityCheckListCellRenderer(resources) {
      @Override
      protected boolean isLegal(Object object) {
        return provider.isLegal((IItemType) object);
      }

      @Override
      protected String getPrintName(IResources res, Object value) {
        return res.getString("ItemType." + ((IIdentificate) value).getId() + ".PrintName"); //$NON-NLS-1$ //$NON-NLS-2$
      }
    };
  }

  public String getSelectionTitle() {
    return resources.getString("AnathemaPersistence.NewWizard.SelectItem.Title"); //$NON-NLS-1$
  }

  public IBasicMessage getSelectMessage() {
    return new BasicMessage(resources.getString("AnathemaPersistence.NewWizard.SelectItem.Message")); //$NON-NLS-1$
  }
}