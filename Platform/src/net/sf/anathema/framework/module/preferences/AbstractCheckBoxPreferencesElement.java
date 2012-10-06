package net.sf.anathema.framework.module.preferences;

import net.miginfocom.layout.CC;
import net.sf.anathema.framework.presenter.action.preferences.IPreferencesElement;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class AbstractCheckBoxPreferencesElement implements IPreferencesElement {
  private boolean dirty = false;
  private JCheckBox checkBox;

  @Override
  public void addComponent(JPanel panel, IResources resources) {
    JComponent component = createCheckBox(resources);
    panel.add(component, new CC().spanX());
  }

  protected abstract boolean getBooleanParameter();

  protected abstract void setValue(boolean value);

  protected abstract String getLabelKey();

  private JComponent createCheckBox(IResources resources) {
    checkBox = new JCheckBox(resources.getString(getLabelKey()), getBooleanParameter());
    checkBox.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (checkBox.isSelected() != getBooleanParameter()) {
          setValue(checkBox.isSelected());
          dirty = true;
        }
      }
    });
    return checkBox;
  }

  @Override
  public boolean isValid() {
    return true;
  }

  @Override
  public boolean isDirty() {
    return dirty;
  }

  @Override
  public void reset() {
    resetValue();
    checkBox.setSelected(getBooleanParameter());
    dirty = false;
  }

  protected abstract void resetValue();
}
