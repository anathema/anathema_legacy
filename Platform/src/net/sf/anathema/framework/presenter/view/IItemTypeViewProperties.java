package net.sf.anathema.framework.presenter.view;

import net.sf.anathema.lib.gui.ui.IObjectUi;
import net.sf.anathema.lib.workflow.wizard.selection.IWizardFactory;

import javax.swing.Icon;

public interface IItemTypeViewProperties {

  public Icon getIcon();

  public IWizardFactory getNewItemWizardFactory();

  public String getLabelKey();

  public IObjectUi<Object> getItemTypeUI();
}