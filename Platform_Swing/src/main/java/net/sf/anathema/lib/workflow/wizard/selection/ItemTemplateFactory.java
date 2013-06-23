package net.sf.anathema.lib.workflow.wizard.selection;

public interface ItemTemplateFactory {

  IDialogModelTemplate NO_TEMPLATE = null;

  IDialogModelTemplate createTemplate();
}