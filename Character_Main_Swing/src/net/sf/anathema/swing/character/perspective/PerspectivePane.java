package net.sf.anathema.swing.character.perspective;

import net.sf.anathema.lib.gui.IView;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.Border;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class PerspectivePane implements IView {

  private final JPanel panel = new JPanel(new BorderLayout());
  private final JPanel navigationPanel = new JPanel(new BorderLayout());
  private final JPanel contentPanel = new JPanel(new BorderLayout());

  public PerspectivePane() {
    navigationPanel.setMinimumSize(new Dimension(200, 0));
    navigationPanel.setPreferredSize(new Dimension(200, 0));
    navigationPanel.setMaximumSize(new Dimension(200, Integer.MAX_VALUE));
    panel.add(wrap(navigationPanel), BorderLayout.WEST);
    panel.add(wrap(contentPanel), BorderLayout.CENTER);
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
