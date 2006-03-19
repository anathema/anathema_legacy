package net.sf.anathema.framework.module.preferences;

import static net.sf.anathema.framework.presenter.action.preferences.IAnathemaPreferencesConstants.OPEN_PDF_PREFERENCE;

public class OpenPdfPreferencesElement extends AbstractLabelledComboBoxPreferencesElement {

  private boolean openPdf = SYSTEM_PREFERENCES.getBoolean(OPEN_PDF_PREFERENCE, true);

  @Override
  protected boolean getBooleanParameter() {
    return openPdf;
  }

  public static boolean openDocumentAfterPrint() {
    return SYSTEM_PREFERENCES.getBoolean(OPEN_PDF_PREFERENCE, true);
  }

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
}