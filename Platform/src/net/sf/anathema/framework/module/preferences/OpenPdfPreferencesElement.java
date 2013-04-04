package net.sf.anathema.framework.module.preferences;

import net.sf.anathema.initialization.PreferenceElement;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identified;

import javax.swing.JPanel;

import static net.sf.anathema.framework.presenter.action.preferences.IAnathemaPreferencesConstants.OPEN_PDF_PREFERENCE;
import static net.sf.anathema.framework.reporting.AbstractPrintAction.isAutoOpenSupported;

@PreferenceElement
@Weight(weight = 30)
public class OpenPdfPreferencesElement extends AbstractCheckBoxPreferencesElement {

  private boolean openPdf = SYSTEM_PREFERENCES.getBoolean(OPEN_PDF_PREFERENCE, true);

  @Override
  public void addComponent(JPanel panel, Resources resources) {
    if (isAutoOpenSupported()) {
      super.addComponent(panel, resources);
    }
  }

  @Override
  protected boolean getBooleanParameter() {
    return openPdf;
  }

  public static boolean openDocumentAfterPrint() {
    return SYSTEM_PREFERENCES.getBoolean(OPEN_PDF_PREFERENCE, true);
  }

  @Override
  public void savePreferences() {
    SYSTEM_PREFERENCES.putBoolean(OPEN_PDF_PREFERENCE, openPdf);
  }

  @Override
  protected void setValue(boolean value) {
    openPdf = value;
  }

  @Override
  protected String getLabelKey() {
    return "AnathemaCore.Tools.Preferences.OpenPdf"; //$NON-NLS-1$
  }

  @Override
  protected void resetValue() {
    openPdf = SYSTEM_PREFERENCES.getBoolean(OPEN_PDF_PREFERENCE, true);
  }

  @Override
  public Identified getCategory() {
    return SYSTEM_CATEGORY;
  }
}