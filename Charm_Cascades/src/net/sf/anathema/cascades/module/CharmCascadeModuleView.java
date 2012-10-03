package net.sf.anathema.cascades.module;

import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.cascades.view.CascadeView;
import net.sf.anathema.framework.view.item.AbstractItemView;
import net.sf.anathema.platform.tree.presenter.view.NodeProperties;
import net.sf.anathema.platform.tree.presenter.view.ToolTipProperties;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class CharmCascadeModuleView extends AbstractItemView implements ICascadeViewFactory {
  private final JPanel panel = new JPanel(new MigLayout(new LC().wrapAfter(1).fill().insets("2")));

  public CharmCascadeModuleView(String printName, Icon icon) {
    super(printName, icon);
  }

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