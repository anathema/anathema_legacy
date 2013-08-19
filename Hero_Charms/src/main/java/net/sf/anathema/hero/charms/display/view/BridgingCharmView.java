package net.sf.anathema.hero.charms.display.view;

import net.sf.anathema.fx.hero.perspective.AbstractBridgingView;
import net.sf.anathema.hero.charms.display.special.ToggleButtonSpecialNodeView;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.platform.tree.display.CategorizedSpecialNodeView;
import net.sf.anathema.platform.tree.display.ContentFactory;
import net.sf.anathema.platform.tree.display.TreeView;

public class BridgingCharmView extends AbstractBridgingView implements CharmView {
  private FxCharmView fxView;

  public BridgingCharmView(FxCharmView fxView) {
    this.fxView = fxView;
    init(fxView);
  }

  @Override
  public TreeView addTreeView() {
    return fxView.addTreeView();
  }

  @Override
  public ObjectSelectionView<Identifier> addSelectionView(String title, AgnosticUIConfiguration<Identifier> uiConfig) {
    return fxView.addSelectionView(title, uiConfig);
  }

  @Override
  public ObjectSelectionView<Identifier> addSelectionViewAndSizeItFor(String title, AgnosticUIConfiguration<Identifier> uiConfig, Identifier[] objects) {
    return fxView.addSelectionViewAndSizeItFor(title, uiConfig, objects);
  }

  @Override
  public void addCharmCascadeHelp(String helpText) {
    fxView.addCharmCascadeHelp(helpText);
  }

  @Override
  public void whenCursorLeavesCharmAreaResetAllPopups() {
    fxView.whenCursorLeavesCharmAreaResetAllPopups();
  }

  @Override
  public void registerSpecialType(Class contentClass, ContentFactory factory) {
    fxView.registerSpecialType(contentClass, factory);
  }

  @Override
  public ToggleButtonSpecialNodeView createToggleButtonSpecialView() {
    return fxView.createToggleButtonSpecialView();
  }

  @Override
  public CategorizedSpecialNodeView createCategorizedSpecialView() {
    return fxView.createCategorizedSpecialView();
  }
}