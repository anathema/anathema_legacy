package net.sf.anathema.framework.view.perspective;

import net.sf.anathema.lib.gui.action.SmartAction;

import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import java.awt.Component;

public class PerspectiveSelectionBar {
  private final JToolBar toolbar = new JToolBar();
  private final ButtonGroup buttonGroup = new ButtonGroup();
  private final PerspectiveStack perspectiveStack;

  public PerspectiveSelectionBar(PerspectiveStack perspectiveStack) {
    this.perspectiveStack = perspectiveStack;
  }

  public void addPerspective(final Perspective perspective) {
    JToggleButton selectionButton = new JToggleButton(createAction(perspective));
    toolbar.add(selectionButton);
    buttonGroup.add(selectionButton);
  }

  private SmartAction createAction(final Perspective perspective) {
    return new SmartAction(perspective.getTitle()) {
      @Override
      protected void execute(Component parentComponent) {
        perspectiveStack.show(perspective);
      }
    };
  }

  public JComponent getContent() {
    return toolbar;
  }

  public void selectFirstButton() {
    JToggleButton button = (JToggleButton) toolbar.getComponent(0);
    button.setSelected(true);
  }
}