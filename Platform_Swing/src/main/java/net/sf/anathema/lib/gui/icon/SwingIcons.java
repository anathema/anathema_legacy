package net.sf.anathema.lib.gui.icon;

import javax.swing.Icon;
import javax.swing.UIManager;

public class SwingIcons {

  public static Icon getOptionPaneErrorIcon() {
    return UIManager.getIcon("OptionPane.errorIcon");
  }

  public static Icon getOptionPaneWarningIcon() {
    return UIManager.getIcon("OptionPane.warningIcon");
  }

  public static Icon getOptionPaneInformationIcon() {
    return UIManager.getIcon("OptionPane.informationIcon");
  }

  public static Icon getOptionPaneQuestionIcon() {
    return UIManager.getIcon("OptionPane.questionIcon");
  }
}