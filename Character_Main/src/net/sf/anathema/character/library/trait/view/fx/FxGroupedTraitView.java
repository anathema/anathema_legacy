package net.sf.anathema.character.library.trait.view.fx;

import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.library.trait.view.GroupedTraitView;
import net.sf.anathema.character.presenter.ExtensibleTraitView;
import net.sf.anathema.character.view.ColumnCount;
import org.tbee.javafx.scene.layout.MigPane;

public class FxGroupedTraitView implements GroupedTraitView {
  private final FxGroupedColumnPanel panel;

  public FxGroupedTraitView(MigPane pane, ColumnCount columnCount) {
    this.panel = new FxGroupedColumnPanel(pane, columnCount);
  }

  @Override
  public void startNewGroup(String groupLabel) {
    panel.startNewGroup(groupLabel);
  }

  @Override
  public ExtensibleTraitView addExtensibleTraitView(String labelText, int value, int maxValue, Trait trait) {
    FxTraitView view = new FxTraitView(value, maxValue, trait);
    FxExtensibleTraitView extensibleTraitView = new FxExtensibleTraitView(view);
    panel.add(extensibleTraitView.getNode());
    return extensibleTraitView;
  }
}
