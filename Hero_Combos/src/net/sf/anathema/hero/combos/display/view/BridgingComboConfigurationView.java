package net.sf.anathema.hero.combos.display.view;

import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.hero.combos.display.presenter.ComboConfigurationView;
import net.sf.anathema.hero.combos.display.presenter.ComboContainer;
import net.sf.anathema.hero.combos.display.presenter.ComboViewProperties;
import net.sf.anathema.hero.magic.display.MagicLearnProperties;
import net.sf.anathema.hero.magic.display.MagicLearnView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.platform.fx.BridgingPanel;

import javax.swing.JComponent;

public class BridgingComboConfigurationView implements ComboConfigurationView, IView {
  private final FxComboConfigurationView fxView;
  private final BridgingPanel panel = new BridgingPanel();

  public BridgingComboConfigurationView(FxComboConfigurationView fxView) {
    this.fxView = fxView;
    panel.init(fxView,"skin/combos/combos.css");
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

  @Override
  public JComponent getComponent() {
    return panel.getComponent();
  }
}
