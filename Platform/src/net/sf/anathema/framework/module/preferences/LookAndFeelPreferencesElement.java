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
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.metal.MetalLookAndFeel;

import net.disy.commons.swing.layout.GridDialogLayoutDataUtilities;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.framework.presenter.action.preferences.IPreferencesElement;
import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;

public class LookAndFeelPreferencesElement implements IPreferencesElement {
  private LookAndFeelItem selected;
  private JComboBox combo;
  private JTextField customLaf;
  
  public LookAndFeelPreferencesElement() {
    this.selected = null;
    this.combo = null;
    this.customLaf = null;
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

    JTextField currentText = customLaf;
    JComboBox currentCombo = combo;
    if (currentCombo == null || currentText == null) {
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

    if (selectIndex < 0) {
      for (int i = 0; i < itemCount; i++) {
        LookAndFeelItem item = (LookAndFeelItem)currentCombo.getItemAt(i);
        if (item.getClassName() == null) {
          selectIndex = i;
        }
      }
    }

    if (selectIndex >= 0) {
      currentCombo.setSelectedIndex(selectIndex);
    }

    currentText.setText(className);
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
  
  private IDialogComponent getComponent(final IResources resources) {
    final JLabel label = new JLabel(resources.getString("AnathemaCore.Tools.Preferences.LookAndFeelCaption")); //$NON-NLS-1$
    customLaf = new JTextField(25);
    combo = new JComboBox();
    
    combo.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        boolean enableEdit = false;
        selected = (LookAndFeelItem)combo.getSelectedItem();
        if (selected != null) {
          String className = selected.getClassName();
          if (className != null) {
            customLaf.setText(className);
          }
          else {
            customLaf.setText(getLookAndFeelClassName());
            selected = new LookAndFeelItem(resources, null, customLaf.getText().trim());
            enableEdit = true;
          }
        }
        customLaf.setEnabled(enableEdit);
      }
    });
    
    customLaf.getDocument().addDocumentListener(new DocumentListener() {
      private void onChange() {
        LookAndFeelItem currentSelection = (LookAndFeelItem)combo.getSelectedItem();
        if (currentSelection != null) {
          if (currentSelection.getClassName() == null) {
            selected = new LookAndFeelItem(resources, null, customLaf.getText().trim());
          }
        }
      }
      
      @Override
      public void removeUpdate(DocumentEvent arg0) {
        onChange();
      }
      
      @Override
      public void insertUpdate(DocumentEvent arg0) {
        onChange();
      }
      
      @Override
      public void changedUpdate(DocumentEvent arg0) {
        onChange();
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
        panel.add(combo, GridDialogLayoutDataUtilities.createHorizontalSpanData(columnCount - 1));
        panel.add(customLaf, GridDialogLayoutDataUtilities.createHorizontalSpanData(
            columnCount,
            GridDialogLayoutData.FILL_HORIZONTAL));
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
      this.name = name != null 
          ? name 
          : resources.getString("AnathemaCore.Tools.Preferences.CustomLookAndFeel"); //$NON-NLS-1$
      this.className = !"".equals(className) ? className : null;
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