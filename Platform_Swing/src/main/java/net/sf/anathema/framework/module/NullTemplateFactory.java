package net.sf.anathema.framework.module;

import net.sf.anathema.lib.gui.dialog.userdialog.page.IDialogPage;
import net.sf.anathema.lib.workflow.wizard.selection.DialogBasedTemplateFactory;
import net.sf.anathema.lib.workflow.wizard.selection.IDialogModelTemplate;

public class NullTemplateFactory implements DialogBasedTemplateFactory {

  @Override
  public IDialogPage createPage(IDialogModelTemplate template) {
    return null;
  }

  @Override
  public IDialogModelTemplate createTemplate() {
    return null;
  }

  @Override
  public boolean needsFurtherDetails() {
    return false;
  }
}