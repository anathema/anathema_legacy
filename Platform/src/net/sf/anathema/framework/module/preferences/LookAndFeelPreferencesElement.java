package net.sf.anathema.framework.module.preferences;

import net.sf.anathema.framework.configuration.InitializationPreferences;
import net.sf.anathema.framework.presenter.action.preferences.IPreferencesElement;
import net.sf.anathema.initialization.PreferenceElement;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.Identified;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import static net.sf.anathema.framework.presenter.action.preferences.IAnathemaPreferencesConstants.USER_LOOK_AND_FEEL_CLASSNAME;

@PreferenceElement
@Weight(weight = 10)
public class LookAndFeelPreferencesElement implements IPreferencesElement {
  private LookAndFeelItem selected;
  private JComboBox combo;

  public LookAndFeelPreferencesElement() {
    this.selected = null;
    this.combo = null;
  }

  private static boolean compareClassNames(String name1, String name2) {
    if (name1 == null || name2 == null) {
      return false;
    }
    return name1.equals(name2);
  }

  private void selectClass(String className) {
    JComboBox currentCombo = combo;
    if (currentCombo == null) {
      return;
    }
    selected = null;
    int itemCount = currentCombo.getItemCount();
    int selectIndex = -1;
    String resolvedClassName = resolveLookAndFeelClassName(className);
    for (int i = 0; i < itemCount; i++) {
      LookAndFeelItem item = (LookAndFeelItem) currentCombo.getItemAt(i);
      if (compareClassNames(resolvedClassName, item.getClassName())) {
        selected = item;
        selectIndex = i;
        break;
      }
    }
    if (selectIndex < 0) {
      for (int i = 0; i < itemCount; i++) {
        LookAndFeelItem item = (LookAndFeelItem) currentCombo.getItemAt(i);
        if (item.getClassName() == null) {
          selectIndex = i;
        }
      }
    }
    if (selectIndex >= 0) {
      currentCombo.setSelectedIndex(selectIndex);
    }
  }

  private void selectCurrentSettings() {
    final String className = getLookAndFeelClassName();
    if (SwingUtilities.isEventDispatchThread()) {
      selectClass(className);
    } else {
      SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
          selectClass(className);
        }
      });
    }
  }

  @Override
  public void addComponent(JPanel panel, IResources resources) {
    JLabel label = new JLabel(resources.getString("AnathemaCore.Tools.Preferences.LookAndFeelCaption")); //$NON-NLS-1$
    // This implementation is a bit nasty but I (kelemen@github.com) was not sure
    // about the intended contract of some methods. So to be on the safe side,
    // I simply mimicked the previous implementation.
    combo = new JComboBox();
    combo.addActionListener(new LookAndFeelChangeListener(resources));
    List<LookAndFeelItem> items = new LinkedList<>();
    for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
      items.add(new LookAndFeelItem(resources, info.getName(), info.getClassName()));
    }
    combo.setModel(new DefaultComboBoxModel(items.toArray()));
    selectCurrentSettings();
    panel.add(label);
    panel.add(combo);
  }

  private String getSelectedClassName() {
    LookAndFeelItem currentSelected = selected;
    return currentSelected != null ? currentSelected.getClassName() : getLookAndFeelClassName();
  }

  @Override
  public void savePreferences() {
    String selectedClass = getSelectedClassName();
    if (selectedClass != null) {
      SYSTEM_PREFERENCES.put(USER_LOOK_AND_FEEL_CLASSNAME, selectedClass);
    } else {
      SYSTEM_PREFERENCES.remove(USER_LOOK_AND_FEEL_CLASSNAME);
    }
  }

  private static String getLookAndFeelClassName() {
    return InitializationPreferences.getDefaultPreferences().getUserLookAndFeel();
  }

  private static String resolveLookAndFeelClassName(String className) {
    return className != null ? className : UIManager.getSystemLookAndFeelClassName();
  }

  @Override
  public Identified getCategory() {
    return SYSTEM_CATEGORY;
  }

  @Override
  public boolean isValid() {
    String className = getSelectedClassName();
    return className == null || isLookAndFeelClass(className);
  }

  @Override
  public boolean isDirty() {
    LookAndFeelItem currentSelected = selected;
    if (currentSelected == null) {
      return false;
    }
    return !compareClassNames(resolveLookAndFeelClassName(currentSelected.getClassName()),
            resolveLookAndFeelClassName(getLookAndFeelClassName()));
  }

  @Override
  public void reset() {
    selectCurrentSettings();
  }

  private boolean isLookAndFeelClass(String className) {
    try {
      Class<?> classOfLaf = Class.forName(className);
      return LookAndFeel.class.isAssignableFrom(classOfLaf);
    } catch (ClassNotFoundException e) {
      return false;
    }
  }

  private class LookAndFeelChangeListener implements ActionListener {
    private final IResources resources;

    public LookAndFeelChangeListener(IResources resources) {
      this.resources = resources;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      selected = (LookAndFeelItem) combo.getSelectedItem();
      if (selected != null && selected.getClassName() == null) {
        selected = new LookAndFeelItem(resources, null, getLookAndFeelClassName());
      }
    }
  }
}
