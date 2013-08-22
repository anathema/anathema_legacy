package net.sf.anathema.fx.hero.perspective;

import javafx.scene.Node;
import net.sf.anathema.framework.perspective.SwingPerspectivePane;
import net.sf.anathema.hero.framework.perspective.CharacterGridView;
import net.sf.anathema.platform.fx.BridgingPanel;
import net.sf.anathema.platform.fx.InteractionView;
import net.sf.anathema.platform.fx.NodeHolder;
import net.sf.anathema.swing.hero.perspective.SwingInteractionView;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class CharacterSystemView {

  private final SwingPerspectivePane pane = new SwingPerspectivePane();
  private final CharacterGridFxView gridView = new CharacterGridFxView();
  private final StackView stackView = new StackView();
  private final SwingInteractionView interactionView;

  public CharacterSystemView() {
    this.interactionView = new SwingInteractionView();
    JPanel panel = createNavigationPanel();
    pane.setNavigationComponent(panel);
    BridgingPanel bridgingPanel = new BridgingPanel();
    bridgingPanel.init(new NodeHolder() {
      @Override
      public Node getNode() {
        return stackView.getComponent();
      }
    });
    pane.setContentComponent(bridgingPanel.getComponent());
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