package net.sf.anathema.framework.repository;

import net.sf.anathema.framework.IDialogProperties;
import net.sf.anathema.lib.resources.Resources;

public class AnathemaDialogProperties implements IDialogProperties {

  private final Resources resources;

  public AnathemaDialogProperties(Resources resources) {
    this.resources = resources;
  }

  @Override
  public String getOkButtonText() {
    return resources.getString("UserDialog.OkayButton.Text"); //$NON-NLS-1$
  }

  @Override
  public String getCancelButtonText() {
    return resources.getString("UserDialog.CancelButton.Text"); //$NON-NLS-1$
  }

  @Override
  public String getHelpButtonText() {
    return resources.getString("UserDialog.HelpButton.Text"); //$NON-NLS-1$
  }

  @Override
  public String getFoldOutButtonText() {
    return resources.getString("FoldoutDialog.Foldout.Button.Text"); //$NON-NLS-1$
  }

  @Override
  public String getFoldInButtonText() {
    return resources.getString("FoldoutDialog.Foldin.Button.Text"); //$NON-NLS-1$
  }

  protected final Resources getResources() {
    return resources;
  }
}
