package net.sf.anathema.charmentry.presenter;

import net.disy.commons.core.message.IBasicMessage;
import net.disy.commons.swing.dialog.core.IPageContent;
import net.sf.anathema.character.generic.impl.traits.EssenceTemplate;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.framework.intvalue.ISelectableIntValueView;
import net.sf.anathema.charmentry.module.ICharmEntryViewFactory;
import net.sf.anathema.charmentry.presenter.model.ICharmEntryModel;
import net.sf.anathema.charmentry.presenter.model.IPrerequisitesModel;
import net.sf.anathema.charmentry.presenter.view.IPrerequisitesEntryView;
import net.sf.anathema.charmentry.properties.PrerequisitePageProperties;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.gui.selection.ISelectionIntValueChangedListener;
import net.sf.anathema.lib.gui.wizard.AbstractAnathemaWizardPage;
import net.sf.anathema.lib.gui.wizard.workflow.CheckInputListener;
import net.sf.anathema.lib.gui.wizard.workflow.ICondition;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;

public class PrerequisitesEntryPage extends AbstractAnathemaWizardPage {

  private final IResources resources;
  private final ICharmEntryModel model;
  private final ICharmEntryViewFactory viewFactory;
  private final PrerequisitePageProperties properties;
  private IPrerequisitesEntryView view;

  public PrerequisitesEntryPage(IResources resources, ICharmEntryModel model, ICharmEntryViewFactory viewFactory) {
    this.resources = resources;
    this.properties = new PrerequisitePageProperties(resources);
    this.model = model;
    this.viewFactory = viewFactory;
  }

  @Override
  protected void addFollowUpPages(CheckInputListener inputListener) {
    addFollowupPage(new CostEntryPage(resources, model, viewFactory), inputListener, new ICondition() {
      @Override
      public boolean isFulfilled() {
        return isPrerequisiteSelected() && !isPermanentCharm();
      }
    });
    addFollowupPage(
        new SecondEditionPrerequisiteCharmsPage(resources, model, viewFactory),
        inputListener,
        new ICondition() {
          @Override
          public boolean isFulfilled() {
            return isPrerequisiteSelected()
                && isPermanentCharm();
          }
        });

  }

  private boolean isPermanentCharm() {
    return getPageModel().isPermanentCharm();
  }

  private boolean isPrerequisiteSelected() {
    return getPageModel().getPrimaryPrerequisite() != null;
  }

  @Override
  protected void initModelListening(CheckInputListener inputListener) {
    getPageModel().addModelListener(inputListener);
  }

  @Override
  protected void initPageContent() {
    this.view = viewFactory.createPrerequisiteTraitsView();
    initPrimaryPrerequistePresentation();
    initEssencePrerequisitePresentation();
  }

  private void initPrimaryPrerequistePresentation() {
    final ISelectableIntValueView<IIdentificate> primaryView = view.addSelectablePrerequisiteView(
        properties.getPrimaryPrerequisiteLabel(),
        getPageModel().getPrimaryPrerequisiteTypes(),
        1,
        EssenceTemplate.SYSTEM_ESSENCE_MAX);
    primaryView.addSelectionChangedListener(new ISelectionIntValueChangedListener<IIdentificate>() {
      @Override
      public void valueChanged(IIdentificate type, int value) {
        getPageModel().setPrimaryPrerequisite((ITraitType) type, value);
      }
    });
    getPageModel().addModelListener(new IChangeListener() {
      @Override
      public void changeOccurred() {
        primaryView.setSelectableValues(getPageModel().getPrimaryPrerequisiteTypes());
        if (getPageModel().getPrimaryPrerequisite() != null) {
          primaryView.setValue(getPageModel().getPrimaryPrerequisite().getCurrentValue());
        }
      }
    });
    primaryView.setValue(1);
  }

  private void initEssencePrerequisitePresentation() {
    final IIntValueView traitView = view.addEssencePrerequisiteView(
        properties.getEssenceLabel(),
        1,
        EssenceTemplate.SYSTEM_ESSENCE_MAX);
    traitView.addIntValueChangedListener(new IIntValueChangedListener() {
      @Override
      public void valueChanged(int newValue) {
        getPageModel().setEssenceMinimum(newValue);
      }
    });
    getPageModel().addModelListener(new IChangeListener() {
      @Override
      public void changeOccurred() {
        traitView.setValue(getPageModel().getEssenceMinimum());
      }
    });
    traitView.setValue(1);
  }

  @Override
  public boolean canFinish() {
    return prerequisitesSelected();
  }

  @Override
  public String getDescription() {
    return properties.getPageTitle();
  }

  @Override
  public IBasicMessage getMessage() {
    if (getPageModel().getPrimaryPrerequisite() == null) {
      return properties.getPrimaryMissingMessage();
    }
    return properties.getPrerequisitesMessage();
  }

  @Override
  public IPageContent getPageContent() {
    return view;
  }

  private boolean prerequisitesSelected() {
    final IGenericTrait primaryPrerequisite = getPageModel().getPrimaryPrerequisite();
    return primaryPrerequisite != null && getPageModel().getEssenceMinimum() >= 1;
  }

  private IPrerequisitesModel getPageModel() {
    return model.getPrerequisitesModel();
  }
}