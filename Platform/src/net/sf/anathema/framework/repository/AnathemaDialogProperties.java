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
    return resources.getString("UserDialog.OkayButton.Text");
  }

  @Override
  public String getCancelButtonText() {
    return resources.getString("UserDialog.CancelButton.Text");
  }

  @Override
  public String getHelpButtonText() {
    return resources.getString("UserDialog.HelpButton.Text");
  }

  @Override
  public String getFoldOutButtonText() {
    return resources.getString("FoldoutDialog.Foldout.Button.Text");
  }

  @Override
  public String getFoldInButtonText() {
    return resources.getString("FoldoutDialog.Foldin.Button.Text");
  }

  protected final Resources getResources() {
    return resources;
  }
}
