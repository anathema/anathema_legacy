package net.sf.anathema.swing.character.perspective;

import net.sf.anathema.character.perspective.CharacterGridView;
import net.sf.anathema.framework.perspective.SwingPerspectivePane;
import net.sf.anathema.fx.character.perspective.CharacterGridFxView;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.platform.fx.InteractionView;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class CharacterSystemView {

  private final SwingPerspectivePane pane = new SwingPerspectivePane();
  private final CharacterGridFxView gridView = new CharacterGridFxView();
  private final StackView stackView = new StackView();
  private final SwingInteractionView interactionView;

  public CharacterSystemView(Resources resources) {
    this.interactionView = new SwingInteractionView(resources);
    JPanel panel = createNavigationPanel();
    pane.setNavigationComponent(panel);
    pane.setContentComponent(stackView.getComponent());
  }

  private JPanel createNavigationPanel() {
    JPanel panel = new JPanel(new BorderLayout());
    panel.add(interactionView.getComponent(), BorderLayout.NORTH);
    panel.add(gridView.getComponent(), BorderLayout.CENTER);
    return panel;
  }

  public InteractionView getInteractionView() {
    return interactionView;
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
