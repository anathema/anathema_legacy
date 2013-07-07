package net.sf.anathema.character.main.library.trait.view.fx;

import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.main.library.trait.view.GroupedFavorableTraitConfigurationView;
import net.sf.anathema.character.main.library.util.CssSkinner;
import net.sf.anathema.character.main.presenter.ExtensibleTraitView;
import net.sf.anathema.character.model.view.ColumnCount;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.platform.fx.BridgingPanel;

import javax.swing.JComponent;

public class BridgingTraitConfigurationView implements IView, GroupedFavorableTraitConfigurationView {
  private final FxGroupedTraitConfigurationView fxView;
  private final BridgingPanel panel = new BridgingPanel();

  public BridgingTraitConfigurationView(FxGroupedTraitConfigurationView fxView) {
    this.fxView = fxView;
  }

  @Override
  public void initGui(ColumnCount columnCount, ICharacterType characterType) {
    fxView.initGui(columnCount, characterType);
    String[] skins = new CssSkinner().getSkins(characterType);
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