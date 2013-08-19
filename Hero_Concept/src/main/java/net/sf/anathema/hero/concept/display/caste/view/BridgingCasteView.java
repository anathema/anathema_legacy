package net.sf.anathema.hero.concept.display.caste.view;

import net.sf.anathema.fx.hero.perspective.AbstractBridgingView;
import net.sf.anathema.hero.concept.CasteType;
import net.sf.anathema.hero.concept.display.caste.presenter.CasteView;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;

public class BridgingCasteView extends AbstractBridgingView implements CasteView {
  private final FxCasteView fxView;

  public BridgingCasteView(FxCasteView fxView) {
    this.fxView = fxView;
    init(fxView, "skin/concept/concept.css");
  }

  @Override
  public ObjectSelectionView<CasteType> addObjectSelectionView(String labelText, AgnosticUIConfiguration<CasteType> renderer) {
    return fxView.addObjectSelectionView(labelText, renderer);
  }
}