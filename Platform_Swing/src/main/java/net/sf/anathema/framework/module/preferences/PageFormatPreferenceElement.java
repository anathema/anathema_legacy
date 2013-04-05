package net.sf.anathema.framework.module.preferences;

import net.sf.anathema.framework.presenter.action.preferences.IPreferencesElement;
import net.sf.anathema.framework.reporting.pdf.PageSize;
import net.sf.anathema.framework.view.EnumSelectCellRenderer;
import net.sf.anathema.initialization.PreferenceElement;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identified;

import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static net.sf.anathema.framework.presenter.action.preferences.IAnathemaPreferencesConstants.PAGE_FORMAT_PREFERENCE;
import static net.sf.anathema.framework.reporting.pdf.PageSize.Letter;

@PreferenceElement
@Weight(weight = 20)
public class PageFormatPreferenceElement implements IPreferencesElement {

  private PageSize pageFormat = loadPreference();

  private boolean dirty;
  private LabelledPreferenceComboBox<PageSize> box;

  @Override
  public void addComponent(JPanel panel, Resources resources) {
    String labelText = resources.getString("AnathemaReporting.Tools.Preferences.PageFormat") + ":";
    EnumSelectCellRenderer renderer = new EnumSelectCellRenderer("PageSize.", resources);
    box = new LabelledPreferenceComboBox<>(labelText, renderer, PageSize.values());
    box.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        PageSize selectedFormat = box.getSelectedItem();
        if (selectedFormat != pageFormat) {
          pageFormat = selectedFormat;
          dirty = true;
        }
      }
    });
    box.setSelectedItem(pageFormat);
    box.addTo(panel);
  }

  @Override
  public void savePreferences() {
    SYSTEM_PREFERENCES.put(PAGE_FORMAT_PREFERENCE, pageFormat.name());
  }

  public static PageSize loadPreference() {
    return PageSize.valueOf(SYSTEM_PREFERENCES.get(PAGE_FORMAT_PREFERENCE, Letter.name()));
  }

  @Override
  public void reset() {
    pageFormat = loadPreference();
    box.setSelectedItem(pageFormat);
    dirty = false;
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
}
