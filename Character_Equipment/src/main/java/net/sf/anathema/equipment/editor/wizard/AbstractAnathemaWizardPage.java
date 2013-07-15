package net.sf.anathema.equipment.editor.wizard;

import net.sf.anathema.lib.data.Condition;
import net.sf.anathema.lib.message.BasicMessage;
import net.sf.anathema.lib.message.IBasicMessage;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractAnathemaWizardPage implements IAnathemaWizardPage {

  private IBasicMessage message = new BasicMessage("!!! UNSET-MESSAGE !!!");
  private IAnathemaWizardPage previousPage = null;
  protected final Map<Condition, IAnathemaWizardPage> followUpPagesByCondition = new HashMap<>();

  @Override
  public boolean canFlipToNextPage() {
    return getNextPage() != null;
  }

  @Override
  public final IAnathemaWizardPage getPreviousPage() {
    return previousPage;
  }

  protected final void addFollowupPage(IAnathemaWizardPage page, CheckInputListener inputListener, Condition condition) {
    followUpPagesByCondition.put(condition, page);
    page.initPresentation(inputListener);
  }

  @Override
  public final IWizardPage getNextPage() {
    for (Condition condition : followUpPagesByCondition.keySet()) {
      if (condition.isFulfilled()) {
        IAnathemaWizardPage page = followUpPagesByCondition.get(condition);
        if (page != null) {
          page.setPreviousPage(this);
        }
        return page;
      }
    }
    return null;
  }

  @Override
  public final void setPreviousPage(IAnathemaWizardPage page) {
    this.previousPage = page;
  }

  @Override
  public final void initPresentation(CheckInputListener inputListener) {
    initModelListening(inputListener);
    addFollowUpPages(inputListener);
    initPageContent();
  }

  protected abstract void initPageContent();

  protected abstract void addFollowUpPages(CheckInputListener inputListener);

  protected abstract void initModelListening(CheckInputListener inputListener);

  @Override
  public String getTitle() {
    return getDescription();
  }

  @Override
  public IBasicMessage getMessage() {
    return message;
  }
}