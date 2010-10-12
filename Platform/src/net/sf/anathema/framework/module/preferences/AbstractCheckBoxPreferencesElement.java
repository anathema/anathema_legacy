package net.sf.anathema.framework.module.preferences;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;

import net.disy.commons.swing.layout.GridDialogLayoutDataUtilities;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.framework.presenter.action.preferences.IPreferencesElement;
import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;
import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractCheckBoxPreferencesElement implements IPreferencesElement {

  private boolean dirty = false;
  private JCheckBox checkBox;

  public void addCompoment(IGridDialogPanel panel, IResources resources) {
    panel.add(getComponent(resources));
  }

  private IDialogComponent getComponent(IResources resources) {
    final JComponent component = createCheckBox(resources);
    return new IDialogComponent() {
      public void fillInto(JPanel panel, int columnCount) {
        panel.add(component, GridDialogLayoutDataUtilities.createHorizontalSpanData(
            2,
            GridDialogLayoutData.FILL_HORIZONTAL));
      }

      public int getColumnCount() {
        return 1;
      }
    };
  }

  protected abstract boolean getBooleanParameter();

  protected abstract void setValue(boolean value);

  protected abstract String getLabelKey();

  private JComponent createCheckBox(IResources resources) {
    checkBox = new JCheckBox(resources.getString(getLabelKey()), getBooleanParameter());
    checkBox.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (checkBox.isSelected() != getBooleanParameter()) {
          setValue(checkBox.isSelected());
          dirty = true;
        }
      }
    });
    return checkBox;
  }
  
  public boolean isValid() {
    return true;
  }

  public boolean isDirty() {
    return dirty;
  }

  public void reset() {
    resetValue();
    checkBox.setSelected(getBooleanParameter());
    dirty = false;
  }

  protected abstract void resetValue();
}