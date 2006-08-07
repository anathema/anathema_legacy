package net.sf.anathema.lib.workflow.wizard.selection;

import javax.swing.ListCellRenderer;

import net.disy.commons.core.message.IBasicMessage;

public interface IObjectSelectionProperties {

  public ListCellRenderer getCellRenderer();

  public String getSelectionTitle();

  public IBasicMessage getSelectMessage();
}