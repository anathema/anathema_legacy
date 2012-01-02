package net.sf.anathema.lib.gui.wizard;

import java.util.HashMap;
import java.util.Map;

import net.disy.commons.swing.dialog.wizard.IBasicWizardPage;
import net.sf.anathema.lib.gui.wizard.workflow.CheckInputListener;
import net.sf.anathema.lib.gui.wizard.workflow.ICondition;

public abstract class AbstractAnathemaWizardPage implements IAnathemaWizardPage {

  private IAnathemaWizardPage previousPage;
  protected final Map<ICondition, IAnathemaWizardPage> followUpPagesByCondition = new HashMap<ICondition, IAnathemaWizardPage>();

  public boolean canFlipToNextPage() {
    return getNextPage() != null;
  }

  public String getTitle() {
    return getDescription();
  }

  public void performHelp() {
    //Nothing to do
  }

  public boolean isHelpAvailable() {
    return false;
  }

  public final IAnathemaWizardPage getPreviousPage() {
    return previousPage;
  }

  protected final void addFollowupPage(IAnathemaWizardPage page, CheckInputListener inputListener, ICondition condition) {
    followUpPagesByCondition.put(condition, page);
    page.initPresentation(inputListener);
    //page.setPreviousPage(this);
  }

  public final IBasicWizardPage getNextPage() {
    for (ICondition condition : followUpPagesByCondition.keySet()) {
      if (condition.isFulfilled()) {
    	  IBasicWizardPage page = followUpPagesByCondition.get(condition);
    	  if (page instanceof IAnathemaWizardPage)
    		  ((IAnathemaWizardPage)page).setPreviousPage(this);
          return page;
      }
    }
    return null;
  }

  public final void setPreviousPage(IAnathemaWizardPage page) {
    this.previousPage = page;
  }

  public final void initPresentation(CheckInputListener inputListener) {
    initModelListening(inputListener);
    addFollowUpPages(inputListener);
    initPageContent();
  }

  protected abstract void initPageContent();

  protected abstract void addFollowUpPages(CheckInputListener inputListener);

  protected abstract void initModelListening(CheckInputListener inputListener);
}