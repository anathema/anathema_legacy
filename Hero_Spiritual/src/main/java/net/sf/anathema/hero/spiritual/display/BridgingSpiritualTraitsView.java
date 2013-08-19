package net.sf.anathema.hero.spiritual.display;

import net.sf.anathema.character.main.library.util.CssSkinner;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.character.main.view.labelledvalue.IValueView;
import net.sf.anathema.framework.value.IntValueView;
import net.sf.anathema.fx.hero.perspective.AbstractBridgingView;

public class BridgingSpiritualTraitsView extends AbstractBridgingView implements SpiritualTraitsView {
  private final FxSpiritualTraitsView fxView;

  public BridgingSpiritualTraitsView(FxSpiritualTraitsView fxView) {
    this.fxView = fxView;
  }

  @Override
  public void initGui(SpiritualTraitsViewProperties properties, CharacterType characterType) {
    fxView.initGui(properties, characterType);
    CssSkinner cssSkinner = new CssSkinner();
    String[] skins = cssSkinner.getSkins(characterType);
    init(fxView, skins);
  }

  @Override
  public IntValueView addVirtue(String labelText, int maxValue) {
    return fxView.addVirtue(labelText, maxValue);
  }

  @Override
  public IntValueView addWillpower(String labelText, int maxValue) {
    return fxView.addWillpower(labelText, maxValue);
  }

  @Override
  public IntValueView addEssenceView(String labelText, int maxValue) {
    return fxView.addEssenceView(labelText, maxValue);
  }

  @Override
  public IValueView<String> addPoolView(String labelText) {
    return fxView.addPoolView(labelText);
  }
}
