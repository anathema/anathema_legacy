package net.sf.anathema.framework.module.preferences;

import net.sf.anathema.framework.presenter.action.SupportedLocale;
import net.sf.anathema.framework.presenter.action.preferences.IPreferencesElement;
import net.sf.anathema.initialization.PreferenceElement;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identified;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static net.sf.anathema.framework.presenter.action.preferences.IAnathemaPreferencesConstants.DEFAULT_LOCALE;
import static net.sf.anathema.framework.presenter.action.preferences.IAnathemaPreferencesConstants.LOCALE_PREFERENCE;

@PreferenceElement
@Weight(weight = 0)
public class LanguagePreferencesElement implements IPreferencesElement {
  private SupportedLocale locale = SupportedLocale.valueOf(SYSTEM_PREFERENCES.get(LOCALE_PREFERENCE, DEFAULT_LOCALE));
  private boolean dirty;
  private JComboBox languageBox;

  @SuppressWarnings("unchecked")
  @Override
  public void addComponent(JPanel panel, Resources resources) {
    JLabel languageLabel = new JLabel(
            resources.getString("AnathemaCore.Tools.Preferences.Language") + ":");
    languageBox = new JComboBox(SupportedLocale.values());
    languageBox.setRenderer(new DefaultListCellRenderer() {
      @Override
      public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
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
      @Override
      public void actionPerformed(ActionEvent e) {
        if (languageBox.getSelectedItem() != locale) {
          locale = (SupportedLocale) languageBox.getSelectedItem();
          dirty = true;
        }
      }
    });
    languageBox.setSelectedItem(locale);
    panel.add(languageLabel);
    panel.add(languageBox);
  }

  @Override
  public void savePreferences() {
    SYSTEM_PREFERENCES.put(LOCALE_PREFERENCE, locale.name());
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
  public Identified getCategory() {
    return SYSTEM_CATEGORY;
  }

  @Override
  public void reset() {
    locale = SupportedLocale.valueOf(SYSTEM_PREFERENCES.get(LOCALE_PREFERENCE, DEFAULT_LOCALE));
    languageBox.setSelectedItem(locale);
    dirty = false;
  }
}
