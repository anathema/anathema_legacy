package net.sf.anathema.charmentry.demo.page;

import net.disy.commons.core.message.BasicMessage;
import net.disy.commons.core.message.IBasicMessage;
import net.disy.commons.swing.dialog.core.IPageContent;
import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.charmentry.demo.ICharmEntryModel;
import net.sf.anathema.charmentry.demo.ICharmEntryViewFactory;
import net.sf.anathema.charmentry.demo.ICharmTypeEntryModel;
import net.sf.anathema.charmentry.demo.ICharmTypeEntryPageProperties;
import net.sf.anathema.charmentry.demo.ICharmTypeEntryView;
import net.sf.anathema.charmentry.demo.page.properties.CharmTypeEntryPageProperties;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.gui.wizard.AbstractAnathemaWizardPage;
import net.sf.anathema.lib.gui.wizard.workflow.CheckInputListener;
import net.sf.anathema.lib.gui.wizard.workflow.ICondition;
import net.sf.anathema.lib.resources.IResources;

public class CharmTypeEntryPage extends AbstractAnathemaWizardPage {
  private final IResources resources;
  private final ICharmEntryModel model;
  private final ICharmEntryViewFactory viewFactory;
  private final ICharmTypeEntryPageProperties properties;
  private final IBasicMessage defaultMessage;
  private ICharmTypeEntryView view;

  public CharmTypeEntryPage(IResources resources, ICharmEntryModel model, ICharmEntryViewFactory viewFactory) {
    this.resources = resources;
    this.properties = new CharmTypeEntryPageProperties(resources);
    this.defaultMessage = new BasicMessage(properties.getCharmTypeMessage());
    this.model = model;
    this.viewFactory = viewFactory;
  }

  @Override
  protected void addFollowUpPages(CheckInputListener inputListener) {
    addFollowupPage(new DurationEntryPage(resources, model, viewFactory), inputListener, new ICondition() {
      public boolean isFullfilled() {
        final CharmType charmType = getPageModel().getCharmType();
        return charmType != null && !isSpecialCharmType();
      }
    });
    addFollowupPage(new PrerequisitesEntryPage(resources, model, viewFactory), inputListener, new ICondition() {
      public boolean isFullfilled() {
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
    IObjectSelectionView typeView = getView().addComboBoxRow(
        getProperties().getTypeLabel(),
        getProperties().getDefaultIdentificateRenderer(),
        getPageModel().getCharmTypes());
    typeView.addObjectSelectionChangedListener(new IObjectValueChangedListener<CharmType>() {
      public void valueChanged(CharmType newValue) {
        getPageModel().setCharmType(newValue);
      }
    });
  }

  public boolean canFinish() {
    return false;
  }

  public String getDescription() {
    return properties.getPageHeader();
  }

  public IBasicMessage getMessage() {
    return defaultMessage;
  }

  public IPageContent getPageContent() {
    return view;
  }

  protected ICharmTypeEntryView getView() {
    return view;
  }

  protected ICharmTypeEntryPageProperties getProperties() {
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
