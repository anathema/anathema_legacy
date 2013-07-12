package net.sf.anathema.fx.hero.configurableview;

import net.sf.anathema.character.main.library.util.CssSkinner;
import net.sf.anathema.character.main.type.ICharacterType;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.framework.value.IntValueView;
import net.sf.anathema.hero.display.configurableview.ConfigurableCharacterView;
import net.sf.anathema.hero.display.configurableview.MultiComponentLine;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.platform.fx.BridgingPanel;

import javax.swing.JComponent;

public class BridgingConfigurableView implements ConfigurableCharacterView, IView {
  private final FxConfigurableView fxView;
  private final BridgingPanel panel = new BridgingPanel();

  public BridgingConfigurableView(FxConfigurableView fxView, ICharacterType type) {
    this.fxView = fxView;
    String[] skins = new CssSkinner().getSkins(type);
    panel.init(fxView, skins);
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
  public MultiComponentLine addMultiComponentLine() {
    return fxView.addMultiComponentLine();
  }

  @Override
  public <T> IObjectSelectionView<T> addSelectionView(String label, AgnosticUIConfiguration<T> uiConfiguration) {
    return fxView.addSelectionView(label, uiConfiguration);
  }

  @Override
  public IntValueView addDotSelector(String label, int maxValue) {
    return fxView.addDotSelector(label, maxValue);
  }
}