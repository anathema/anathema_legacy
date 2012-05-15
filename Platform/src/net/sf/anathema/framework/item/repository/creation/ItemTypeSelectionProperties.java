package net.sf.anathema.framework.item.repository.creation;

import net.disy.commons.swing.ui.IObjectUi;
import net.disy.commons.swing.ui.ObjectUiListCellRenderer;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.lib.message.BasicMessage;
import net.sf.anathema.lib.message.IBasicMessage;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.wizard.selection.ILegalityProvider;
import net.sf.anathema.lib.workflow.wizard.selection.IObjectSelectionProperties;

import javax.swing.JList;
import javax.swing.ListCellRenderer;
import java.awt.Component;

public class ItemTypeSelectionProperties implements IObjectSelectionProperties {

  private final IResources resources;
  private final ILegalityProvider<IItemType> provider;
  private final IObjectUi<Object> ui;

  public ItemTypeSelectionProperties(IResources resources, ILegalityProvider<IItemType> provider, IObjectUi<Object> ui) {
    this.resources = resources;
    this.provider = provider;
    this.ui = ui;

  }

  @Override
  public ListCellRenderer getCellRenderer() {
    return new ObjectUiListCellRenderer(ui) {
      private static final long serialVersionUID = -3220572466818925411L;

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

  @Override
  public String getSelectionTitle() {
    return resources.getString("AnathemaPersistence.NewWizard.SelectItem.Title"); //$NON-NLS-1$
  }

  @Override
  public IBasicMessage getSelectMessage() {
    return new BasicMessage(resources.getString("AnathemaPersistence.NewWizard.SelectItem.Message")); //$NON-NLS-1$
  }
}