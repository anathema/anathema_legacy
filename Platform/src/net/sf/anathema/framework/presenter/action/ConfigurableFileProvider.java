package net.sf.anathema.framework.presenter.action;

import net.sf.anathema.lib.workflow.wizard.selection.IAnathemaWizardModelTemplate;

import java.io.File;

public class ConfigurableFileProvider implements IAnathemaWizardModelTemplate, IFileProvider {

  private File file;

  public void setFile(File file) {
    this.file = file;
  }

  @Override
  public File getFile() {
    return file;
  }
}