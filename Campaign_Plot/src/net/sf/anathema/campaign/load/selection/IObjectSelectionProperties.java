package net.sf.anathema.campaign.load.selection;

import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.message.IBasicMessage;

public interface IObjectSelectionProperties {

  AgnosticUIConfiguration getCellRenderer();

  String getSelectionTitle();

  IBasicMessage getSelectMessage();
}