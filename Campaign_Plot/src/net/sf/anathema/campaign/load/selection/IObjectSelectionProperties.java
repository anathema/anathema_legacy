package net.sf.anathema.campaign.load.selection;

import net.sf.anathema.lib.gui.TechnologyAgnosticUIConfiguration;
import net.sf.anathema.lib.message.IBasicMessage;

public interface IObjectSelectionProperties {

  TechnologyAgnosticUIConfiguration getCellRenderer();

  String getSelectionTitle();

  IBasicMessage getSelectMessage();
}