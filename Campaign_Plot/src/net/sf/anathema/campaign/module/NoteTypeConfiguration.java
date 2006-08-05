package net.sf.anathema.campaign.module;

import javax.swing.Icon;

import net.sf.anathema.campaign.persistence.BasicDataItemPersister;
import net.sf.anathema.campaign.persistence.ISeriesPersistenceConstants;
import net.sf.anathema.campaign.presenter.NotePresenter;
import net.sf.anathema.campaign.presenter.action.AddNewBasicItemAction;
import net.sf.anathema.campaign.view.BasicItemView;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.itemdata.model.IBasicItemData;
import net.sf.anathema.framework.itemdata.view.IBasicItemView;
import net.sf.anathema.framework.module.AbstractItemTypeConfiguration;
import net.sf.anathema.framework.module.NullWizardPageFactory;
import net.sf.anathema.framework.persistence.IRepositoryItemPersister;
import net.sf.anathema.framework.presenter.IItemViewFactory;
import net.sf.anathema.framework.presenter.IWizardFactory;
import net.sf.anathema.framework.presenter.action.ActionMenuItem;
import net.sf.anathema.framework.presenter.menu.IMenuItem;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.repository.ItemType;
import net.sf.anathema.framework.repository.RepositoryConfiguration;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.framework.view.IItemView;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.resources.IResources;

public class NoteTypeConfiguration extends AbstractItemTypeConfiguration {

  public NoteTypeConfiguration() {
    super(new ItemType("Note", new RepositoryConfiguration(".not", "Notes/"))); //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
  }

  @Override
  protected String getPrintNameKey() {
    return "ItemType.Note.PrintName"; //$NON-NLS-1$
  }

  @Override
  protected String getLoadMessageKey() {
    return "NotePersistence.LoadDialog.DefaultMessage"; //$NON-NLS-1$
  }

  @Override
  protected String getLoadTitleKey() {
    return "NotePersistence.LoadDialog.Title"; //$NON-NLS-1$
  }

  @Override
  protected IRepositoryItemPersister createPersister(IAnathemaModel model) {
    return new BasicDataItemPersister(getItemType(), ISeriesPersistenceConstants.TAG_NOTE_ROOT);
  }

  @Override
  protected IItemViewFactory createItemViewFactory(IAnathemaModel anathemaModel, final IResources resources) {
    return new IItemViewFactory() {
      public IItemView createView(IItem item) throws AnathemaException {
        String printName = item.getDisplayName();
        Icon icon = new PlotUI(resources).getNoteTabIcon();
        IBasicItemView basicItemView = new BasicItemView(printName, icon);
        IBasicItemData basicItem = (IBasicItemData) item.getItemData();
        new NotePresenter(basicItemView, resources, basicItem).initPresentation();
        return basicItemView;
      }
    };
  }

  @Override
  protected IMenuItem[] createAddMenuEntries(IAnathemaView view, IAnathemaModel anathemaModel, IResources resources) {
    return new IMenuItem[] { new ActionMenuItem(AddNewBasicItemAction.createMenuAction(
        resources,
        anathemaModel,
        getItemType(),
        "Note.MenuName.New")) }; //$NON-NLS-1$
  }

  @Override
  protected IWizardFactory createCreationWizardPageFactory(IAnathemaModel anathemaModel, IResources resources) {
    return new NullWizardPageFactory();
  }
}