package net.sf.anathema.hero.intimacies.display;

import javafx.scene.Node;
import net.miginfocom.layout.CC;
import net.sf.anathema.character.main.library.overview.OverviewCategory;
import net.sf.anathema.fx.hero.overview.FxOverviewCategory;
import net.sf.anathema.fx.hero.traitview.FxExtensibleTraitView;
import net.sf.anathema.fx.hero.traitview.FxTraitView;
import net.sf.anathema.fx.hero.traitview.SimpleTraitViewPanel;
import net.sf.anathema.hero.display.ExtensibleTraitView;
import net.sf.anathema.platform.fx.FxThreading;
import net.sf.anathema.platform.fx.NodeHolder;
import org.tbee.javafx.scene.layout.MigPane;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.fillWithoutInsets;
import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;

public class FxIntimaciesView implements IntimaciesView, NodeHolder {
  private final MigPane content = new MigPane(fillWithoutInsets());
  private final MigPane creationPane = new MigPane(withoutInsets());
  private final SimpleTraitViewPanel entryPanel = new SimpleTraitViewPanel();
  private final MigPane overviewPanel = new MigPane();

  public FxIntimaciesView() {
    MigPane mainPanel = new MigPane(fillWithoutInsets().wrapAfter(1));
    mainPanel.add(creationPane, new CC().growX());
    mainPanel.add(entryPanel.getNode(), new CC().alignY("top").growX());
    content.add(mainPanel, new CC().alignY("top").growX());
    content.add(overviewPanel, new CC().alignY("top").growX());
  }

  @Override
  public Node getNode() {
    return content;
  }

  @Override
  public StringEntryView addSelectionView(String labelText) {
    final FxStringEntryView view = new FxStringEntryView(labelText);
    FxThreading.runOnCorrectThread(new Runnable() {
      @Override
      public void run() {
        creationPane.add(view.getNode());
      }
    });
    return view;
  }

  @Override
  public OverviewCategory addOverview(String border) {
    return new FxOverviewCategory(overviewPanel, border);
  }

  @Override
  public void setOverview(final OverviewCategory overviewView) {
    FxThreading.runOnCorrectThread(new Runnable() {
      @Override
      public void run() {
        overviewPanel.getChildren().clear();
        FxOverviewCategory view = (FxOverviewCategory) overviewView;
        overviewPanel.add(view.getNode());
      }
    });
  }

  @Override
  public ExtensibleTraitView addIntimacy(String name, int currentValue, int maximalValue) {
    FxTraitView view = FxTraitView.WithDefaultLayout(name, maximalValue);
    FxExtensibleTraitView traitView = new FxExtensibleTraitView(view);
    traitView.addTo(entryPanel);
    return traitView;
  }
}