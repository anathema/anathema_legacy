package net.sf.anathema.lib.workflow.wizard.selection;

import net.sf.anathema.lib.gui.dialog.userdialog.page.IDialogPage;

public interface DialogBasedTemplateFactory {

  boolean needsFurtherDetails();

  IDialogPage createPage(IDialogModelTemplate template);

  IDialogModelTemplate createTemplate();
}