package net.sf.anathema.hero.othertraits.display;

import net.sf.anathema.character.main.library.util.CssSkinner;
import net.sf.anathema.character.main.type.ICharacterType;
import net.sf.anathema.character.main.view.labelledvalue.IValueView;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.platform.fx.BridgingPanel;

import javax.swing.JComponent;

public class BridgingOtherTraitsView implements IView, AdvantageView {
  private final FxOtherTraitsView fxView;
  private final BridgingPanel panel = new BridgingPanel();

  public BridgingOtherTraitsView(FxOtherTraitsView fxView) {
    this.fxView = fxView;
  }

  @Override
  public void initGui(AdvantageViewProperties properties, ICharacterType characterType) {
    fxView.initGui(properties, characterType);
    CssSkinner cssSkinner = new CssSkinner();
    String[] skins = cssSkinner.getSkins(characterType);
    panel.init(fxView, skins);
  }

  @Override
  public JComponent getComponent() {
    return panel.getComponent();
  }

  @Override
  public IIntValueView addVirtue(String labelText, int maxValue) {
    return fxView.addVirtue(labelText, maxValue);
  }

  @Override
  public IIntValueView addWillpower(String labelText, int maxValue) {
    return fxView.addWillpower(labelText, maxValue);
  }

  @Override
  public IIntValueView addEssenceView(String labelText, int maxValue) {
    return fxView.addEssenceView(labelText, maxValue);
  }

  @Override
  public IValueView<String> addPoolView(String labelText) {
    return fxView.addPoolView(labelText);
  }
}
