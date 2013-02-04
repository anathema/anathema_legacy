package net.sf.anathema.swing.character.perspective;

import net.sf.anathema.character.perspective.CharacterGridView;
import net.sf.anathema.fx.character.perspective.CharacterGridFxView;

import javax.swing.JComponent;

public class CharacterSystemView {

  private final PerspectivePane pane = new PerspectivePane();
  private final CharacterGridFxView gridView = new CharacterGridFxView();
  private final StackView stackView = new StackView();

  public CharacterSystemView() {
    pane.setNavigationComponent(gridView.getComponent());
    pane.setContentComponent(stackView.getComponent());
  }

  public CharacterGridView getGridView() {
    return gridView;
  }

  public StackView getStackView() {
    return stackView;
  }

  public JComponent getComponent() {
    return pane.getComponent();
  }
}
