package net.sf.anathema.charmentry.presenter;

import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.charmentry.module.ICharmEntryViewFactory;
import net.sf.anathema.charmentry.presenter.model.ICharmEntryModel;
import net.sf.anathema.charmentry.presenter.model.ICharmTypeEntryModel;
import net.sf.anathema.charmentry.presenter.view.ICharmTypeEntryView;
import net.sf.anathema.charmentry.properties.CharmTypeEntryPageProperties;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.gui.wizard.AbstractAnathemaWizardPage;
import net.sf.anathema.lib.gui.wizard.workflow.CheckInputListener;
import net.sf.anathema.lib.gui.wizard.workflow.ICondition;
import net.sf.anathema.lib.message.IBasicMessage;
import net.sf.anathema.lib.resources.IResources;

public class CharmTypeEntryPage extends AbstractAnathemaWizardPage {
  private final IResources resources;
  private final ICharmEntryModel model;
  private final ICharmEntryViewFactory viewFactory;
  private final CharmTypeEntryPageProperties properties;
  private ICharmTypeEntryView view;

  public CharmTypeEntryPage(IResources resources, ICharmEntryModel model, ICharmEntryViewFactory viewFactory) {
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
        final CharmType charmType = getPageModel().getCharmType();
        return charmType != null && !isSpecialCharmType();
      }
    });
    addFollowupPage(new PrerequisitesEntryPage(resources, model, viewFactory), inputListener, new ICondition() {
      @Override
      public boolean isFulfilled() {
        return isPermanentCharm();
      }
    });
  }

  protected boolean isSpecialCharmType() {
    return isPermanentCharm();
  }

  private boolean isPermanentCharm() {
    return getPageModel().getCharmType() == CharmType.Permanent;
  }

  @Override
  protected void initModelListening(CheckInputListener inputListener) {
    getPageModel().addModelListener(inputListener);
  }

  @Override
  protected void initPageContent() {
    this.view = viewFactory.getCharmTypeEntryView();
    initTypeView();
  }

  private void initTypeView() {
    IObjectSelectionView<CharmType> typeView = getPageContent().addComboBoxRow(
        getProperties().getTypeLabel(),
        getProperties().getDefaultIdentificateRenderer(),
        getPageModel().getCharmTypes());
    typeView.addObjectSelectionChangedListener(new ObjectValueListener<CharmType>() {
      @Override
      public void valueChanged(CharmType newValue) {
        getPageModel().setCharmType(newValue);
      }
    });
  }

  @Override
  public boolean canFinish() {
    return false;
  }

  @Override
  public String getDescription() {
    return properties.getPageHeader();
  }

  @Override
  public IBasicMessage getMessage() {
    return properties.getCharmTypeDefaultMessage();
  }

  @Override
  public ICharmTypeEntryView getPageContent() {
    return view;
  }

  protected CharmTypeEntryPageProperties getProperties() {
    return properties;
  }

  protected ICharmTypeEntryModel getPageModel() {
    return model.getCharmTypeModel();
  }

  protected IResources getResources() {
    return resources;
  }

  protected ICharmEntryViewFactory getViewFactory() {
    return viewFactory;
  }

  protected ICharmEntryModel getModel() {
    return model;
  }
}
