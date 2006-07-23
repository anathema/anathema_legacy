package net.sf.anathema.demo.platform.item;

import net.disy.commons.core.message.IBasicMessage;
import net.disy.commons.swing.dialog.core.IPageContent;
import net.sf.anathema.lib.gui.wizard.AbstractAnathemaWizardPage;
import net.sf.anathema.lib.gui.wizard.workflow.CheckInputListener;
import net.sf.anathema.lib.resources.IResources;

public class SelectItemTypePage extends AbstractAnathemaWizardPage {

  private final SelectedItemTypeProperties properties;
  private final ISelectItemTypeView view;

  public SelectItemTypePage(IResources resources, INewItemWizardModel model, ISelectItemTypeView view) {
    this.view = view;
    this.properties = new SelectedItemTypeProperties(resources);
  }

  @Override
  protected void addFollowUpPages(CheckInputListener inputListener) {
    // TODO Auto-generated method stub
  }

  @Override
  protected void initModelListening(CheckInputListener inputListener) {
    // TODO Auto-generated method stub

  }

  @Override
  protected void initPageContent() {
    // TODO Auto-generated method stub

  }

  public boolean canFinish() {
    // TODO Auto-generated method stub
    return false;
  }

  public String getDescription() {
    // TODO Auto-generated method stub
    return null;
  }

  public IBasicMessage getMessage() {
    return null;
  }

  public IPageContent getPageContent() {
    // TODO Auto-generated method stub
    return null;
  }
}