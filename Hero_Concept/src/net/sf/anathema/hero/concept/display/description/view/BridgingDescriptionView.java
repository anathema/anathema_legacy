package net.sf.anathema.hero.concept.display.description.view;

import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.hero.concept.display.description.presenter.ICharacterDescriptionView;
import net.sf.anathema.hero.concept.display.description.presenter.IMultiComponentLine;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.platform.fx.BridgingPanel;

import javax.swing.JComponent;

public class BridgingDescriptionView implements ICharacterDescriptionView, IView {
  private final FxDescriptionView fxView;
  private final BridgingPanel panel = new BridgingPanel();

  public BridgingDescriptionView(FxDescriptionView fxView) {
    this.fxView = fxView;
    panel.init(fxView);
  }

  @Override
  public JComponent getComponent() {
    return panel.getComponent();
  }

  @Override
  public ITextView addLineView(String labelText) {
    return fxView.addLineView(labelText);
  }

  @Override
  public ITextView addAreaView(String labelText) {
    return fxView.addAreaView(labelText);
  }

  @Override
  public Tool addEditAction() {
    return fxView.addEditAction();
  }

  @Override
  public IMultiComponentLine addMultiComponentLine() {
    return fxView.addMultiComponentLine();
  }
}