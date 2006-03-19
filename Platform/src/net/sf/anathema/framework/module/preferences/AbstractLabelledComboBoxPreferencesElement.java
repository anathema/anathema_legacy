package net.sf.anathema.framework.module.preferences;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;

import net.disy.commons.swing.layout.grid.GridAlignment;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.framework.presenter.action.preferences.IPreferencesElement;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;

public abstract class AbstractLabelledComboBoxPreferencesElement implements IPreferencesElement {

  private boolean dirty = false;

  public IDialogComponent getComponent(IResources resources) {
    final JComponent component = createCheckBox(resources);
    return new IDialogComponent() {
    
      public void fillInto(JPanel panel, int columnCount) {
        GridDialogLayoutData singleElementData = new GridDialogLayoutData();
        singleElementData.setHorizontalSpan(2);
        singleElementData.setHorizontalAlignment(GridAlignment.FILL);
        singleElementData.setGrabExcessHorizontalSpace(true);
        panel.add(component, singleElementData);
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
    final JCheckBox forceMetalBox = new JCheckBox(resources.getString(getLabelKey()), getBooleanParameter());
    forceMetalBox.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (forceMetalBox.isSelected() != getBooleanParameter()) {
          setValue(forceMetalBox.isSelected());
          dirty = true;
        }
      }
    });
    //forceMetalBox.setHorizontalTextPosition(SwingConstants.LEADING);
    return forceMetalBox;
  }

  public boolean isDirty() {
    return dirty;
  }

  public IIdentificate getCategory() {
    return SYSTEM_CATEGORY;
  }
}