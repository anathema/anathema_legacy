package net.sf.anathema.campaign.music.impl.module;

import net.sf.anathema.campaign.music.impl.view.MusicDatabaseView;
import net.sf.anathema.campaign.music.model.IMusicDatabase;
import net.sf.anathema.campaign.music.presenter.MusicDataBasePresenter;
import net.sf.anathema.campaign.music.presenter.MusicUI;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.database.StartDatabaseAction;
import net.sf.anathema.framework.module.AbstractNonPersistableItemTypeConfiguration;
import net.sf.anathema.framework.presenter.IItemViewFactory;
import net.sf.anathema.framework.presenter.action.ActionMenuItem;
import net.sf.anathema.framework.presenter.menu.IMenuItem;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.repository.ItemType;
import net.sf.anathema.framework.view.ApplicationView;
import net.sf.anathema.framework.view.IItemView;
import net.sf.anathema.initialization.ItemTypeConfiguration;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.resources.IResources;

@ItemTypeConfiguration
@Weight(weight = 40)
public final class MusicDatabaseItemTypeConfiguration extends AbstractNonPersistableItemTypeConfiguration {

  public static final String MUSIC_DATABASE_ITEM_TYPE_ID = "MusicDatabase"; //$NON-NLS-1$

  public MusicDatabaseItemTypeConfiguration() {
    super(new ItemType(MUSIC_DATABASE_ITEM_TYPE_ID, null));
  }

  @Override
  protected IItemViewFactory createItemViewFactory(IAnathemaModel anathemaModel, final IResources resources) {
    return new IItemViewFactory() {
      @Override
      public IItemView createView(IItem item) throws AnathemaException {
        IMusicDatabase database = (IMusicDatabase) item.getItemData();
        MusicUI musicUI = new MusicUI(resources);
        MusicDatabaseView view = new MusicDatabaseView(resources.getString("ItemType.MusicDatabase.PrintName"), musicUI); //$NON-NLS-1$
        new MusicDataBasePresenter(resources, database, view).initPresentation();
        return view;
      }
    };
  }

  @Override
  protected IMenuItem[] createAddMenuEntries(ApplicationView view, IAnathemaModel anathemaModel, IResources resources) {
    MusicDatabaseActionProperties properties = new MusicDatabaseActionProperties(resources);
    return new IMenuItem[] { new ActionMenuItem(StartDatabaseAction.createMenuAction(
        resources,
        anathemaModel,
        properties)) };
  }
}