package net.sf.anathema.gis.platform;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.module.AbstractNonPersistableItemTypeConfiguration;
import net.sf.anathema.framework.presenter.IItemViewFactory;
import net.sf.anathema.framework.presenter.action.ActionMenuItem;
import net.sf.anathema.framework.presenter.menu.IMenuItem;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.repository.ItemType;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.framework.view.IItemView;
import net.sf.anathema.gis.main.impl.model.GisModel;
import net.sf.anathema.gis.main.model.IGisModel;
import net.sf.anathema.gis.main.presenter.GisPresenter;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.resources.IResources;

public final class GisItemTypeConfiguration extends AbstractNonPersistableItemTypeConfiguration {

  public static final String CHARM_ENTRY_ITEM_TYPE_ID = "CharmEntry"; //$NON-NLS-1$

  public GisItemTypeConfiguration() {
    super(new ItemType(CHARM_ENTRY_ITEM_TYPE_ID, null));
  }

  @Override
  protected IItemViewFactory createItemViewFactory(final IAnathemaModel anathemaModel, final IResources resources) {
    return new IItemViewFactory() {
      public IItemView createView(IItem item) throws AnathemaException {
        String printName = item.getDisplayName();
        GisModuleView anathemaGisView = new GisModuleView(printName);
        IGisModel model = new GisModel();
        new GisPresenter(model, anathemaGisView.addGisView(), resources).initPresentation();
        return anathemaGisView;
      }
    };
  }

  @Override
  protected String getPrintNameKey() {
    return "ItemType.Map.PrintName"; //$NON-NLS-1$
  }

  @Override
  protected IMenuItem[] createAddMenuEntries(IAnathemaView view, IAnathemaModel anathemaModel, IResources resources) {
    return new IMenuItem[] { new ActionMenuItem(ShowCharmEntryAction.createMenuAction(resources, anathemaModel)) };
  }
}