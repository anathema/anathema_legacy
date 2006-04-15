package net.sf.anathema.charmentry.module;

import net.sf.anathema.charmentry.model.CharmEntryModel;
import net.sf.anathema.charmentry.presenter.CharmEntryPresenter;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.module.AbstractNonPersistableItemTypeConfiguration;
import net.sf.anathema.framework.presenter.IItemViewFactory;
import net.sf.anathema.framework.presenter.action.ActionMenuItem;
import net.sf.anathema.framework.presenter.menu.IMenuItem;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.repository.ItemType;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.framework.view.IItemView;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.resources.IResources;

public final class CharmEntryItemTypeConfiguration extends AbstractNonPersistableItemTypeConfiguration {

  public static final String CHARM_ENTRY_ITEM_TYPE_ID = "CharmEntry"; //$NON-NLS-1$

  public CharmEntryItemTypeConfiguration() {
    super(new ItemType(CHARM_ENTRY_ITEM_TYPE_ID, null));
  }

  @Override
  protected IItemViewFactory createItemViewFactory(final IAnathemaModel anathemaModel, final IResources resources) {
    return new IItemViewFactory() {
      public IItemView createView(IItem item) throws AnathemaException {
        String printName = item.getDisplayName();
        CharmEntryModuleView entryView = new CharmEntryModuleView(printName, null);
        CharmEntryModel model = new CharmEntryModel();
        new CharmEntryPresenter(model, entryView.addCharmEntryView(), resources).initPresentation();
        return entryView;
      }
    };
  }

  @Override
  protected String getPrintNameKey() {
    return "ItemType.CharmEntry.PrintName"; //$NON-NLS-1$
  }

  @Override
  protected IMenuItem[] createAddMenuEntries(IAnathemaView view, IAnathemaModel anathemaModel, IResources resources) {
    return new IMenuItem[] { new ActionMenuItem(ShowCharmEntryAction.createMenuAction(resources, anathemaModel)) };
  }
}