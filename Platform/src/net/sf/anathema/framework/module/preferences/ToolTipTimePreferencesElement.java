package net.sf.anathema.framework.module.preferences;

import static net.sf.anathema.framework.presenter.action.preferences.IAnathemaPreferencesConstants.TOOL_TIP_TIME_PREFERENCE;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.framework.presenter.action.preferences.IPreferencesElement;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;
import net.sf.anathema.lib.gui.widgets.IntegerSpinner;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;

public class ToolTipTimePreferencesElement implements IPreferencesElement {

  private int toolTipTime = SYSTEM_PREFERENCES.getInt(TOOL_TIP_TIME_PREFERENCE, 10);
  private boolean dirty;
  private IntegerSpinner spinner;

  public void addCompoment(IGridDialogPanel panel, IResources resources) {
    panel.add(getComponent(resources));
  }

  private IDialogComponent getComponent(IResources resources) {
    final JLabel toolTipTimeLabel = new JLabel(resources.getString("AnathemaCore.Tools.Preferences.ToolTipTime") + ":"); //$NON-NLS-1$ //$NON-NLS-2$
    spinner = new IntegerSpinner(toolTipTime);
    spinner.setPreferredWidth(70);
    spinner.setMinimum(0);
    spinner.addChangeListener(new IIntValueChangedListener() {
      public void valueChanged(int newValue) {
        if (toolTipTime == newValue) {
          return;
        }
        toolTipTime = newValue;
        dirty = true;
      }
    });
    return new IDialogComponent() {
      public int getColumnCount() {
        return 2;
      }

      public void fillInto(JPanel panel, int columnCount) {
        panel.add(toolTipTimeLabel);
        panel.add(spinner.getComponent());
      }
    };
  }

  public void savePreferences() {
    IPreferencesElement.SYSTEM_PREFERENCES.putInt(TOOL_TIP_TIME_PREFERENCE, toolTipTime);
  }
  
  public boolean isValid() {
    return true;
  }

  public boolean isDirty() {
    return dirty;
  }

  public IIdentificate getCategory() {
    return SYSTEM_CATEGORY;
  }

  public void reset() {
    toolTipTime = SYSTEM_PREFERENCES.getInt(TOOL_TIP_TIME_PREFERENCE, 10);
    spinner.setValue(toolTipTime);
    dirty = false;
  }
}