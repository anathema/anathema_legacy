package net.sf.anathema.campaign.music.presenter.library;

import javax.swing.Action;

import net.sf.anathema.campaign.music.model.IMusicDatabase;
import net.sf.anathema.campaign.music.model.libary.ILibrary;
import net.sf.anathema.campaign.music.model.libary.ILibraryChangedListener;
import net.sf.anathema.campaign.music.model.libary.ILibraryControl;
import net.sf.anathema.campaign.music.model.selection.IMusicSelectionModel;
import net.sf.anathema.campaign.music.presenter.IMusicSearchControl;
import net.sf.anathema.campaign.music.presenter.library.content.AddMusicFileAction;
import net.sf.anathema.campaign.music.presenter.library.content.AddMusicFolderAction;
import net.sf.anathema.campaign.music.presenter.library.control.AddLibraryAction;
import net.sf.anathema.campaign.music.presenter.library.control.DeleteLibraryAction;
import net.sf.anathema.campaign.music.presenter.library.control.LibrarySelectionListener;
import net.sf.anathema.campaign.music.view.library.ILibraryControlView;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.gui.list.actionview.IActionAddableListView;
import net.sf.anathema.lib.resources.IResources;

public class LibraryControlPresenter {

  private final ILibraryControlView controlView;
  private final ILibraryControl libraryModel;
  private final IResources resources;
  private final IMusicSelectionModel selectionModel;
  private final IMusicSearchControl searchControl;

  public LibraryControlPresenter(ILibraryControlView controlView, IMusicDatabase database, IResources resources) {
    this.controlView = controlView;
    this.libraryModel = database.getLibraryControl();
    this.selectionModel = database.getMusicSelectionModel();
    this.searchControl = database.getMusicSearchControl();
    this.resources = resources;
  }

  public void initPresentation() {
    initLibraryListView();
    initTrackListView();
    initListening();
  }

  private void initListening() {
    controlView.addLibraryListSelectionListener(new LibrarySelectionListener(controlView, searchControl, resources));
    libraryModel.addLibraryChangedListener(new ILibraryChangedListener() {
      public void librariesChanged(ILibrary[] allLibraries, ILibrary selectedLibrary) {
        controlView.getLibraryView().setListItems(allLibraries);
      }
    });
    selectionModel.addCurrentSelectionChangeListener(new IChangeListener() {
      public void changeOccured() {
        refreshTrackView();
      }
    });
    selectionModel.getTrackDetailModel().addChangeDetailListener(new IChangeListener() {
      public void changeOccured() {
        refreshTrackView();
      }
    });
  }

  private void initTrackListView() {
    Action removeTrackAction = new RemoveTrackFromLibraryAction(resources, controlView, libraryModel);
    controlView.getTrackListView().addAction(removeTrackAction);
  }

  private void initLibraryListView() {
    IActionAddableListView libraryView = controlView.getLibraryView();
    libraryView.addAction(new AddLibraryAction(libraryModel, resources));
    libraryView.addAction(new DeleteLibraryAction(resources, controlView, libraryModel));
    libraryView.addAction(new AddMusicFolderAction(resources, searchControl, libraryModel, controlView));
    libraryView.addAction(new AddMusicFileAction(resources, searchControl, libraryModel, controlView));
    libraryView.setListItems(libraryModel.getAllLibraries());
  }

  private void refreshTrackView() {
    controlView.getTrackListView().refreshView();
  }
}