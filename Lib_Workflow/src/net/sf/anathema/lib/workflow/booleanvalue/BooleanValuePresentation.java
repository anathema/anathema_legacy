package net.sf.anathema.lib.workflow.booleanvalue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JToggleButton;

import net.sf.anathema.lib.control.booleanvalue.IBooleanValueChangedListener;

public class BooleanValuePresentation {

  public void initPresentation(final JToggleButton button, final BooleanValueModel model) {
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        model.setValue(button.isSelected());
      }
    });
    model.addChangeListener(new IBooleanValueChangedListener() {
      public void valueChanged(boolean newValue) {
        button.setSelected(newValue);
      }
    });
    button.setSelected(model.getValue());
  }
}