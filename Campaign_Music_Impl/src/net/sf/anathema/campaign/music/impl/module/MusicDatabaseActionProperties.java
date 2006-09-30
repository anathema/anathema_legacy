package net.sf.anathema.campaign.music.impl.module;

import java.io.File;
import java.io.IOException;

import javax.swing.Icon;

import net.sf.anathema.campaign.music.impl.model.MusicDatabase;
import net.sf.anathema.campaign.music.presenter.MusicUI;
import net.sf.anathema.framework.itemdata.model.IItemData;
import net.sf.anathema.framework.module.AbstractDatabaseActionProperties;
import net.sf.anathema.lib.resources.IResources;

public class MusicDatabaseActionProperties extends AbstractDatabaseActionProperties {
  private static final String MUSIC_DATABASE_ITEM_ID = "MusicDatabase.Item"; //$NON-NLS-1$
  private final IResources resources;

  public MusicDatabaseActionProperties(IResources resources) {
    this.resources = resources;
  }

  public String getItemTypeId() {
    return MusicDatabaseItemTypeConfiguration.MUSIC_DATABASE_ITEM_TYPE_ID;
  }

  public String getActionName() {
    return resources.getString("MusicDatabase.NewAction.Name"); //$NON-NLS-1$
  }

  public Icon getActionIcon() {
    return new MusicUI(resources).getMusicToolBarIcon();
  }

  public String getToolTipText() {
    return resources.getString("MusicDatabase.NewAction.Tooltip"); //$NON-NLS-1$
  }

  public String getProgressMonitorTitle() {
    return resources.getString("Music.DatabaseStart.Progress.Title"); //$NON-NLS-1$;
  }

  public String getProgressTaskTitle() {
    return resources.getString("Music.DatabaseStart.Progress.Presentation"); //$NON-NLS-1$
  }

  @Override
  protected String getFolderName() {
    return "music"; //$NON-NLS-1$
  }

  public String getItemId() {
    return MUSIC_DATABASE_ITEM_ID;
  }

  public IItemData createItemData(File repositoryFolder) throws IOException {
    File parentFolder = getParentFolder(repositoryFolder);
    return new MusicDatabase(new File(parentFolder, "musicdatabase.yap")); //$NON-NLS-1$
  }
}
