package net.sf.anathema.cascades.module;

import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.hero.charms.display.view.CharmView;
import net.sf.anathema.hero.charms.display.view.SwingCharmView;
import net.sf.anathema.platform.tree.display.NodeProperties;
import net.sf.anathema.platform.tree.display.ToolTipProperties;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class CharmCascadeModuleView implements CascadeViewFactory, IView {
  private final JPanel panel = new JPanel(new MigLayout(new LC().wrapAfter(1).fill().insets("2")));

  @Override
  public CharmView createCascadeView(ToolTipProperties properties, NodeProperties nodeProperties) {
    SwingCharmView view = new SwingCharmView();
    view.initGui(properties, nodeProperties);
    panel.add(view.getComponent(), new CC().grow().push());
    return view;
  }

  @Override
  public JComponent getComponent() {
    return panel;
  }
}