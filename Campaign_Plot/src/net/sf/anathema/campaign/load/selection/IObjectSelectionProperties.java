package net.sf.anathema.campaign.load.selection;

import net.sf.anathema.lib.message.IBasicMessage;

import javax.swing.ListCellRenderer;

public interface IObjectSelectionProperties {

  ListCellRenderer getCellRenderer();

  String getSelectionTitle();

  IBasicMessage getSelectMessage();
}