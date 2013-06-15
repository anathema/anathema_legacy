package net.sf.anathema.framework.module.preferences;

import net.sf.anathema.framework.presenter.action.preferences.IPreferencesElement;
import net.sf.anathema.initialization.PreferenceElement;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.control.IIntValueChangedListener;
import net.sf.anathema.lib.gui.widgets.IntegerSpinner;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identifier;

import javax.swing.JLabel;
import javax.swing.JPanel;

import static net.sf.anathema.framework.presenter.action.preferences.IAnathemaPreferencesConstants.TOOL_TIP_TIME_PREFERENCE;

@PreferenceElement
@Weight(weight = 40)
public class ToolTipTimePreferencesElement implements IPreferencesElement {
  private int toolTipTime = SYSTEM_PREFERENCES.getInt(TOOL_TIP_TIME_PREFERENCE, 10);
  private boolean dirty;
  private IntegerSpinner spinner;

  @Override
  public void addComponent(JPanel panel, Resources resources) {
    String text = resources.getString("AnathemaCore.Tools.Preferences.ToolTipTime") + ":";
    JLabel toolTipTimeLabel = new JLabel(text);
    spinner = new IntegerSpinner(toolTipTime);
    spinner.setPreferredWidth(70);
    spinner.setMinimum(0);
    spinner.setMaximum(10);
    spinner.addChangeListener(new IIntValueChangedListener() {
      @Override
      public void valueChanged(int newValue) {
        if (toolTipTime == newValue) {
          return;
        }
        toolTipTime = newValue;
        dirty = true;
      }
    });
    panel.add(toolTipTimeLabel);
    panel.add(spinner.getComponent());
  }

  @Override
  public void savePreferences() {
    IPreferencesElement.SYSTEM_PREFERENCES.putInt(TOOL_TIP_TIME_PREFERENCE, toolTipTime);
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
  public Identifier getCategory() {
    return SYSTEM_CATEGORY;
  }

  @Override
  public void reset() {
    toolTipTime = SYSTEM_PREFERENCES.getInt(TOOL_TIP_TIME_PREFERENCE, 10);
    spinner.setValue(toolTipTime);
    dirty = false;
  }
}
