package net.sf.anathema.lib.workflow.booleanvalue;

import net.sf.anathema.lib.control.IBooleanValueChangedListener;

import javax.swing.JToggleButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BooleanValuePresentation {

  public void initPresentation(final JToggleButton button, final BooleanValueModel model) {
    button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        model.setValue(button.isSelected());
      }
    });
    model.addChangeListener(new IBooleanValueChangedListener() {
      @Override
      public void valueChanged(boolean newValue) {
        button.setSelected(newValue);
      }
    });
    button.setSelected(model.getValue());
  }

  public void initPresentation(final IBooleanValueView view, final BooleanValueModel model) {
    view.addChangeListener(new IBooleanValueChangedListener() {
      @Override
      public void valueChanged(boolean newValue) {
        model.setValue(newValue);
      }
    });
    model.addChangeListener(new IBooleanValueChangedListener() {
      @Override
      public void valueChanged(boolean newValue) {
        view.setSelected(newValue);
      }
    });
    view.setSelected(model.getValue());
  }
}