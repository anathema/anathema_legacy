package net.sf.anathema.cascades.module;

import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.cascades.view.CascadeView;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.platform.tree.presenter.view.NodeProperties;
import net.sf.anathema.platform.tree.presenter.view.ToolTipProperties;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class CharmCascadeModuleView implements ICascadeViewFactory, IView {
  private final JPanel panel = new JPanel(new MigLayout(new LC().wrapAfter(1).fill().insets("2")));

  @Override
  public CascadeView createCascadeView(ToolTipProperties properties, NodeProperties nodeProperties) {
    CascadeView view = new CascadeView(properties, nodeProperties);
    panel.add(view.getComponent(), new CC().grow().push());
    return view;
  }

  @Override
  public JComponent getComponent() {
    return panel;
  }
}