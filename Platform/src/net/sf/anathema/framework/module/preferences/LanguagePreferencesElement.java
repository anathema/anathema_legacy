package net.sf.anathema.framework.module.preferences;

import static net.sf.anathema.framework.presenter.action.preferences.IAnathemaPreferencesConstants.DEFAULT_LOCALE;
import static net.sf.anathema.framework.presenter.action.preferences.IAnathemaPreferencesConstants.LOCALE_PREFERENCE;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.framework.presenter.action.SupportedLocale;
import net.sf.anathema.framework.presenter.action.preferences.IPreferencesElement;
import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;

public class LanguagePreferencesElement implements IPreferencesElement {

  private SupportedLocale locale = SupportedLocale.valueOf(SYSTEM_PREFERENCES.get(LOCALE_PREFERENCE, DEFAULT_LOCALE));
  private boolean dirty;
  private JComboBox languageBox;

  public void addCompoment(IGridDialogPanel panel, IResources resources) {
    panel.add(getComponent(resources));
  }

  private IDialogComponent getComponent(IResources resources) {
    final JLabel languageLabel = new JLabel(resources.getString("AnathemaCore.Tools.Preferences.Language") + ":"); //$NON-NLS-1$ //$NON-NLS-2$
    languageBox = new JComboBox(SupportedLocale.values());
    languageBox.setRenderer(new DefaultListCellRenderer() {
      @Override
      public Component getListCellRendererComponent(
          JList list,
          Object value,
          int index,
          boolean isSelected,
          boolean cellHasFocus) {
        SupportedLocale displayLocale = (SupportedLocale) value;
        String displayString = displayLocale.getLocale().getDisplayLanguage(locale.getLocale());
        char oldFirstChar = displayString.charAt(0);
        char firstChar = Character.toUpperCase(oldFirstChar);
        displayString = firstChar + displayString.substring(1);
        return super.getListCellRendererComponent(list, displayString, index, isSelected, cellHasFocus);
      }
    });
    languageBox.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (languageBox.getSelectedItem() != locale) {
          locale = (SupportedLocale) languageBox.getSelectedItem();
          dirty = true;
        }
      }
    });
    languageBox.setSelectedItem(locale);
    return new IDialogComponent() {

      public int getColumnCount() {
        return 2;
      }

      public void fillInto(JPanel panel, int columnCount) {
        panel.add(languageLabel);
        panel.add(languageBox);
      }
    };
  }

  public void savePreferences() {
    SYSTEM_PREFERENCES.put(LOCALE_PREFERENCE, locale.name());
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
    locale = SupportedLocale.valueOf(SYSTEM_PREFERENCES.get(LOCALE_PREFERENCE, DEFAULT_LOCALE));
    languageBox.setSelectedItem(locale);
    dirty = false;
  }
}