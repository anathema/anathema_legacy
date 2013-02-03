package net.sf.anathema.swing.character.perspective;

import net.sf.anathema.fx.character.perspective.CharacterGridFxView;
import net.sf.anathema.character.perspective.CharacterGridView;
import net.sf.anathema.lib.control.IChangeListener;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class CharacterSystemView {

  private class ButtonChangedListener implements IChangeListener {
    @Override
    public void changeOccurred() {
      panel.remove(gridView.getComponent());
      panel.add(gridView.getComponent(), BorderLayout.WEST);
      panel.revalidate();
    }
  }

  private final JPanel panel = new JPanel(new BorderLayout());
  private final CharacterGridFxView gridView = new CharacterGridFxView(new ButtonChangedListener());
  private final StackView stackView = new StackView();

  public CharacterSystemView() {
    panel.add(gridView.getComponent(), BorderLayout.WEST);
    panel.add(stackView.getComponent(), BorderLayout.CENTER);
  }

  public CharacterGridView getGridView() {
    return gridView;
  }

  public StackView getStackView() {
    return stackView;
  }

  public JComponent getComponent() {
    return panel;
  }
}
