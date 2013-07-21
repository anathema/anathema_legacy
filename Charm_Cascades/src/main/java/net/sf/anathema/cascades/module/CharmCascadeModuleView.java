package net.sf.anathema.cascades.module;

import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.hero.charms.display.view.AbstractCascadeSelectionView;
import net.sf.anathema.hero.charms.display.view.CascadeSelectionView;
import net.sf.anathema.platform.tree.display.NodeProperties;
import net.sf.anathema.platform.tree.display.ToolTipProperties;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class CharmCascadeModuleView implements CascadeViewFactory, IView {
  private final JPanel panel = new JPanel(new MigLayout(new LC().wrapAfter(1).fill().insets("2")));

  @Override
  public CascadeSelectionView createCascadeView(ToolTipProperties properties, NodeProperties nodeProperties) {
    AbstractCascadeSelectionView view = new AbstractCascadeSelectionView();
    view.initGui(properties, nodeProperties);
    panel.add(view.getComponent(), new CC().grow().push());
    return view;
  }

  @Override
  public JComponent getComponent() {
    return panel;
  }
}