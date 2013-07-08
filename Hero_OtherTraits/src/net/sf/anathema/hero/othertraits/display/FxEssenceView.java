package net.sf.anathema.hero.othertraits.display;

import javafx.scene.Node;
import net.sf.anathema.character.main.library.trait.view.fx.FxTraitView;
import net.sf.anathema.character.main.view.labelledvalue.IValueView;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.fx.hero.overview.FxStringOverview;
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

  public IIntValueView addEssenceView(String labelText, int maxValue) {
    FxTraitView essenceView = FxTraitView.WithDefaultLayout(labelText, maxValue);
    essenceView.addTo(panel);
    return essenceView;
  }


}
