package net.sf.anathema.character.equipment.creation.view.swing;

import net.sf.anathema.lib.control.IBooleanValueChangedListener;
import net.sf.anathema.lib.workflow.booleanvalue.IBooleanValueView;

import javax.swing.JCheckBox;
import javax.swing.JComponent;


public class SwingBooleanView implements IBooleanValueView {
  private JCheckBox checkBox = new JCheckBox();
  
  @Override
  public void setSelected(boolean selected) {
    checkBox.setEnabled(selected);
  }

  @Override
  public void addChangeListener(IBooleanValueChangedListener listener) {
    checkBox.addChangeListener(e -> listener.valueChanged(checkBox.isSelected()));
  }

  public JComponent getComponent() {
    return checkBox;
  }
}