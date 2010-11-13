package net.sf.anathema.framework.presenter.view;

import javax.swing.Icon;

import net.disy.commons.swing.ui.IObjectUi;
import net.sf.anathema.lib.workflow.wizard.selection.IWizardFactory;

public interface IItemTypeViewProperties {

  public Icon getIcon();

  public IWizardFactory getNewItemWizardFactory();

  public String getLabelKey();

  public IObjectUi getItemTypeUI();
}