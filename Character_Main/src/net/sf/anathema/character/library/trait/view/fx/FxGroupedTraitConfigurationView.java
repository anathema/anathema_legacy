package net.sf.anathema.character.library.trait.view.fx;

import javafx.scene.Node;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.library.trait.view.GroupedFavorableTraitConfigurationView;
import net.sf.anathema.character.presenter.ExtensibleTraitView;
import net.sf.anathema.character.view.ColumnCount;
import org.tbee.javafx.scene.layout.MigPane;

public class FxGroupedTraitConfigurationView implements GroupedFavorableTraitConfigurationView {
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
  public ExtensibleTraitView addExtensibleTraitView(String string, int currentValue, int maximalValue,
                                                    Trait favorableTrait) {
    return groupedView.addExtensibleTraitView(string, currentValue, maximalValue, favorableTrait);
  }

  public Node getNode() {
    return pane;
  }
}
