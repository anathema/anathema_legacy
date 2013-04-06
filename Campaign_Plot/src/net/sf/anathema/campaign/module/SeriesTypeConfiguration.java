package net.sf.anathema.campaign.module;

import net.sf.anathema.campaign.model.ISeries;
import net.sf.anathema.campaign.persistence.SeriesPersister;
import net.sf.anathema.campaign.presenter.CampaignPresenter;
import net.sf.anathema.campaign.presenter.TextEditorProperties;
import net.sf.anathema.campaign.presenter.view.SeriesView;
import net.sf.anathema.campaign.view.CampaignView;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.module.AbstractPersistableItemTypeConfiguration;
import net.sf.anathema.framework.persistence.IRepositoryItemPersister;
import net.sf.anathema.framework.presenter.ItemViewFactory;
import net.sf.anathema.framework.presenter.view.IItemTypeViewProperties;
import net.sf.anathema.framework.presenter.view.SimpleItemTypeViewProperties;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.repository.ItemType;
import net.sf.anathema.framework.repository.RepositoryConfiguration;
import net.sf.anathema.framework.swing.styledtext.ITextEditorProperties;
import net.sf.anathema.framework.view.ItemView;
import net.sf.anathema.initialization.ItemTypeConfiguration;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.resources.Resources;

@ItemTypeConfiguration
@Weight(weight = 10)
public class SeriesTypeConfiguration extends AbstractPersistableItemTypeConfiguration {

  public static final String SERIES_ITEM_TYPE_ID = "Series";

  public SeriesTypeConfiguration() {
    super(new ItemType(SERIES_ITEM_TYPE_ID, new RepositoryConfiguration(".srs", "Series/", "main")));
  }

  @Override
  protected ItemViewFactory createItemViewFactory(IApplicationModel model, final Resources resources) {
    return new ItemViewFactory() {
      @Override
      public ItemView createView(IItem item) throws AnathemaException {
        String printName = item.getDisplayName();
        RelativePath icon = new PlotUI().getSeriesIconPath();
        ITextEditorProperties editorProperties = new TextEditorProperties(resources);
        SeriesView campaignView = new CampaignView(printName, icon, editorProperties);
        ISeries seriesData = (ISeries) item.getItemData();
        new CampaignPresenter(campaignView, resources, seriesData).initPresentation();
        return campaignView;
      }
    };
  }

  @Override
  protected IRepositoryItemPersister createPersister(IApplicationModel model) {
    return new SeriesPersister(getItemType());
  }

  @Override
  protected IItemTypeViewProperties createItemTypeCreationProperties(IApplicationModel anathemaModel, Resources resources) {
    return new SimpleItemTypeViewProperties(getItemType(), new PlotUI().getSeriesTabIcon());
  }
}