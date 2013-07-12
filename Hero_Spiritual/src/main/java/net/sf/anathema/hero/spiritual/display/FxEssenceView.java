package net.sf.anathema.hero.spiritual.display;

import javafx.scene.Node;
import net.sf.anathema.character.main.view.labelledvalue.IValueView;
import net.sf.anathema.framework.value.IntValueView;
import net.sf.anathema.fx.hero.overview.FxStringOverview;
import net.sf.anathema.fx.hero.traitview.FxTraitView;
import org.tbee.javafx.scene.layout.MigPane;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.fillWithoutInsets;

public class FxEssenceView {
  private final MigPane panel = new MigPane(fillWithoutInsets().wrapAfter(2));

  public Node getNode() {
    return panel;
  }

  public IValueView<String> addPoolView(String labelText) {
    FxStringOverview poolView = new FxStringOverview(labelText);
    poolView.addTo(panel);
    return poolView;
  }

  public IntValueView addEssenceView(String labelText, int maxValue) {
    FxTraitView essenceView = FxTraitView.WithDefaultLayout(labelText, maxValue);
    essenceView.addTo(panel);
    return essenceView;
  }


}
