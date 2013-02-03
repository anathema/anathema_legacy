package net.sf.anathema.character.impl.module.perspective;

import net.sf.anathema.lib.gui.IView;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.CardLayout;

public class StackView {

  private final CardLayout stack = new CardLayout();
  private final JPanel viewPanel = new JPanel(stack);

  public void showView(String identifier) {
    stack.show(viewPanel, identifier);
  }

  public void addView(String identifier, IView view) {
    viewPanel.add(view.getComponent(), identifier);
    viewPanel.revalidate();
    viewPanel.repaint();
  }

  public JComponent getContent() {
    return viewPanel;
  }
}
