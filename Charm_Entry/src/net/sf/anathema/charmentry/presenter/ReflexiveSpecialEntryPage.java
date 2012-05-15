package net.sf.anathema.charmentry.presenter;

import net.disy.commons.swing.util.ToggleComponentEnabler;
import net.sf.anathema.charmentry.module.ICharmEntryViewFactory;
import net.sf.anathema.charmentry.presenter.model.ICharmEntryModel;
import net.sf.anathema.charmentry.presenter.model.IReflexiveSpecialsEntryModel;
import net.sf.anathema.charmentry.presenter.view.IReflexiveSpecialsView;
import net.sf.anathema.charmentry.properties.CharmTypeEntryPageProperties;
import net.sf.anathema.framework.value.IIntValueDisplay;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.control.IIntValueChangedListener;
import net.sf.anathema.lib.gui.dialog.core.IPageContent;
import net.sf.anathema.lib.gui.wizard.AbstractAnathemaWizardPage;
import net.sf.anathema.lib.gui.wizard.workflow.CheckInputListener;
import net.sf.anathema.lib.gui.wizard.workflow.ICondition;
import net.sf.anathema.lib.message.IBasicMessage;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.JToggleButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReflexiveSpecialEntryPage extends AbstractAnathemaWizardPage {

  private final IResources resources;
  private final ICharmEntryModel model;
  private final ICharmEntryViewFactory viewFactory;
  private final CharmTypeEntryPageProperties properties;
  private IReflexiveSpecialsView view;

  public ReflexiveSpecialEntryPage(IResources resources, ICharmEntryModel model, ICharmEntryViewFactory viewFactory) {
    this.resources = resources;
    this.properties = new CharmTypeEntryPageProperties(resources);
    this.model = model;
    this.viewFactory = viewFactory;
  }

  @Override
  protected void addFollowUpPages(CheckInputListener inputListener) {
    addFollowupPage(new DurationEntryPage(resources, model, viewFactory), inputListener, new ICondition() {
      @Override
      public boolean isFulfilled() {
        return true;
      }
    });
  }

  @Override
  protected void initModelListening(CheckInputListener inputListener) {
    // nothing to do
  }

  @Override
  protected void initPageContent() {
    this.view = viewFactory.createReflexiveSpecialsView();
    IIntValueView firstStepView = view.addIntegerSelectionView(properties.getDefaultStepLabel(), 1, 10);
    firstStepView.addIntValueChangedListener(new IIntValueChangedListener() {
      @Override
      public void valueChanged(int newValue) {
        getPageModel().setStep(newValue);
      }
    });
    firstStepView.setValue(getPageModel().getPrimaryStep());
    final JToggleButton button = view.addCheckBoxRow(properties.getSplitStepLabel());
    button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        getPageModel().setSplitEnabled(button.isSelected());
      }
    });
    button.setSelected(getPageModel().isSplitEnabled());
    IIntValueDisplay defensiveView = view.addIntegerSelectionView(properties.getDefensiveStepLabel(), 1, 10);
    ToggleComponentEnabler.connect(button, defensiveView.getComponent());
    defensiveView.addIntValueChangedListener(new IIntValueChangedListener() {
      @Override
      public void valueChanged(int newValue) {
        getPageModel().setDefenseStep(newValue);
      }
    });
    defensiveView.setValue(getPageModel().getSecondaryStep());
  }

  private IReflexiveSpecialsEntryModel getPageModel() {
    return model.getCharmTypeModel().getReflexiveSpecialsModel();
  }

  @Override
  public boolean canFinish() {
    return false;
  }

  @Override
  public String getDescription() {
    return properties.getReflexiveSpecialsTitle();
  }

  @Override
  public IBasicMessage getMessage() {
    return properties.getReflexiveSpecialsMessage();
  }

  @Override
  public IPageContent getPageContent() {
    return view;
  }

}
