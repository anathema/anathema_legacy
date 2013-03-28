package net.sf.anathema.framework.presenter.view;

import net.sf.anathema.lib.gui.ui.ObjectUi;
import net.sf.anathema.lib.workflow.wizard.selection.IWizardFactory;

import javax.swing.Icon;

public interface IItemTypeViewProperties {

  Icon getIcon();

  IWizardFactory getNewItemWizardFactory();

  String getLabelKey();

  ObjectUi<Object> getItemTypeUI();
}