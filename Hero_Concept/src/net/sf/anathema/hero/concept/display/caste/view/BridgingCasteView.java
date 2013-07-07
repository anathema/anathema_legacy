package net.sf.anathema.hero.concept.display.caste.view;

import net.sf.anathema.character.main.caste.CasteType;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.hero.concept.display.caste.presenter.CasteView;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.platform.fx.BridgingPanel;

import javax.swing.JComponent;

public class BridgingCasteView implements CasteView, IView {
  private final FxCasteView fxView;
  private final BridgingPanel panel = new BridgingPanel();

  public BridgingCasteView(FxCasteView fxView) {
    this.fxView = fxView;
    panel.init(fxView, "skin/concept/concept.css");
  }

  @Override
  public JComponent getComponent() {
    return panel.getComponent();
  }

  @Override
  public IObjectSelectionView<CasteType> addObjectSelectionView(String labelText, AgnosticUIConfiguration<CasteType> renderer) {
    return fxView.addObjectSelectionView(labelText, renderer);
  }

}