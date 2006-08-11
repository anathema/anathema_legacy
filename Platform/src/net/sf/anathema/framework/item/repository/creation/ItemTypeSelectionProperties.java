package net.sf.anathema.framework.item.repository.creation;

import java.awt.Component;

import javax.swing.JList;
import javax.swing.ListCellRenderer;

import net.disy.commons.core.message.BasicMessage;
import net.disy.commons.core.message.IBasicMessage;
import net.disy.commons.swing.ui.IObjectUi;
import net.disy.commons.swing.ui.ObjectUiListCellRenderer;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.wizard.selection.ILegalityProvider;
import net.sf.anathema.lib.workflow.wizard.selection.IObjectSelectionProperties;

public class ItemTypeSelectionProperties implements IObjectSelectionProperties {

  private final IResources resources;
  private final ILegalityProvider<IItemType> provider;
  private final IObjectUi ui;

  public ItemTypeSelectionProperties(IResources resources, ILegalityProvider<IItemType> provider, IObjectUi ui) {
    this.resources = resources;
    this.provider = provider;
    this.ui = ui;

  }

  public ListCellRenderer getCellRenderer() {
    return new ObjectUiListCellRenderer(ui) {
      @Override
      public Component getListCellRendererComponent(
          JList list,
          Object value,
          int index,
          boolean isSelected,
          boolean cellHasFocus) {
        boolean isLegal = provider.isLegal((IItemType) value);
        boolean hasFocus = cellHasFocus && isLegal;
        boolean selected = isSelected && isLegal;
        Component displayComponent = super.getListCellRendererComponent(list, value, index, selected, hasFocus);
        displayComponent.setEnabled(isLegal);
        return displayComponent;
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