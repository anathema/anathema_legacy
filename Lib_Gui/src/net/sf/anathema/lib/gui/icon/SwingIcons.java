package net.sf.anathema.lib.gui.icon;

import javax.swing.Icon;
import javax.swing.UIManager;

public class SwingIcons {

  public static Icon getOptionPaneErrorIcon() {
    return UIManager.getIcon("OptionPane.errorIcon"); //$NON-NLS-1$
  }

  public static Icon getOptionPaneWarningIcon() {
    return UIManager.getIcon("OptionPane.warningIcon"); //$NON-NLS-1$
  }

  public static Icon getOptionPaneInformationIcon() {
    return UIManager.getIcon("OptionPane.informationIcon"); //$NON-NLS-1$
  }

  public static Icon getOptionPaneQuestionIcon() {
    return UIManager.getIcon("OptionPane.questionIcon"); //$NON-NLS-1$
  }
}