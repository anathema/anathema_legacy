package net.sf.anathema.framework.module.preferences;

import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.GridDialogLayoutDataFactory;
import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.framework.configuration.AnathemaPreferences;
import net.sf.anathema.framework.presenter.action.preferences.IPreferencesElement;
import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import static net.sf.anathema.framework.presenter.action.preferences.IAnathemaPreferencesConstants.USER_LOOK_AND_FEEL_CLASSNAME;

public class LookAndFeelPreferencesElement implements IPreferencesElement {
  private LookAndFeelItem selected;
  private JComboBox combo;
  private JTextField customLaf;
  private boolean allowCustom;

  public LookAndFeelPreferencesElement() {
    this.selected = null;
    this.combo = null;
    this.customLaf = null;
    this.allowCustom = false;
  }

  private static boolean compareClassNames(String name1, String name2) {
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

    if (allowCustom && customLaf != null) {
      customLaf.setText(selected != null ? selected.getClassName() : className);
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

  private void updateSelectedClassName() {
    if (allowCustom) {
      String className = selected != null ? selected.getClassName() : null;
      customLaf.setText(className != null ? className : getLookAndFeelClassName());
      customLaf.setEnabled(selected == null || selected.isCustom());
    }
  }

  private IDialogComponent getComponent(final IResources resources) {
    allowCustom = "true".equals(resources.getString("AnathemaCore.Tools.Preferences.AllowCustomLAF").trim().toLowerCase(Locale.US));
    final JLabel label = new JLabel(resources.getString("AnathemaCore.Tools.Preferences.LookAndFeelCaption")); //$NON-NLS-1$

    // This implementation is a bit nasty but I (kelemen@github.com) was not sure
    // about the intended contract of some methods. So to be on the safe side,
    // I simply mimicked the previous implementation.

    combo = new JComboBox();
    combo.addActionListener(new LookAndFeelChangeListener(resources));

    if (allowCustom) {
      customLaf = new JTextField(25);

      customLaf.getDocument().addDocumentListener(new CustomLookAndFeelListener(resources));
    }

    List<LookAndFeelItem> items = new LinkedList<LookAndFeelItem>();
    if (allowCustom) {
      items.add(new LookAndFeelItem(resources));
    }
    for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
      items.add(new LookAndFeelItem(resources, info.getName(), info.getClassName()));
    }

    combo.setModel(new DefaultComboBoxModel(items.toArray()));
    selectCurrentSettings();

    return new LookAndFeelDialogComponent(label);
  }

  @Override
  public void addCompoment(IGridDialogPanel panel, IResources resources) {
    panel.add(getComponent(resources));
  }

  private String getSelectedClassName() {
    LookAndFeelItem currentSelected = selected;
    return currentSelected != null
            ? currentSelected.getClassName()
            : getLookAndFeelClassName();
  }

  public void savePreferences() {
    String selectedClass = getSelectedClassName();
    if (selectedClass != null) {
      SYSTEM_PREFERENCES.put(USER_LOOK_AND_FEEL_CLASSNAME, selectedClass);
    } else {
      SYSTEM_PREFERENCES.remove(USER_LOOK_AND_FEEL_CLASSNAME);
    }
  }

  private static String getLookAndFeelClassName() {
    return AnathemaPreferences.getDefaultPreferences().getUserLookAndFeel();
  }

  private static String resolveLookAndFeelClassName(String className) {
    return className != null ? className : UIManager.getSystemLookAndFeelClassName();
  }

  public IIdentificate getCategory() {
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
    return !compareClassNames(
            resolveLookAndFeelClassName(currentSelected.getClassName()),
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

  private class LookAndFeelDialogComponent implements IDialogComponent {

    private final JLabel label;

    public LookAndFeelDialogComponent(JLabel label) {
      this.label = label;
    }

    public int getColumnCount() {
      return 2;
    }

    public void fillInto(JPanel panel, int columnCount) {
      panel.add(label);
      if (allowCustom) {
        panel.add(combo, GridDialogLayoutDataFactory.createHorizontalSpanData(columnCount - 1));
        panel.add(customLaf, GridDialogLayoutDataFactory.createHorizontalSpanData(
                columnCount,
                GridDialogLayoutData.FILL_HORIZONTAL));
      } else {
        panel.add(combo);
      }
    }
  }

  private class CustomLookAndFeelListener implements DocumentListener {
    private final IResources resources;

    public CustomLookAndFeelListener(IResources resources) {
      this.resources = resources;
    }

    private void onChange() {
      LookAndFeelItem currentSelection = (LookAndFeelItem) combo.getSelectedItem();
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
  }

  private class LookAndFeelChangeListener implements ActionListener {
    private final IResources resources;

    public LookAndFeelChangeListener(IResources resources) {
      this.resources = resources;
    }

    public void actionPerformed(ActionEvent e) {
      selected = (LookAndFeelItem) combo.getSelectedItem();
      if (selected != null && selected.getClassName() == null) {
        selected = new LookAndFeelItem(resources, null, getLookAndFeelClassName());
      }
      updateSelectedClassName();
    }
  }
}