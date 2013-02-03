package net.sf.anathema.character.impl.module.perspective;

import net.sf.anathema.character.perspective.CharacterStackPresenter;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.swing.character.perspective.StackView;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class CharacterSystemView {

  private final JPanel panel = new JPanel(new BorderLayout());
  private final CharacterButtonGrid buttonGrid = new CharacterButtonGrid();
  private final StackView stackView = new StackView();

  public CharacterSystemView() {
    panel.add(buttonGrid.getComponent(), BorderLayout.WEST);
    panel.add(stackView.getComponent(), BorderLayout.CENTER);
  }

  public void fillButtonGrid(IAnathemaModel model, CharacterStackPresenter characterStack) {
    buttonGrid.fillFromRepository(model, characterStack);
  }

  public StackView getStackView() {
    return stackView;
  }

  public JComponent getComponent() {
    return panel;
  }
}
