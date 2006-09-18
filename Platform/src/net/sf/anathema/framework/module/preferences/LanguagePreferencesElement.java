package net.sf.anathema.framework.module.preferences;

import static net.sf.anathema.framework.presenter.action.preferences.IAnathemaPreferencesConstants.DEFAULT_LOCALE;
import static net.sf.anathema.framework.presenter.action.preferences.IAnathemaPreferencesConstants.LOCALE_PREFERENCE;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.framework.presenter.action.NamedLocale;
import net.sf.anathema.framework.presenter.action.preferences.IPreferencesElement;
import net.sf.anathema.framework.view.IdentificateSelectCellRenderer;
import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;

public class LanguagePreferencesElement implements IPreferencesElement {

  private NamedLocale locale = NamedLocale.valueOf(SYSTEM_PREFERENCES.get(LOCALE_PREFERENCE, DEFAULT_LOCALE));
  private boolean dirty;
  private JComboBox languageBox;

  public void addCompoment(IGridDialogPanel panel, IResources resources) {
    panel.add(getComponent(resources));
  }

  private IDialogComponent getComponent(IResources resources) {
    final JLabel languageLabel = new JLabel(resources.getString("AnathemaCore.Tools.Preferences.Language") + ":"); //$NON-NLS-1$ //$NON-NLS-2$
    languageBox = new JComboBox(NamedLocale.values());
    languageBox.setRenderer(new IdentificateSelectCellRenderer("AnathemaCore.Tools.Preferences.Language.", resources)); //$NON-NLS-1$
    languageBox.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (languageBox.getSelectedItem() != locale) {
          locale = (NamedLocale) languageBox.getSelectedItem();
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
    SYSTEM_PREFERENCES.put(LOCALE_PREFERENCE, locale.getId());
  }

  public boolean isDirty() {
    return dirty;
  }

  public IIdentificate getCategory() {
    return SYSTEM_CATEGORY;
  }

  public void reset() {
    locale = NamedLocale.valueOf(SYSTEM_PREFERENCES.get(LOCALE_PREFERENCE, DEFAULT_LOCALE));
    languageBox.setSelectedItem(locale);
    dirty = false;
  }
}