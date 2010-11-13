package net.sf.anathema.framework.repository;

import net.sf.anathema.framework.IDialogProperties;
import net.sf.anathema.lib.resources.IResources;

public class AnathemaDialogProperties implements IDialogProperties {

  private final IResources resources;

  public AnathemaDialogProperties(IResources resources) {
    this.resources = resources;
  }

  public String getOkButtonText() {
    return resources.getString("UserDialog.OkayButton.Text"); //$NON-NLS-1$
  }

  public String getCancelButtonText() {
    return resources.getString("UserDialog.CancelButton.Text"); //$NON-NLS-1$
  }

  public String getHelpButtonText() {
    return resources.getString("UserDialog.HelpButton.Text"); //$NON-NLS-1$
  }

  public String getFoldOutButtonText() {
    return resources.getString("FoldoutDialog.Foldout.Button.Text"); //$NON-NLS-1$
  }

  public String getFoldInButtonText() {
    return resources.getString("FoldoutDialog.Foldin.Button.Text"); //$NON-NLS-1$
  }

  protected final IResources getResources() {
    return resources;
  }
}
