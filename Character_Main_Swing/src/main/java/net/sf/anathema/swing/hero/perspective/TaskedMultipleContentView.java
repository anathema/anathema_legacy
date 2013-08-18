package net.sf.anathema.swing.hero.perspective;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import net.miginfocom.layout.CC;
import net.sf.anathema.framework.presenter.view.MultipleContentView;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.framework.view.util.ContentProperties;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
import net.sf.anathema.platform.fx.FxThreading;
import net.sf.anathema.platform.fx.StyledTitledPane;
import org.jdesktop.swingx.JXTitledSeparator;
import org.tbee.javafx.scene.layout.MigPane;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;

public class TaskedMultipleContentView implements MultipleContentView {
  private final MigPane contentPane = new MigPane(LayoutUtils.fillWithoutInsets().wrapAfter(1));
  private final String header;
  private final MigPane paneContainer;
  private CardLayout viewStack;
  private JPanel viewPanel;
  private boolean isEmpty = true;

  public TaskedMultipleContentView(String header, MigPane paneContainer, CardLayout viewStack, JPanel viewPanel) {
    this.header = header;
    this.paneContainer = paneContainer;
    this.viewStack = viewStack;
    this.viewPanel = viewPanel;
  }

  @Override
  public void addView(IView view, final ContentProperties tabProperties) {
    final String name = tabProperties.getName();
    viewPanel.add(createContainer(view, name), name);
    final Button trigger = new Button(name);
    trigger.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) {
        new SwitchToView(name, viewPanel, viewStack).execute();
      }
    });
    FxThreading.runOnCorrectThread(new Runnable() {
      @Override
      public void run() {
        contentPane.add(trigger);
      }
    });
    isEmpty = false;
  }

  @Override
  public void finishInitialization() {
    if (isEmpty) {
      return;
    }
    FxThreading.runOnCorrectThread(new Runnable() {
      @Override
      public void run() {
        Node styledPane = StyledTitledPane.Create(header, contentPane);
        paneContainer.add(styledPane, new CC().push().grow());
      }
    });
  }

  private JComponent createContainer(IView content, String name) {
    JPanel viewComponent = new JPanel(new BorderLayout());
    JXTitledSeparator title = new JXTitledSeparator(name);
    title.setBorder(new EmptyBorder(0, 0, 5, 0));
    title.setFont(title.getFont().deriveFont(Font.BOLD));
    viewComponent.add(title, BorderLayout.NORTH);
    viewComponent.setBorder(new EmptyBorder(10, 10, 10, 10));
    viewComponent.add(content.getComponent(), BorderLayout.CENTER);
    return viewComponent;
  }
}
