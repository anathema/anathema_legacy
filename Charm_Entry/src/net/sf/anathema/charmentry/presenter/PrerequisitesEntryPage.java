package net.sf.anathema.charmentry.presenter;

import net.disy.commons.core.message.IBasicMessage;
import net.disy.commons.swing.dialog.core.IPageContent;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.impl.traits.EssenceTemplate;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.charmentry.module.ICharmEntryViewFactory;
import net.sf.anathema.charmentry.presenter.model.ICharmEntryModel;
import net.sf.anathema.charmentry.presenter.model.IPrerequisitesModel;
import net.sf.anathema.charmentry.presenter.view.IPrerequisitesEntryView;
import net.sf.anathema.charmentry.presenter.view.ISelectableTraitView;
import net.sf.anathema.charmentry.properties.PrerequisitePageProperties;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.gui.wizard.AbstractAnathemaWizardPage;
import net.sf.anathema.lib.gui.wizard.workflow.CheckInputListener;
import net.sf.anathema.lib.gui.wizard.workflow.ICondition;
import net.sf.anathema.lib.resources.IResources;

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
      public boolean isFulfilled() {
        return isPrerequisiteSelected() && !isPermanentCharm();
      }
    });
    addFollowupPage(new PrerequisiteCharmsPage(resources, model, viewFactory), inputListener, new ICondition() {
      public boolean isFulfilled() {
        return isPrerequisiteSelected()
            && isPermanentCharm()
            && getPageModel().getEdition() == ExaltedEdition.FirstEdition;
      }
    });
    addFollowupPage(
        new SecondEditionPrerequisiteCharmsPage(resources, model, viewFactory),
        inputListener,
        new ICondition() {
          public boolean isFulfilled() {
            return isPrerequisiteSelected()
                && isPermanentCharm()
                && getPageModel().getEdition() == ExaltedEdition.SecondEdition;
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
    final ISelectableTraitView primaryView = view.addSelectablePrerequisiteView(
        properties.getPrimaryPrerequisiteLabel(),
        getPageModel().getPrimaryPrerequisiteTypes());
    primaryView.addTraitSelectionListener(new ITraitSelectionChangedListener() {
      public void selectionChanged(Object type, int value) {
        getPageModel().setPrimaryPrerequisite((ITraitType) type, value);
      }
    });
    getPageModel().addModelListener(new IChangeListener() {
      public void changeOccurred() {
        primaryView.setSelectableTraits(getPageModel().getPrimaryPrerequisiteTypes());
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
      public void valueChanged(int newValue) {
        getPageModel().setEssenceMinimum(newValue);
      }
    });
    getPageModel().addModelListener(new IChangeListener() {
      public void changeOccurred() {
        traitView.setValue(getPageModel().getEssenceMinimum());
      }
    });
    traitView.setValue(1);
  }

  public boolean canFinish() {
    return prerequisitesSelected();
  }

  public String getDescription() {
    return properties.getPageTitle();
  }

  public IBasicMessage getMessage() {
    if (getPageModel().getPrimaryPrerequisite() == null) {
      return properties.getPrimaryMissingMessage();
    }
    return properties.getPrerequisitesMessage();
  }

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