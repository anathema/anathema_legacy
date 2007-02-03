package net.sf.anathema.campaign.music.impl.module;

import javax.swing.Icon;

import net.sf.anathema.campaign.music.impl.view.MusicDatabaseView;
import net.sf.anathema.campaign.music.model.IMusicDatabase;
import net.sf.anathema.campaign.music.presenter.MusicDataBasePresenter;
import net.sf.anathema.campaign.music.presenter.MusicUI;
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
import net.sf.anathema.platform.database.StartDatabaseAction;

public final class MusicDatabaseItemTypeConfiguration extends AbstractNonPersistableItemTypeConfiguration {

  public static final String MUSIC_DATABASE_ITEM_TYPE_ID = "MusicDatabase"; //$NON-NLS-1$

  public MusicDatabaseItemTypeConfiguration() {
    super(new ItemType(MUSIC_DATABASE_ITEM_TYPE_ID, null));
  }

  @Override
  protected IItemViewFactory createItemViewFactory(IAnathemaModel anathemaModel, final IResources resources) {
    return new IItemViewFactory() {
      public IItemView createView(IItem item) throws AnathemaException {
        IMusicDatabase database = (IMusicDatabase) item.getItemData();
        Icon icon = new MusicUI(resources).getMusicTabIcon();
        MusicDatabaseView view = new MusicDatabaseView(resources.getString("ItemType.MusicDatabase.PrintName"), icon); //$NON-NLS-1$
        new MusicDataBasePresenter(resources, database, view).initPresentation();
        return view;
      }
    };
  }

  @Override
  protected IMenuItem[] createAddMenuEntries(IAnathemaView view, IAnathemaModel anathemaModel, IResources resources) {
    MusicDatabaseActionProperties properties = new MusicDatabaseActionProperties(resources);
    return new IMenuItem[] { new ActionMenuItem(StartDatabaseAction.createMenuAction(
        resources,
        anathemaModel,
        properties)) };
  }
}