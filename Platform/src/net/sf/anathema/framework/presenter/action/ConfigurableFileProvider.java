package net.sf.anathema.framework.presenter.action;

import net.sf.anathema.lib.workflow.wizard.selection.IDialogModelTemplate;

import java.io.File;

public class ConfigurableFileProvider implements IDialogModelTemplate, IFileProvider {

  private File file;

  public void setFile(File file) {
    this.file = file;
  }

  @Override
  public File getFile() {
    return file;
  }
}