package net.sf.anathema.campaign.module;

import javax.swing.Icon;

import net.sf.anathema.campaign.concrete.SeriesContentModel;
import net.sf.anathema.campaign.model.ISeries;
import net.sf.anathema.campaign.persistence.SeriesPersister;
import net.sf.anathema.campaign.presenter.CampaignPresenter;
import net.sf.anathema.campaign.presenter.action.AddNewSeriesAction;
import net.sf.anathema.campaign.presenter.view.ISeriesView;
import net.sf.anathema.campaign.view.CampaignView;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.module.AbstractItemTypeConfiguration;
import net.sf.anathema.framework.persistence.IRepositoryItemPersister;
import net.sf.anathema.framework.presenter.IItemViewFactory;
import net.sf.anathema.framework.presenter.action.ActionMenuItem;
import net.sf.anathema.framework.presenter.menu.IMenuItem;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.repository.ItemType;
import net.sf.anathema.framework.repository.RepositoryConfiguration;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.framework.view.IItemView;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.resources.IResources;

public final class SeriesTypeConfiguration extends AbstractItemTypeConfiguration {

  public static final String SERIES_ITEM_TYPE_ID = "Series"; //$NON-NLS-1$

  public SeriesTypeConfiguration() {
    super(new ItemType(SERIES_ITEM_TYPE_ID, new RepositoryConfiguration(".srs", "Series/", "main"))); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  }

  @Override
  protected IItemViewFactory createItemViewFactory(final IAnathemaModel anathemaModel, final IResources resources) {
    return new IItemViewFactory() {
      public IItemView createView(IItem item) throws AnathemaException {
        String printName = item.getDisplayName();
        Icon icon = resources.getImageIcon("TabSeries16.png"); //$NON-NLS-1$
        ISeriesView campaignView = new CampaignView(printName, icon);
        ISeries seriesData = (ISeries) item.getItemData();
        new CampaignPresenter(campaignView, resources, seriesData).initPresentation();
        return campaignView;
      }
    };
  }

  @Override
  protected IRepositoryItemPersister createPersister(IAnathemaModel model) {
    ItemType[] supportedItemTypes = SeriesContentModel.createSupportedItemTypes(model.getItemTypeRegistry());
    return new SeriesPersister(
        model.getRepository().getPrintNameFileAccess(),
        getItemType(),
        supportedItemTypes,
        model.getItemTypeRegistry());
  }

  @Override
  protected String getPrintNameKey() {
    return "ItemType.Campaign.PrintName"; //$NON-NLS-1$
  }

  @Override
  protected String getLoadMessageKey() {
    return "CampaignPersistence.LoadDialog.DefaultMessage"; //$NON-NLS-1$
  }

  @Override
  protected String getLoadTitleKey() {
    return "CampaignPersistence.LoadDialog.Title"; //$NON-NLS-1$
  }

  @Override
  protected IMenuItem[] createAddMenuEntries(IAnathemaView view, IAnathemaModel anathemaModel, IResources resources) {
    return new IMenuItem[] { new ActionMenuItem(AddNewSeriesAction.createMenuAction(resources, anathemaModel)) };
  }
}