package net.sf.anathema.charmentry.presenter;

import net.disy.commons.core.message.IBasicMessage;
import net.disy.commons.swing.dialog.core.IPageContent;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.charmentry.module.ICharmEntryViewFactory;
import net.sf.anathema.charmentry.presenter.model.ICharmEntryModel;
import net.sf.anathema.charmentry.presenter.model.ICostEntryModel;
import net.sf.anathema.charmentry.presenter.view.ICostEntryPageView;
import net.sf.anathema.charmentry.presenter.view.ICostEntryView;
import net.sf.anathema.charmentry.properties.CostEntryProperties;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.wizard.AbstractAnathemaWizardPage;
import net.sf.anathema.lib.gui.wizard.workflow.CheckInputListener;
import net.sf.anathema.lib.gui.wizard.workflow.ICondition;
import net.sf.anathema.lib.resources.IResources;

public class CostEntryPage extends AbstractAnathemaWizardPage {

  private final IResources resources;
  private final ICharmEntryModel model;
  private final ICharmEntryViewFactory viewFactory;
  private final CostEntryProperties properties;
  private ICostEntryPageView view;

  public CostEntryPage(IResources resources, ICharmEntryModel model, ICharmEntryViewFactory viewFactory) {
    this.resources = resources;
    this.properties = new CostEntryProperties(resources);
    this.model = model;
    this.viewFactory = viewFactory;
  }

  @Override
  protected void addFollowUpPages(CheckInputListener inputListener) {
    addFollowupPage(new PrerequisiteCharmsPage(resources, model, viewFactory), inputListener, new ICondition() {
      public boolean isFullfilled() {
        return getPageModel().getEdition() == ExaltedEdition.FirstEdition;
      }
    });
    addFollowupPage(
        new SecondEditionPrerequisiteCharmsPage(resources, model, viewFactory),
        inputListener,
        new ICondition() {
          public boolean isFullfilled() {
            return getPageModel().getEdition() == ExaltedEdition.SecondEdition;
          }
        });
  }

  @Override
  protected void initModelListening(CheckInputListener inputListener) {
    // nothing to do
  }

  @Override
  protected void initPageContent() {
    this.view = viewFactory.createCostEntryView();
    ICostEntryView essenceView = view.addCostEntryView(
        properties.getEssenceLabel(),
        properties.getCostLabel(),
        properties.getTextLabel());
    essenceView.getValueView().addTextChangedListener(new IObjectValueChangedListener<String>() {
      public void valueChanged(String newValue) {
        getPageModel().setEssenceCostValue(newValue);
      }
    });
    essenceView.getTextView().addTextChangedListener(new IObjectValueChangedListener<String>() {
      public void valueChanged(String newValue) {
        getPageModel().setEssenceCostText(newValue);
      }
    });
    ICostEntryView willpowerView = view.addCostEntryView(
        properties.getWillpowerLabel(),
        properties.getCostLabel(),
        properties.getTextLabel());
    willpowerView.getValueView().addTextChangedListener(new IObjectValueChangedListener<String>() {
      public void valueChanged(String newValue) {
        getPageModel().setWillpowerCostValue(newValue);
      }
    });
    willpowerView.getTextView().addTextChangedListener(new IObjectValueChangedListener<String>() {
      public void valueChanged(String newValue) {
        getPageModel().setWillpowerCostText(newValue);
      }
    });
    ICostEntryView healthView = view.addCostEntryView(
        properties.getHealthLabel(),
        properties.getCostLabel(),
        properties.getTextLabel());
    healthView.getValueView().addTextChangedListener(new IObjectValueChangedListener<String>() {
      public void valueChanged(String newValue) {
        getPageModel().setHealthCostValue(newValue);
      }
    });
    healthView.getTextView().addTextChangedListener(new IObjectValueChangedListener<String>() {
      public void valueChanged(String newValue) {
        getPageModel().setHealthCostText(newValue);
      }
    });
    ICostEntryView experienceView = view.addCostEntryView(
        properties.getExperienceLabel(),
        properties.getCostLabel(),
        properties.getTextLabel());
    experienceView.getValueView().addTextChangedListener(new IObjectValueChangedListener<String>() {
      public void valueChanged(String newValue) {
        getPageModel().setXpCostValue(newValue);
      }
    });
    experienceView.getTextView().addTextChangedListener(new IObjectValueChangedListener<String>() {
      public void valueChanged(String newValue) {
        getPageModel().setXpCostText(newValue);
      }
    });
  }

  private ICostEntryModel getPageModel() {
    return model.getCostEntryModel();
  }

  public boolean canFinish() {
    return true;
  }

  public String getDescription() {
    return properties.getCostTitle();
  }

  public IBasicMessage getMessage() {
    return properties.getCostMessage();
  }

  public IPageContent getPageContent() {
    return view;
  }
}