package net.sf.anathema.fx.hero.traitview;

import net.sf.anathema.character.main.library.trait.view.GroupedFavorableTraitConfigurationView;
import net.sf.anathema.character.main.library.util.CssSkinner;
import net.sf.anathema.character.main.presenter.ExtensibleTraitView;
import net.sf.anathema.character.main.type.ICharacterType;
import net.sf.anathema.character.main.view.ColumnCount;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.platform.fx.BridgingPanel;

import javax.swing.JComponent;

public class BridgingTraitConfigurationView implements IView, GroupedFavorableTraitConfigurationView {
  private final FxGroupedTraitConfigurationView fxView;
  private final ICharacterType type;
  private final BridgingPanel panel = new BridgingPanel();

  public BridgingTraitConfigurationView(FxGroupedTraitConfigurationView fxView, ICharacterType type) {
    this.fxView = fxView;
    this.type = type;
  }

  @Override
  public void initGui(ColumnCount columnCount) {
    fxView.initGui(columnCount);
    String[] skins = new CssSkinner().getSkins(type);
    panel.init(fxView, skins);
  }

  @Override
  public void startNewTraitGroup(String groupLabel) {
    fxView.startNewTraitGroup(groupLabel);
  }

  @Override
  public ExtensibleTraitView addExtensibleTraitView(String string, int maximalValue) {
    return fxView.addExtensibleTraitView(string, maximalValue);
  }

  @Override
  public JComponent getComponent() {
    return panel.getComponent();
  }
}