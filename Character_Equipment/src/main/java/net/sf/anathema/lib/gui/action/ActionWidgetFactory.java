package net.sf.anathema.lib.gui.action;

import net.sf.anathema.lib.control.ChangeListener;

import javax.swing.JCheckBox;

public class ActionWidgetFactory {

  public static JCheckBox createCheckBox(final SmartToggleAction action) {
    final JCheckBox checkBox = new JCheckBox(action);
    checkBox.setSelected(action.getSelectionModel().getValue());
    action.getSelectionModel().addChangeListener(new ChangeListener() {
      @Override
      public void changeOccurred() {
        checkBox.setSelected(action.getSelectionModel().getValue());
      }
    });
    return checkBox;
  }
}