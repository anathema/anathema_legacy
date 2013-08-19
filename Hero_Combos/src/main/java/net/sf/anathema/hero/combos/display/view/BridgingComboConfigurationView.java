package net.sf.anathema.hero.combos.display.view;

import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.fx.hero.perspective.AbstractBridgingView;
import net.sf.anathema.hero.charms.display.magic.MagicLearnProperties;
import net.sf.anathema.hero.charms.display.magic.MagicLearnView;
import net.sf.anathema.hero.combos.display.presenter.ComboConfigurationView;
import net.sf.anathema.hero.combos.display.presenter.ComboContainer;
import net.sf.anathema.hero.combos.display.presenter.ComboViewProperties;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public class BridgingComboConfigurationView extends AbstractBridgingView implements ComboConfigurationView, IView {
  private final FxComboConfigurationView fxView;

  public BridgingComboConfigurationView(FxComboConfigurationView fxView) {
    this.fxView = fxView;
    init(fxView, "skin/combos/combos.css", "skin/platform/tooltip.css");
  }

  @Override
  public void addComboEditor(ComboViewProperties properties) {
    fxView.addComboEditor(properties);
  }

  @Override
  public MagicLearnView addMagicLearnView(MagicLearnProperties viewProperties) {
    return fxView.addMagicLearnView(viewProperties);
  }

  @Override
  public ComboContainer addComboContainer() {
    return fxView.addComboContainer();
  }

  @Override
  public ITextView addComboNameView(String viewTitle) {
    return fxView.addComboNameView(viewTitle);
  }

  @Override
  public ITextView addComboDescriptionView(String viewTitle) {
    return fxView.addComboDescriptionView(viewTitle);
  }
}
