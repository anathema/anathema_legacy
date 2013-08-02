package net.sf.anathema.hero.charms.display.view;

import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.platform.fx.BridgingPanel;
import net.sf.anathema.platform.tree.display.ContentFactory;
import net.sf.anathema.platform.tree.display.TreeView;

import javax.swing.JComponent;

public class BridgingCharmView implements CharmView, IView {
  private FxCharmView fxView;
  private BridgingPanel panel = new BridgingPanel();

  public BridgingCharmView(FxCharmView fxView) {
    this.fxView = fxView;
    panel.init(fxView);
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
  public JComponent getComponent() {
    return panel.getComponent();
  }
}