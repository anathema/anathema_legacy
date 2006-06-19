package net.sf.anathema.charmentry.presenter;

import net.disy.commons.core.message.IBasicMessage;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.charmentry.module.ICharmEntryViewFactory;
import net.sf.anathema.charmentry.presenter.model.ICharmEntryModel;
import net.sf.anathema.charmentry.presenter.model.ICharmPrerequisitesEntryModel;
import net.sf.anathema.charmentry.presenter.view.IPrerequisiteCharmsEntryView;
import net.sf.anathema.charmentry.properties.IPrerequisiteCharmsPageProperties;
import net.sf.anathema.charmentry.properties.PrerequisiteCharmsPageProperties;
import net.sf.anathema.framework.presenter.view.IdentificateListCellRenderer;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.gui.wizard.AbstractAnathemaWizardPage;
import net.sf.anathema.lib.gui.wizard.workflow.CheckInputListener;
import net.sf.anathema.lib.lang.ArrayUtilities;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.container.ISelectionContainerView;

public class PrerequisiteCharmsPage extends AbstractAnathemaWizardPage {

  private final IResources resources;
  private final ICharmEntryModel model;
  private final ICharmEntryViewFactory viewFactory;
  private final IPrerequisiteCharmsPageProperties properties;
  private IPrerequisiteCharmsEntryView view;

  public PrerequisiteCharmsPage(IResources resources, ICharmEntryModel model, ICharmEntryViewFactory viewFactory) {
    this.resources = resources;
    this.properties = new PrerequisiteCharmsPageProperties(resources);
    this.model = model;
    this.viewFactory = viewFactory;
  }

  @Override
  protected void addFollowUpPages(CheckInputListener inputListener) {
    // nothing to do
  }

  @Override
  protected void initModelListening(CheckInputListener inputListener) {
    getPageModel().addModelListener(inputListener);
  }

  protected ICharmPrerequisitesEntryModel getPageModel() {
    return model.getCharmPrerequisitesModel();
  }

  @Override
  protected void initPageContent() {
    this.view = viewFactory.createPrerequisiteCharmsView();
    final ISelectionContainerView<ICharm> charmView = view.addPrerequisiteCharmView(new IdentificateListCellRenderer(resources));
    charmView.addSelectionChangeListener(new IChangeListener() {
      public void changeOccured() {
        Object[] selectedValues = charmView.getSelectedValues();
        ICharm[] charms = new ICharm[selectedValues.length];
        ArrayUtilities.copyAll(selectedValues, charms);
        getPageModel().setPrerequisiteCharms(charms);
      }
    });
    getPageModel().addModelListener(new IChangeListener() {
      public void changeOccured() {
        try {
          charmView.populate(getPageModel().getAvailableCharms());
        }
        catch (PersistenceException e) {
          // TODO Handle
          e.printStackTrace();
        }
      }
    });
  }

  public boolean canFinish() {
    return true;
  }

  public String getDescription() {
    return properties.getPageTitle();
  }

  public IBasicMessage getMessage() {
    return properties.getDefaultMessage();
  }

  public IPrerequisiteCharmsEntryView getPageContent() {
    return view;
  }

  protected IPrerequisiteCharmsPageProperties getProperties() {
    return properties;
  }

  protected IResources getResources() {
    return resources;
  }

  protected ICharmEntryModel getModel() {
    return model;
  }

  protected ICharmEntryViewFactory getViewFactory() {
    return viewFactory;
  }
}