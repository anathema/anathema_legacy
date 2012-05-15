package net.sf.anathema.lib.gui.dialog.core;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.util.ButtonPanelBuilder;
import net.disy.commons.swing.layout.util.LayoutDirection;
import net.sf.anathema.lib.gui.action.MnemonicLabelParser;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.dialog.DialogIconResources;
import net.sf.anathema.lib.gui.dialog.DialogMessages;
import net.sf.anathema.lib.gui.swing.IEnableable;
import net.sf.anathema.lib.gui.widgets.Gap;
import net.sf.anathema.lib.gui.widgets.RolloverButtonFactory;

import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DialogButtonBarBuilder {

  private final List<JComponent> leftSideComponents = new ArrayList<JComponent>();
  private final List<JComponent> buttons = new ArrayList<JComponent>();
  private AbstractButton helpButton;

  public void addLeftSideComponent(JComponent component) {
    leftSideComponents.add(component);
  }

  public void addButtons(JComponent... buttonComponents) {
    buttons.addAll(Arrays.asList(buttonComponents));
  }

  public void addButtonsCompacted(JComponent... buttonComponents) {
    JPanel compactedButtonPanel = new JPanel(new GridLayout(1, 0, 0, 0));
    for (JComponent component : buttonComponents) {
      compactedButtonPanel.add(component);
    }
    addButtons(compactedButtonPanel);
  }

  public IEnableable setHelpHandler(IDialogHelpHandler helpHandler) {
    helpButton = createHelpButton(helpHandler);
    return new IEnableable() {
      @Override
      public void setEnabled(boolean enabled) {
        helpButton.setEnabled(enabled);
      }
    };
  }

  public JComponent createButtonBar() {
    ArrayList<JComponent> allLeftComponents = new ArrayList<JComponent>();
    if (helpButton != null) {
      allLeftComponents.add(new Gap(0, 0));//Add a little gap for moving the button to the right a bit 
      allLeftComponents.add(helpButton);
    }
    allLeftComponents.addAll(leftSideComponents);

    JPanel panel = new JPanel(new GridDialogLayout(allLeftComponents.size() + 1, false));
    for (JComponent component : allLeftComponents) {
      panel.add(component);
    }
    ButtonPanelBuilder buttonPanelBuilder = new ButtonPanelBuilder(LayoutDirection.HORIZONTAL);
    for (JComponent createdButton : buttons) {
      buttonPanelBuilder.add(createdButton);
    }
    JPanel buttonPanel = buttonPanelBuilder.createPanel();
    panel.add(buttonPanel, GridDialogLayoutData.FILL_HORIZONTAL);
    return panel;
  }

  private static AbstractButton createHelpButton(final IDialogHelpHandler helpHandler) {
    SmartAction helpAction = new SmartAction(DialogIconResources.DIALOG_HELP) {
      @Override
      protected void execute(Component parentComponent) {
        helpHandler.execute(parentComponent);
      }
    };
    String helpText = MnemonicLabelParser
        .parse(DialogMessages.HELP)
        .getPlainText();
    helpAction.setToolTipText(helpText);
    return RolloverButtonFactory.createButton(helpAction);
  }
}