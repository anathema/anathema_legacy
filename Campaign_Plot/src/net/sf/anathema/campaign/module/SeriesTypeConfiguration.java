package net.sf.anathema.campaign.module;

import javax.swing.Icon;

import net.sf.anathema.campaign.concrete.SeriesContentModel;
import net.sf.anathema.campaign.model.ISeries;
import net.sf.anathema.campaign.persistence.SeriesPersister;
import net.sf.anathema.campaign.presenter.CampaignPresenter;
import net.sf.anathema.campaign.presenter.view.ISeriesView;
import net.sf.anathema.campaign.view.CampaignView;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.module.AbstractPersistableItemTypeConfiguration;
import net.sf.anathema.framework.persistence.IRepositoryItemPersister;
import net.sf.anathema.framework.presenter.IItemViewFactory;
import net.sf.anathema.framework.presenter.view.IItemTypeCreationViewProperties;
import net.sf.anathema.framework.presenter.view.SimpleItemTypeCreationViewProperties;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.repository.ItemType;
import net.sf.anathema.framework.repository.RepositoryConfiguration;
import net.sf.anathema.framework.view.IItemView;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.resources.IResources;

public final class SeriesTypeConfiguration extends AbstractPersistableItemTypeConfiguration {

  public static final String SERIES_ITEM_TYPE_ID = "Series"; //$NON-NLS-1$

  public SeriesTypeConfiguration() {
    super(new ItemType(SERIES_ITEM_TYPE_ID, new RepositoryConfiguration(".srs", "Series/", "main"))); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  }

  @Override
  protected IItemViewFactory createItemViewFactory(final IAnathemaModel anathemaModel, final IResources resources) {
    return new IItemViewFactory() {
      public IItemView createView(IItem item) throws AnathemaException {
        String printName = item.getDisplayName();
        Icon icon = new PlotUI(resources).getSeriesTabIcon();
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
  protected IItemTypeCreationViewProperties createItemTypeCreationProperties(
      IAnathemaModel anathemaModel,
      IResources resources) {
    return new SimpleItemTypeCreationViewProperties(getItemType(), new PlotUI(resources).getSeriesTabIcon());
  }
}