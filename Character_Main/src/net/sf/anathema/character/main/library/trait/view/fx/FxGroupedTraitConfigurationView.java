package net.sf.anathema.character.main.library.trait.view.fx;

import javafx.scene.Node;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.main.library.trait.view.GroupedFavorableTraitConfigurationView;
import net.sf.anathema.character.main.presenter.ExtensibleTraitView;
import net.sf.anathema.character.main.view.ColumnCount;
import net.sf.anathema.platform.fx.NodeHolder;
import org.tbee.javafx.scene.layout.MigPane;

public class FxGroupedTraitConfigurationView implements GroupedFavorableTraitConfigurationView, NodeHolder {
  private FxGroupedTraitView groupedView;
  private final MigPane pane = new MigPane();

  @Override
  public void initGui(ColumnCount columnCount, ICharacterType characterType) {
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
