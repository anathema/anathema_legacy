package net.sf.anathema.framework.perspective;

import net.miginfocom.layout.AC;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.framework.swing.IView;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class SwingPerspectivePane implements IView {

  private final JPanel panel = new JPanel(new MigLayout(new LC().fill(), new AC().grow().fill()));
  private final JPanel navigationPanel = new JPanel(new BorderLayout());
  private final JPanel contentPanel = new JPanel(new BorderLayout());

  public SwingPerspectivePane() {
    navigationPanel.setMinimumSize(new Dimension(200, 0));
    navigationPanel.setPreferredSize(new Dimension(200, 0));
    navigationPanel.setMaximumSize(new Dimension(200, Integer.MAX_VALUE));
    panel.add(wrap(navigationPanel),new CC().dockWest());
    panel.add(wrap(contentPanel), new CC().push().grow());
  }

  private JPanel wrap(JComponent component) {
    JPanel panel = new JPanel(new BorderLayout());
    panel.add(component, BorderLayout.CENTER);
    panel.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
    component.setBorder(BorderFactory.createRaisedSoftBevelBorder());
    return panel;
  }

  public void setNavigationComponent(JComponent component) {
    navigationPanel.add(component, BorderLayout.CENTER);
  }

  public void setContentComponent(JComponent component) {
    contentPanel.add(component, BorderLayout.CENTER);
  }

  @Override
  public JComponent getComponent() {
    return panel;
  }
}