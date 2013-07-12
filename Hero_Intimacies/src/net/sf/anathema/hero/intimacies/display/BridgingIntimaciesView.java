package net.sf.anathema.hero.intimacies.display;

import net.sf.anathema.character.main.library.overview.OverviewCategory;
import net.sf.anathema.character.main.library.util.CssSkinner;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.hero.display.ExtensibleTraitView;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.platform.fx.BridgingPanel;

import javax.swing.JComponent;

public class BridgingIntimaciesView implements IntimaciesView, IView {

  private final FxIntimaciesView fxView;
  private final BridgingPanel panel = new BridgingPanel();

  public BridgingIntimaciesView(FxIntimaciesView fxView, CharacterType type) {
    this.fxView = fxView;
    String[] skins = new CssSkinner().getSkins(type);
    panel.init(fxView, skins);
  }

  @Override
  public JComponent getComponent() {
    return panel.getComponent();
  }

  @Override
  public StringEntryView addSelectionView(String labelText) {
    return fxView.addSelectionView(labelText);
  }

  @Override
  public OverviewCategory addOverview(String border) {
    return fxView.addOverview(border);
  }

  @Override
  public void setOverview(OverviewCategory overviewView) {
    fxView.setOverview(overviewView);
  }

  @Override
  public ExtensibleTraitView addIntimacy(String name, int currentValue, int maximalValue) {
    return fxView.addIntimacy(name, currentValue, maximalValue);
  }
}
