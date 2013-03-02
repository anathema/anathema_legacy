package net.sf.anathema.swing.character.perspective;

import net.sf.anathema.character.perspective.model.model.CharacterIdentifier;
import net.sf.anathema.lib.gui.IView;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.CardLayout;

public class StackView implements IView {

  private final CardLayout stack = new CardLayout();
  private final JPanel viewPanel = new JPanel(stack);

  public void showView(CharacterIdentifier identifier) {
    stack.show(viewPanel, identifier.getId());
  }

  public void addView(CharacterIdentifier identifier, IView view) {
    viewPanel.add(view.getComponent(), identifier.getId());
    viewPanel.revalidate();
    viewPanel.repaint();
  }

  @Override
  public JComponent getComponent() {
    return viewPanel;
  }
}
