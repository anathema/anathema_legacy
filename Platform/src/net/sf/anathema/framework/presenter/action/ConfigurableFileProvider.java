package net.sf.anathema.framework.presenter.action;

import java.io.File;

import net.sf.anathema.lib.workflow.wizard.selection.IAnathemaWizardModelTemplate;

public class ConfigurableFileProvider implements IAnathemaWizardModelTemplate, IFileProvider {

  private File file;

  public void setFile(File file) {
    this.file = file;
  }
  
  public File getFile() {
    return file;
  }
}