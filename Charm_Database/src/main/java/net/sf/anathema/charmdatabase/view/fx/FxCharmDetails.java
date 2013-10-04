package net.sf.anathema.charmdatabase.view.fx;

import javafx.application.Platform;
import javafx.scene.Node;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.CC;
import net.sf.anathema.charmdatabase.view.CharmDescriptionPanel;
import net.sf.anathema.charmdatabase.view.CharmDetails;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
import net.sf.anathema.platform.fx.StyledTitledPane;
import net.sf.anathema.platform.fx.selection.SelectionViewFactory;

import org.tbee.javafx.scene.layout.MigPane;

public class FxCharmDetails implements CharmDetails {

  //private final FxCharmListView<IEquipmentStats> listView = new FxCharmListView<>();
  private final FxCharmDescriptionPanel descriptionPanel;
  private MigPane outerPane = new MigPane(LayoutUtils.fillWithoutInsets().wrapAfter(1), new AC().grow().fill());

  public FxCharmDetails(SelectionViewFactory selectionFactory) {
    this.descriptionPanel = new FxCharmDescriptionPanel(selectionFactory);
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
  public CharmDescriptionPanel addDescriptionPanel(final String title) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        Node titledPane = StyledTitledPane.Create(title, descriptionPanel.getNode());
        outerPane.add(titledPane, new CC().grow().push());
      }
    });
    return descriptionPanel;
  }
}