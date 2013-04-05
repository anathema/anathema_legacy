package net.sf.anathema.framework.view.perspective;

import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.resources.Resources;

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
    this.toolbar.setFloatable(false);
  }

  public void addPerspective(final Perspective perspective, Resources resources) {
    SmartAction action = createAction(perspective, resources);
    JToggleButton selectionButton = new JToggleButton(action);
    toolbar.add(selectionButton);
    buttonGroup.add(selectionButton);
  }

  private SmartAction createAction(final Perspective perspective, Resources resources) {
    SmartAction action = new SmartAction() {
      @Override
      protected void execute(Component parentComponent) {
        perspectiveStack.show(perspective);
      }
    };
    PerspectiveToggle toggle = new ActionPerspectiveToggle(action, resources);
    perspective.configureToggle(toggle);
    return action;
  }

  public JComponent getContent() {
    return toolbar;
  }

  public void selectFirstButton() {
    JToggleButton button = (JToggleButton) toolbar.getComponent(0);
    button.setSelected(true);
  }
}