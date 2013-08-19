package net.sf.anathema.hero.spells.display.view;

import net.sf.anathema.character.main.magic.spells.CircleType;
import net.sf.anathema.fx.hero.perspective.AbstractBridgingView;
import net.sf.anathema.hero.charms.display.magic.MagicLearnProperties;
import net.sf.anathema.hero.charms.display.magic.MagicLearnView;
import net.sf.anathema.hero.spells.display.presenter.SpellView;
import net.sf.anathema.hero.spells.display.presenter.SpellViewProperties;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.util.Identifier;

public class BridgingSpellView extends AbstractBridgingView implements SpellView {
  private final FxSpellView fxView;

  public BridgingSpellView(FxSpellView fxView) {
    this.fxView = fxView;
    init(fxView, "skin/platform/tooltip.css");
  }

  @Override
  public void addCircleSelection(Identifier[] circles, SpellViewProperties properties) {
    fxView.addCircleSelection(circles, properties);
  }

  @Override
  public void showSelectedCircle(CircleType newValue) {
    fxView.showSelectedCircle(newValue);
  }

  @Override
  public MagicLearnView addMagicLearnView(MagicLearnProperties properties) {
    return fxView.addMagicLearnView(properties);
  }

  @Override
  public void addCircleSelectionListener(ObjectValueListener<CircleType> listener) {
    fxView.addCircleSelectionListener(listener);
  }
}
