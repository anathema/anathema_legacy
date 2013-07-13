package net.sf.anathema.hero.spells.display.view;

import net.sf.anathema.character.main.magic.model.spells.CircleType;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.hero.magic.display.MagicLearnProperties;
import net.sf.anathema.hero.magic.display.MagicLearnView;
import net.sf.anathema.hero.spells.display.presenter.SpellView;
import net.sf.anathema.hero.spells.display.presenter.SpellViewProperties;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.platform.fx.BridgingPanel;

import javax.swing.JComponent;

public class BridgingSpellView implements IView, SpellView {
  private final FxSpellView fxView;
  private final BridgingPanel panel = new BridgingPanel();

  public BridgingSpellView(FxSpellView fxView) {
    this.fxView = fxView;
    panel.init(fxView, "skin/platform/tooltip.css");
  }

  @Override
  public JComponent getComponent() {
    return panel.getComponent();
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
