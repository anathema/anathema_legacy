package net.sf.anathema.framework.presenter.action;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import net.disy.commons.core.message.BasicMessage;
import net.disy.commons.core.message.IBasicMessage;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.wizard.selection.IObjectSelectionProperties;

public class LoadItemWizardProperties implements IObjectSelectionProperties {

  private final IResources resources;

  public LoadItemWizardProperties(IResources resources) {
    this.resources = resources;
  }

  public ListCellRenderer getCellRenderer() {
    return new DefaultListCellRenderer() {
      @Override
      public Component getListCellRendererComponent(
          JList list,
          Object value,
          int index,
          boolean isSelected,
          boolean cellHasFocus) {
        return super.getListCellRendererComponent(list, value.toString(), index, isSelected, cellHasFocus);
      }
    };
  }

  public IBasicMessage getSelectMessage() {
    return new BasicMessage(resources.getString("AnathemaPersistence.LoadAction.Message.Default")); //$NON-NLS-1$
  }

  public String getSelectionTitle() {
    return resources.getString("AnathemaPersistence.LoadMenu.Name"); //$NON-NLS-1$
  }
}