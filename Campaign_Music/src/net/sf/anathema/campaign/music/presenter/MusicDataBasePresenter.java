package net.sf.anathema.campaign.music.presenter;

import net.sf.anathema.campaign.music.model.IMusicDatabase;
import net.sf.anathema.campaign.music.model.selection.IMusicSelectionModel;
import net.sf.anathema.campaign.music.presenter.library.LibraryControlPresenter;
import net.sf.anathema.campaign.music.presenter.search.MusicSearchPresenter;
import net.sf.anathema.campaign.music.presenter.selection.MoveSelectedTracksToSelectionAction;
import net.sf.anathema.campaign.music.presenter.selection.MusicSelectionPresenter;
import net.sf.anathema.campaign.music.presenter.selection.list.MusicSelectionColumnViewSettings;
import net.sf.anathema.campaign.music.presenter.selection.player.IMusicPlayerModel;
import net.sf.anathema.campaign.music.presenter.selection.player.MusicPlayerPresenter;
import net.sf.anathema.campaign.music.view.IMusicDatabaseView;
import net.sf.anathema.campaign.music.view.categorization.IMusicCategorizationProperties;
import net.sf.anathema.campaign.music.view.library.ILibraryControlView;
import net.sf.anathema.campaign.music.view.selection.IMusicSelectionView;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.resources.IResources;

public class MusicDataBasePresenter implements IPresenter {

  private final IMusicDatabaseView view;
  private final IMusicDatabase dataBase;
  private final IResources resources;

  public MusicDataBasePresenter(IResources resources, IMusicDatabase dataBase, IMusicDatabaseView view) {
    this.resources = resources;
    this.dataBase = dataBase;
    this.view = view;
  }

  public void initPresentation() {
    IMusicCategorizationProperties categoryProperties = new MusicCategorizationProperties(resources);
    ILibraryControlProperties libraryProperties = new LibraryControlProperties(resources);
    IMusicPlayerProperties playerProperties = new MusicPlayerProperties(resources);
    IMusicSelectionProperties selectionProperties = new MusicSelectionProperties(resources);
    ITrackDetailsProperties detailsProperties = new TrackDetailsProperties(resources);
    final ILibraryControlView controlView = view.addLibraryControlView(new MusicLibraryColumnViewSettings(
        dataBase.getLibraryControl()), categoryProperties, libraryProperties);
    IMusicPlayerModel musicPlayerModel = dataBase.getMusicPlayerModel();
    IMusicSelectionView selectionView = view.addMusicSelectionView(
        new MusicSelectionColumnViewSettings(dataBase.getMusicSelectionModel()),
        musicPlayerModel != null,
        categoryProperties,
        playerProperties,
        selectionProperties,
        detailsProperties);
    new LibraryControlPresenter(controlView, dataBase, resources).initPresentation();
    new MusicSearchPresenter(controlView, dataBase, resources).initPresentation();
    IMusicSelectionModel selectionModel = dataBase.getMusicSelectionModel();
    new MusicSelectionPresenter(resources, selectionView, selectionModel).initPresentation();
    new TrackDetailsPresenter(resources, selectionView.getTrackDetailsView(), selectionModel.getTrackDetailModel()).initPresentation();
    if (musicPlayerModel != null) {
      new MusicPlayerPresenter(
          resources,
          selectionView.getPlayerView(),
          musicPlayerModel,
          dataBase.getMusicSelectionModel()).initPresentation();
    }
    controlView.getTrackListView().addAction(
        new MoveSelectedTracksToSelectionAction(resources, controlView.getTrackListView(), selectionModel));
  }
}