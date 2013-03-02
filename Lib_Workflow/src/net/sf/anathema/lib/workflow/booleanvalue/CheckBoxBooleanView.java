package net.sf.anathema.lib.workflow.booleanvalue;

import net.sf.anathema.lib.control.IBooleanValueChangedListener;
import net.sf.anathema.lib.gui.IView;

import javax.swing.JCheckBox;
import javax.swing.JToggleButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckBoxBooleanView implements IBooleanValueView, IView {

  private final JCheckBox checkBox;

  public CheckBoxBooleanView(String label) {
    checkBox = new JCheckBox(label);
  }

  @Override
  public void addChangeListener(final IBooleanValueChangedListener listener) {
    checkBox.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        listener.valueChanged(checkBox.isSelected());
      }
    });
  }

  @Override
  public void setSelected(boolean selected) {
    checkBox.setSelected(selected);
  }

  @Override
  public JToggleButton getComponent() {
    return checkBox;
  }
}