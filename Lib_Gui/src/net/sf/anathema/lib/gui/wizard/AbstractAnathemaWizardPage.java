package net.sf.anathema.lib.gui.wizard;

import java.util.HashMap;
import java.util.Map;

import net.disy.commons.swing.dialog.wizard.IBasicWizardPage;
import net.sf.anathema.lib.gui.wizard.workflow.CheckInputListener;
import net.sf.anathema.lib.gui.wizard.workflow.ICondition;

public abstract class AbstractAnathemaWizardPage implements IAnathemaWizardPage {

  private IAnathemaWizardPage previousPage;
  private final Map<ICondition, IAnathemaWizardPage> followUpPagesByCondition = new HashMap<ICondition, IAnathemaWizardPage>();

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
    page.setPreviousPage(this);
  }

  public final IBasicWizardPage getNextPage() {
    for (ICondition condition : followUpPagesByCondition.keySet()) {
      if (condition.isFullfilled()) {
        return followUpPagesByCondition.get(condition);
      }
    }
    return null;
  }

  public final void setPreviousPage(IAnathemaWizardPage page) {
    this.previousPage = page;
  }
}