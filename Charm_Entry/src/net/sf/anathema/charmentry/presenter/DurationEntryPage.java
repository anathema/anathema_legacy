package net.sf.anathema.charmentry.presenter;

import javax.swing.JRadioButton;

import net.disy.commons.core.message.IBasicMessage;
import net.disy.commons.swing.dialog.core.IPageContent;
import net.sf.anathema.character.generic.magic.charms.duration.SimpleDuration;
import net.sf.anathema.charmentry.module.ICharmEntryViewFactory;
import net.sf.anathema.charmentry.presenter.model.ICharmEntryModel;
import net.sf.anathema.charmentry.presenter.model.IDurationEntryModel;
import net.sf.anathema.charmentry.presenter.view.IDurationEntryView;
import net.sf.anathema.charmentry.properties.DurationPageProperties;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.wizard.AbstractAnathemaWizardPage;
import net.sf.anathema.lib.gui.wizard.workflow.CheckInputListener;
import net.sf.anathema.lib.gui.wizard.workflow.ICondition;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public class DurationEntryPage extends AbstractAnathemaWizardPage {

  private final IResources resources;
  private final ICharmEntryModel model;
  private final ICharmEntryViewFactory viewFactory;
  private DurationPageProperties properties;
  private IDurationEntryView view;
  private boolean qualifiedAmountDuration;

  public DurationEntryPage(IResources resources, ICharmEntryModel model, ICharmEntryViewFactory viewFactory) {
    this.resources = resources;
    this.properties = new DurationPageProperties(resources);
    this.model = model;
    this.viewFactory = viewFactory;
  }

  @Override
  protected void addFollowUpPages(CheckInputListener inputListener) {
    addFollowupPage(new PrerequisitesEntryPage(resources, model, viewFactory), inputListener, new ICondition() {
      public boolean isFulfilled() {
        return getPageModel().isDurationComplete();
      }
    });
    addFollowupPage(new QualifiedAmountDurationPage(resources, model, viewFactory), inputListener, new ICondition() {
      public boolean isFulfilled() {
        return qualifiedAmountDuration;
      }
    });
  }

  @Override
  protected void initModelListening(CheckInputListener inputListener) {
    getPageModel().addModelListener(inputListener);
  }

  @Override
  protected void initPageContent() {
    this.view = viewFactory.createDurationView();
    final JRadioButton instantButton = view.addRadioButton(properties.getInstantString());
    final ITextView simpleDurationView = view.addRadioButtonTextField(properties.getSimpleDurationString());
    simpleDurationView.addTextChangedListener(new IObjectValueChangedListener<String>() {
      public void valueChanged(String newValue) {
        getPageModel().setSimpleDuration(newValue);
      }
    });
    final ITextView untilView = view.addRadioButtonTextField(properties.getUntilString());
    untilView.addTextChangedListener(new IObjectValueChangedListener<String>() {
      public void valueChanged(String newValue) {
        getPageModel().setUntilDuration(newValue);
      }
    });
    final JRadioButton amountButton = view.addRadioButton(properties.getQualifiedAmountDurationString());
    view.addTypeChangeListener(new IChangeListener() {
      public void changeOccurred() {
        qualifiedAmountDuration = amountButton.isSelected();
        getPageModel().clearDuration();
        simpleDurationView.setText(null);
        untilView.setText(null);
        if (instantButton.isSelected()) {
          getPageModel().setSimpleDuration(SimpleDuration.INSTANT);
        }
      }
    });
  }

  private IDurationEntryModel getPageModel() {
    return model.getDurationModel();
  }

  public boolean canFinish() {
    return false;
  }

  public String getDescription() {
    return properties.getDurationPageTitle();
  }

  public IBasicMessage getMessage() {
    return properties.getBasicMessage();
  }

  public IPageContent getPageContent() {
    return view;
  }
}