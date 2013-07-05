package net.sf.anathema.character.library.trait.view.fx;

import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.library.trait.view.GroupedFavorableTraitConfigurationView;
import net.sf.anathema.character.presenter.ExtensibleTraitView;
import net.sf.anathema.character.view.ColumnCount;
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
    String coreSkin = "skin/anathema/dotselector.css";
    String characterTypeSkin = chooseSkinForCharacterType(characterType);
    panel.init(fxView, coreSkin, characterTypeSkin);
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

  private String chooseSkinForCharacterType(ICharacterType characterType) {
    if (characterType.getId().equals("Solar")) {
      return "skin/solar/trait.css";
    } else if (characterType.getId().equals("Mortal")) {
      return "skin/mortal/trait.css";
    } else {
      return "skin/anathema/character/trait.css";
    }
  }
}