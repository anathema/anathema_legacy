package net.sf.anathema.campaign.music.impl.module;

import net.sf.anathema.campaign.music.impl.model.MusicDatabase;
import net.sf.anathema.campaign.music.presenter.MusicUI;
import net.sf.anathema.framework.itemdata.model.IItemData;
import net.sf.anathema.framework.module.IDatabaseActionProperties;
import net.sf.anathema.initialization.repository.IDataFileProvider;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.Icon;
import java.io.IOException;
import java.nio.file.Path;

public class MusicDatabaseActionProperties implements IDatabaseActionProperties {
  private static final String MUSIC_DATABASE_ITEM_ID = "MusicDatabase.Item"; //$NON-NLS-1$
  private final IResources resources;

  public MusicDatabaseActionProperties(IResources resources) {
    this.resources = resources;
  }

  @Override
  public String getItemTypeId() {
    return MusicDatabaseItemTypeConfiguration.MUSIC_DATABASE_ITEM_TYPE_ID;
  }

  @Override
  public String getActionName() {
    return resources.getString("MusicDatabase.NewAction.Name"); //$NON-NLS-1$
  }

  @Override
  public Icon getActionIcon() {
    return new MusicUI(resources).getMusicToolBarIcon();
  }

  @Override
  public String getToolTipText() {
    return resources.getString("MusicDatabase.NewAction.Tooltip"); //$NON-NLS-1$
  }

  @Override
  public String getProgressTaskTitle() {
    return resources.getString("Music.DatabaseStart.Progress.Presentation"); //$NON-NLS-1$
  }

  @Override
  public String getItemId() {
    return MUSIC_DATABASE_ITEM_ID;
  }

  @Override
  public IItemData createItemData(IDataFileProvider provider) throws IOException {
    Path parentFolder = provider.getDataBaseDirectory("music"); //$NON-NLS-1$
    return new MusicDatabase(parentFolder.resolve("musicdatabase.yap")); //$NON-NLS-1$
  }
}
