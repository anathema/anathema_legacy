package net.sf.anathema.charmdatabase.view;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.charmdatabase.view.info.CharmInformationPanel;
import net.sf.anathema.charmdatabase.view.rules.CharmRulesPanel;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.selection.VetoableObjectSelectionView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public class AgnosticCharmDatabaseView implements CharmDatabaseView {

  private final CharmNavigation navigation;
  private final CharmDetails details;

  public AgnosticCharmDatabaseView(CharmNavigation navigation, CharmDetails details) {
    this.navigation = navigation;
    this.details = details;
  }

  /*@Override
  public ToolListView<IEquipmentStats> initStatsListView(
          String title, AgnosticUIConfiguration<IEquipmentStats> configuration) {
    return details.initStatsListView(title, configuration);
  }*/

  @Override
  public CharmBasicsPanel addBasicsPanel(String title) {
	  return details.addBasicsPanel(title);
  }
  
  @Override
  public CharmRulesPanel addRulesPanel(String title) {
	  return details.addRulesPanel(title);
  }

  @Override
  public CharmInformationPanel addInformationPanel(String title) {
	  return details.addInformationPanel(title);
  }

  @Override
  public VetoableObjectSelectionView<Charm> getTemplateListView() {
	  return navigation.getTemplateListView();
  }

  @Override
  public Tool addEditTemplateTool() {
	  return navigation.addEditTemplateTool();
  }

  @Override
  public ITextView addTextualFilter(String label) {
	  return navigation.addTextualFilter(label);
  }
}
