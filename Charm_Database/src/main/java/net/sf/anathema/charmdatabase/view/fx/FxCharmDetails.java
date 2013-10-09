package net.sf.anathema.charmdatabase.view.fx;

import javafx.application.Platform;
import javafx.scene.Node;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.sf.anathema.charmdatabase.view.CharmBasicsPanel;
import net.sf.anathema.charmdatabase.view.CharmDetails;
import net.sf.anathema.charmdatabase.view.CharmInformationPanel;
import net.sf.anathema.charmdatabase.view.rules.CharmRulesPanel;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.platform.fx.StyledTitledPane;
import net.sf.anathema.platform.fx.selection.SelectionViewFactory;

import org.tbee.javafx.scene.layout.MigPane;

public class FxCharmDetails implements CharmDetails {

  private final String BASICS_HEIGHT = "20%";
  private final String RULES_HEIGHT = "60%";
  private final String INFO_HEIGHT = "20%";
	
  //private final FxCharmListView<IEquipmentStats> listView = new FxCharmListView<>();
  private final FxCharmBasicsPanel basicsPanel;
  private final FxCharmRulesPanel rulesPanel;
  private final FxCharmInformationPanel informationPanel;
  private MigPane outerPane = new MigPane(new LC().wrapAfter(1), new AC().grow().fill());

  public FxCharmDetails(SelectionViewFactory selectionFactory, Resources resources) {
    this.basicsPanel = new FxCharmBasicsPanel(selectionFactory);
    this.rulesPanel = new FxCharmRulesPanel(resources, selectionFactory);
    this.informationPanel = new FxCharmInformationPanel();
  }

  public Node getNode() {
    return outerPane;
  }

  /*@Override
  public ToolListView<IEquipmentStats> initStatsListView(final String title,
                                                         AgnosticUIConfiguration<IEquipmentStats> configuration) {
    listView.setUiConfiguration(configuration);
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        Node node = listView.getNode();
        Node titledPane = StyledTitledPane.Create(title, node);
        outerPane.add(titledPane, new CC().push().grow());
      }
    });
    return listView;
  }*/

  @Override
  public CharmBasicsPanel addBasicsPanel(final String title) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        Node titledPane = StyledTitledPane.Create(title, basicsPanel.getNode());
        outerPane.add(titledPane, new CC().height(BASICS_HEIGHT).grow().push());
      }
    });
    return basicsPanel;
  }

  @Override
  public CharmRulesPanel addRulesPanel(final String title) {
	  Platform.runLater(new Runnable() {
	      @Override
	      public void run() {
	        Node titledPane = StyledTitledPane.Create(title, rulesPanel.getNode());
	        outerPane.add(titledPane, new CC().height(RULES_HEIGHT).grow().push());
	      }
	    });
	    return rulesPanel;
  }

  @Override
  public CharmInformationPanel addInformationPanel(final String title) {
	  Platform.runLater(new Runnable() {
	      @Override
	      public void run() {
	        Node titledPane = StyledTitledPane.Create(title, informationPanel.getNode());
	        outerPane.add(titledPane, new CC().height(INFO_HEIGHT).grow().push());
	      }
	    });
	    return informationPanel;
  }
}