package net.sf.anathema.charmentry.demo.page;

import net.disy.commons.core.message.IBasicMessage;
import net.disy.commons.swing.dialog.core.IPageContent;
import net.sf.anathema.charmentry.demo.ICharmEntryModel;
import net.sf.anathema.charmentry.demo.ICharmEntryViewFactory;
import net.sf.anathema.lib.gui.wizard.AbstractAnathemaWizardPage;
import net.sf.anathema.lib.gui.wizard.workflow.CheckInputListener;
import net.sf.anathema.lib.resources.IResources;

public class PrerequisiteCharmsPage extends AbstractAnathemaWizardPage {

  public PrerequisiteCharmsPage(IResources resources, ICharmEntryModel model, ICharmEntryViewFactory viewFactory) {
    // TODO Auto-generated constructor stub
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
    return true;
  }

  public String getDescription() {
    return null;
  }

  public IBasicMessage getMessage() {
    // TODO Auto-generated method stub
    return null;
  }

  public IPageContent getPageContent() {
    return null;
  }
}