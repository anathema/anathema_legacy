package net.sf.anathema.charmdatabase.view;

import net.sf.anathema.charmdatabase.view.info.CharmInformationPanel;
import net.sf.anathema.charmdatabase.view.rules.CharmRulesPanel;


public interface CharmDetails {

  //ToolListView<IEquipmentStats> initStatsListView(String title, AgnosticUIConfiguration<IEquipmentStats> configuration);

  CharmBasicsPanel addBasicsPanel(String title);
  
  CharmRulesPanel addRulesPanel(String title);
  
  CharmInformationPanel addInformationPanel(String title);
}