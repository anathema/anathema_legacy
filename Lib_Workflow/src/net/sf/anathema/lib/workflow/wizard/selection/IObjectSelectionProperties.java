package net.sf.anathema.lib.workflow.wizard.selection;

import net.sf.anathema.lib.message.IBasicMessage;

import javax.swing.ListCellRenderer;

public interface IObjectSelectionProperties {

  public ListCellRenderer getCellRenderer();

  public String getSelectionTitle();

  public IBasicMessage getSelectMessage();
}