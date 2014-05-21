package net.sf.anathema.fx.hero.traitview;

import javafx.scene.Node;
import net.sf.anathema.hero.traits.display.GroupedFavorableTraitConfigurationView;
import net.sf.anathema.character.framework.display.ColumnCount;
import net.sf.anathema.hero.display.ExtensibleTraitView;
import net.sf.anathema.platform.fx.NodeHolder;
import org.tbee.javafx.scene.layout.MigPane;

public class FxGroupedTraitConfigurationView implements GroupedFavorableTraitConfigurationView, NodeHolder {
  private FxGroupedTraitView groupedView;
  private final MigPane pane = new MigPane();

  @Override
  public void initGui(ColumnCount columnCount) {
    this.groupedView = new FxGroupedTraitView(pane, columnCount);
  }

  @Override
  public void startNewTraitGroup(String groupLabel) {
    groupedView.startNewGroup(groupLabel);
  }

  @Override
  public ExtensibleTraitView addExtensibleTraitView(String string, int maximalValue) {
    return groupedView.addExtensibleTraitView(string, maximalValue);
  }

  @Override
  public Node getNode() {
    return pane;
  }
}
