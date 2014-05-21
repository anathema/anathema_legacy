package net.sf.anathema.fx.hero.traitview;

import net.sf.anathema.hero.traits.display.GroupedTraitView;
import net.sf.anathema.character.framework.display.ColumnCount;
import net.sf.anathema.hero.display.ExtensibleTraitView;
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
  public ExtensibleTraitView addExtensibleTraitView(String labelText, int maxValue) {
    FxTraitView view = FxTraitView.WithDefaultLayout(labelText, maxValue);
    FxExtensibleTraitView extensibleTraitView = new FxExtensibleTraitView(view);
    extensibleTraitView.addTo(panel);
    return extensibleTraitView;
  }
}
