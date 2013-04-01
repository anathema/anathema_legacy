package net.sf.anathema.framework.presenter.view;

import net.sf.anathema.lib.gui.ui.ObjectUi;
import net.sf.anathema.lib.workflow.wizard.selection.DialogBasedTemplateFactory;

import javax.swing.Icon;

public interface IItemTypeViewProperties {

  Icon getIcon();

  DialogBasedTemplateFactory getNewItemWizardFactory();

  String getLabelKey();

  ObjectUi<Object> getItemTypeUI();
}