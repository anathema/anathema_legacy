package net.sf.anathema.framework.module.preferences;

import static net.sf.anathema.framework.presenter.action.preferences.IAnathemaPreferencesConstants.LOOK_AND_FEEL_PREFERENCE;
import static net.sf.anathema.framework.presenter.action.preferences.IAnathemaPreferencesConstants.USER_LOOK_AND_FEEL_CLASSNAME;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;

import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.framework.presenter.action.preferences.IPreferencesElement;
import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;

public class LookAndFeelPreferencesElement implements IPreferencesElement {
  private LookAndFeelItem selected;
  private JComboBox combo;
  
  public LookAndFeelPreferencesElement() {
    this.selected = null;
    this.combo = null;
  }
  
  private static boolean compareClassNames(String name1, String name2) {
    if (name1 == name2) {
      return true;
    }
    
    if (name1 == null || name2 == null) {
      return false;
    }
    
    return name1.equals(name2);
  }
  
  private void selectClass(String className) {
    assert SwingUtilities.isEventDispatchThread();
    
    JComboBox currentCombo = combo;
    if (currentCombo == null) {
      return;
    }
    
    int itemCount = currentCombo.getItemCount();
    int selectIndex = -1;
    for (int i = 0; i < itemCount; i++) {
      LookAndFeelItem item = (LookAndFeelItem)currentCombo.getItemAt(i);
      if (compareClassNames(className, item.getClassName())) {
        selected = item;
        selectIndex = i;
        break;
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
    }
    else {
      SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
          selectClass(className);
        }
      });
    }
  }
  
  private IDialogComponent getComponent(IResources resources) {
    final JLabel label = new JLabel(resources.getString("AnathemaCore.Tools.Preferences.LookAndFeelCaption")); //$NON-NLS-1$
    combo = new JComboBox();
    combo.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        selected = (LookAndFeelItem)combo.getSelectedItem();
      }
    });
    
    List<LookAndFeelItem> items = new LinkedList<LookAndFeelItem>();
    items.add(new LookAndFeelItem(resources));
    for (UIManager.LookAndFeelInfo info: UIManager.getInstalledLookAndFeels()) {
      items.add(new LookAndFeelItem(resources, info.getName(), info.getClassName()));
    }

    combo.setModel(new DefaultComboBoxModel(items.toArray()));
    selectCurrentSettings();

    return new IDialogComponent() {

      public int getColumnCount() {
        return 2;
      }

      public void fillInto(JPanel panel, int columnCount) {
        panel.add(label);
        panel.add(combo);
      }
    };
  }

  @Override
  public void addCompoment(IGridDialogPanel panel, IResources resources) {
    panel.add(getComponent(resources));
  }

  public void savePreferences() {
    LookAndFeelItem currentSelected = selected;
    String selectedClass = currentSelected != null 
        ? currentSelected.getClassName() 
        : getLookAndFeelClassName();
        
    if (selectedClass != null) {
      SYSTEM_PREFERENCES.put(USER_LOOK_AND_FEEL_CLASSNAME, selectedClass);
    }
    else {
      SYSTEM_PREFERENCES.remove(USER_LOOK_AND_FEEL_CLASSNAME);
    }
    SYSTEM_PREFERENCES.remove(LOOK_AND_FEEL_PREFERENCE);
  }

  private static String getLookAndFeelClassName() {
    String userClass = SYSTEM_PREFERENCES.get(USER_LOOK_AND_FEEL_CLASSNAME, null);
    if (userClass == null) {
      return SYSTEM_PREFERENCES.getBoolean(LOOK_AND_FEEL_PREFERENCE, false)
          ? MetalLookAndFeel.class.getName() 
          : null;
    }
    else {
      return userClass;
    }
  }

  public IIdentificate getCategory() {
    return SYSTEM_CATEGORY;
  }

  @Override
  public boolean isValid() {
    return true;
  }

  @Override
  public boolean isDirty() {
    LookAndFeelItem currentSelected = selected;
    if (currentSelected == null) {
      return false;
    }
    
    return !compareClassNames(currentSelected.getClassName(), getLookAndFeelClassName());
  }

  @Override
  public void reset() {
    selectCurrentSettings();
  }
  
  private static class LookAndFeelItem {
    private final String name;
    private final String className;
    
    public LookAndFeelItem(IResources resources) {
      this(resources, null, null);
    }
    
    public LookAndFeelItem(IResources resources, String name, String className) {
      if (name == null || className == null) {
        this.name = resources.getString("AnathemaCore.Tools.Preferences.DefaultLookAndFeel"); //$NON-NLS-1$
        this.className = null;
      }
      else {
        this.name = name;
        this.className = className;
      }
    }
    
    public String getName() {
      return name;
    }
    
    public String getClassName() {
      return className;
    }
    
    public String toString() {
      return getName();
    }
  }
}