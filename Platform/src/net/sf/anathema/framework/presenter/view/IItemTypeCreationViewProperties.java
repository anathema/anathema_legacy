package net.sf.anathema.framework.presenter.view;

import javax.swing.Icon;

import net.sf.anathema.lib.workflow.wizard.selection.IWizardFactory;

public interface IItemTypeCreationViewProperties {

  public Icon getIcon();

  public IWizardFactory getNewItemWizardFactory();

  public String getLabelKey();
}