package net.sf.anathema.lib.workflow.booleanvalue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JToggleButton;

import net.sf.anathema.lib.control.booleanvalue.IBooleanValueChangedListener;
import net.sf.anathema.lib.gui.IView;

public class CheckBoxBooleanView implements IBooleanValueView, IView {

  private final JCheckBox checkBox;

  public CheckBoxBooleanView(String label) {
    checkBox = new JCheckBox(label);
  }

  public void addChangeListener(final IBooleanValueChangedListener listener) {
    checkBox.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        listener.valueChanged(checkBox.isSelected());
      }
    });
  }

  public void setSelected(boolean selected) {
    checkBox.setSelected(selected);
  }

  public JToggleButton getComponent() {
    return checkBox;
  }
}