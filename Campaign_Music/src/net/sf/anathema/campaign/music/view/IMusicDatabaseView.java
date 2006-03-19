package net.sf.anathema.campaign.music.view;

import net.sf.anathema.campaign.music.presenter.ILibraryControlProperties;
import net.sf.anathema.campaign.music.presenter.IMusicPlayerProperties;
import net.sf.anathema.campaign.music.presenter.IMusicSelectionProperties;
import net.sf.anathema.campaign.music.presenter.ITrackDetailsProperties;
import net.sf.anathema.campaign.music.view.categorization.IMusicCategorizationProperties;
import net.sf.anathema.campaign.music.view.library.ILibraryControlView;
import net.sf.anathema.campaign.music.view.selection.IMusicSelectionView;
import net.sf.anathema.framework.view.IItemView;
import net.sf.anathema.lib.gui.table.columsettings.ITableColumnViewSettings;

public interface IMusicDatabaseView extends IItemView {

  public ILibraryControlView addLibraryControlView(
      ITableColumnViewSettings settings,
      IMusicCategorizationProperties properties,
      ILibraryControlProperties libraryProperties);

  public IMusicSelectionView addMusicSelectionView(
      ITableColumnViewSettings columnSettings,
      boolean includePlayerView,
      IMusicCategorizationProperties categoryProperties,
      IMusicPlayerProperties playerProperties,
      IMusicSelectionProperties selectionProperties,
      ITrackDetailsProperties detailsProperties);
}