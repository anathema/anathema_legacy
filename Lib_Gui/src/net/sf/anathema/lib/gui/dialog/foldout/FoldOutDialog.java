package net.sf.anathema.lib.gui.dialog.foldout;

import net.sf.anathema.lib.gui.action.IActionConfiguration;
import net.sf.anathema.lib.gui.action.MnemonicLabel;
import net.sf.anathema.lib.gui.action.MnemonicLabelParser;
import net.sf.anathema.lib.gui.dialog.userdialog.UserDialog;
import net.sf.anathema.lib.lang.StringUtilities;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FoldOutDialog extends UserDialog {
  private JButton foldOutButton;
  private boolean isFoldedOut = false;
  private JPanel foldOutPanel;

  public FoldOutDialog(Component parent, IFoldOutDialogConfiguration userDialog) {
    super(parent, userDialog);
    isFoldedOut = userDialog.isInitiallyFoldedOut();
    updateResizeable();
  }

  @Override
  protected boolean isMainContentGrabVerticalSpace() {
    // for fold out dialogs the folded out content shall be resized instead of the main content 
    return false;
  }

  private IFoldOutDialogConfiguration getFoldOutUserDialog() {
    return (IFoldOutDialogConfiguration) getConfiguration();
  }

  @Override
  protected JComponent[] createAdditionalButtons() {
    foldOutButton = new JButton();
    foldOutButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        toggleFoldOut();
      }
    });
    updateButtonText();
    return new JComponent[]{ foldOutButton };
  }

  private void toggleFoldOut() {
    isFoldedOut = !isFoldedOut;
    foldOutPanel.setVisible(isFoldedOut);
    updateButtonText();
    updateResizeable();
    if (isFoldedOut) {
      getFoldOutUserDialog().getFoldOutPage().requestFocus();
    }
    else {
      getConfiguration().getDialogPage().requestFocus();
    }
    getDialog().pack();
  }

  private void updateResizeable() {
    getDialog().setResizable(isFoldedOut);
  }

  private void updateButtonText() {
    if (isFoldedOut) {
      configure(foldOutButton, getFoldOutUserDialog().getFoldInButtonConfiguration());
    }
    else {
      configure(foldOutButton, getFoldOutUserDialog().getFoldOutButtonConfiguration());
    }
  }

  private static void configure(JButton button, IActionConfiguration actionConfiguration) {
    MnemonicLabel label = MnemonicLabelParser.parse(actionConfiguration.getName());
    button.setText(label.getPlainText());
    if (label.getMnemonicCharacter() != null) {
      button.setMnemonic(label.getMnemonicCharacter().charValue());
    }
    String toolTipText = actionConfiguration.getToolTipText();
    button.setToolTipText(StringUtilities.isNullOrEmpty(toolTipText) ? null : toolTipText);
    button.setIcon(actionConfiguration.getIcon());
  }

  @Override
  protected JComponent createOptionalBelowButtonsPanel() {
    JComponent foldOutContent = getFoldOutUserDialog().getFoldOutPage().getContent();
    foldOutPanel = new JPanel(new BorderLayout());
    foldOutPanel.setVisible(false);
    foldOutPanel.setBorder(BorderFactory.createEmptyBorder(10, 8, 0, 8));
    foldOutPanel.add(foldOutContent, BorderLayout.CENTER);
    return foldOutPanel;
  }
}