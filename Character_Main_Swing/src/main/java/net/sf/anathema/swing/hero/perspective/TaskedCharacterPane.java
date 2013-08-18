package net.sf.anathema.swing.hero.perspective;

import javafx.scene.Node;
import net.miginfocom.layout.LC;
import net.sf.anathema.character.main.view.CharacterPane;
import net.sf.anathema.framework.presenter.view.MultipleContentView;
import net.sf.anathema.framework.view.util.OptionalView;
import net.sf.anathema.hero.advance.overview.view.OverviewDisplay;
import net.sf.anathema.platform.fx.BridgingPanel;
import net.sf.anathema.platform.fx.NodeHolder;
import org.jdesktop.swingx.JXCollapsiblePane;
import org.tbee.javafx.scene.layout.MigPane;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

public class TaskedCharacterPane implements CharacterPane, OverviewDisplay {

  private final MigPane paneContainer = new MigPane(new LC().wrapAfter(1));
  private final CardLayout viewStack = new CardLayout();
  private final JPanel viewPanel = new JPanel(viewStack);
  private final JPanel content = new JPanel(new BorderLayout());
  private final JXCollapsiblePane overviewPane = new JXCollapsiblePane(JXCollapsiblePane.Direction.RIGHT);
  private final OptionalView overview = new OverviewOptionalView(overviewPane);

  public TaskedCharacterPane() {
    BridgingPanel bridgingPanel = new BridgingPanel();
    bridgingPanel.init(new NodeHolder() {
      @Override
      public Node getNode() {
        return paneContainer;
      }
    });
    JComponent component = bridgingPanel.getComponent();
    //TODO (Swing->FX) Layout properly when everything is in FX
    component.setPreferredSize(new Dimension(200, content.getHeight()));
    content.add(component, BorderLayout.WEST);
    content.add(viewPanel, BorderLayout.CENTER);
    content.add(overviewPane, BorderLayout.EAST);
    overviewPane.setAnimated(false);
    overviewPane.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
  }

  public OptionalView getOverview() {
    return overview;
  }

  @Override
  public MultipleContentView addMultipleContentView(String header) {
    return new TaskedMultipleContentView(header, paneContainer, viewStack, viewPanel);
  }

  @Override
  public void setOverviewPane(JComponent component) {
    overviewPane.removeAll();
    overviewPane.add(component);
  }

  @Override
  public JComponent getComponent() {
    return content;
  }
}