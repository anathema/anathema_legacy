package net.sf.anathema.fx.hero.configurableview;

import net.sf.anathema.character.main.library.util.CssSkinner;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.framework.value.IntValueView;
import net.sf.anathema.fx.hero.perspective.AbstractBridgingView;
import net.sf.anathema.hero.display.configurableview.ConfigurableCharacterView;
import net.sf.anathema.hero.display.configurableview.MultiComponentLine;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public class BridgingConfigurableView extends AbstractBridgingView implements ConfigurableCharacterView {
  private final FxConfigurableView fxView;

  public BridgingConfigurableView(FxConfigurableView fxView, CharacterType type) {
    this.fxView = fxView;
    String[] skins = new CssSkinner().getSkins(type);
    init(fxView, skins);
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
  public <T> ObjectSelectionView<T> addSelectionView(String label, AgnosticUIConfiguration<T> uiConfiguration) {
    return fxView.addSelectionView(label, uiConfiguration);
  }

  @Override
  public IntValueView addDotSelector(String label, int maxValue) {
    return fxView.addDotSelector(label, maxValue);
  }
}