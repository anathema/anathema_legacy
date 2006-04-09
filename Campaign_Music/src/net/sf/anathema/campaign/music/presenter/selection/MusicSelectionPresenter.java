package net.sf.anathema.campaign.music.presenter.selection;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.sf.anathema.campaign.music.model.selection.IMusicSelectionModel;
import net.sf.anathema.campaign.music.model.track.IMp3Track;
import net.sf.anathema.campaign.music.view.selection.IMusicSelectionView;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.gui.list.actionview.IActionAddableListView;
import net.sf.anathema.lib.gui.list.actionview.IMultiSelectionActionAddableListView;
import net.sf.anathema.lib.resources.IResources;

public class MusicSelectionPresenter {

  private final IMusicSelectionView selectionView;
  private final IMusicSelectionModel selectionModel;
  private final IResources resources;

  public MusicSelectionPresenter(
      IResources resources,
      IMusicSelectionView selectionView,
      IMusicSelectionModel selectionModel) {
    this.resources = resources;
    this.selectionView = selectionView;
    this.selectionModel = selectionModel;
  }

  public void initPresentation() {
    selectionModel.addCurrentSelectionChangeListener(new IChangeListener() {
      public void changeOccured() {
        updateTrackList();
      }
    });
    selectionModel.getTrackDetailModel().addChangeDetailListener(new IChangeListener() {
      public void changeOccured() {
        refreshTrackList();
      }
    });
    selectionModel.addSelectionsChangeListener(new IChangeListener() {
      public void changeOccured() {
        updateSelectionList();
      }
    });
    updateTrackList();
    updateSelectionList();
    final IMultiSelectionActionAddableListView trackListView = selectionView.getTrackListView();
    IActionAddableListView selectionListView = selectionView.getSelectionListView();
    selectionListView.addAction(new NewSelectionAction(resources, selectionModel));
    selectionListView.addAction(new DeleteSelectionAction(resources, selectionListView, selectionModel));
    selectionListView.addAction(new PersistReplaceSelectionAction(selectionListView, selectionModel, resources));
    selectionListView.addAction(new PersistAppendSelectionAction(selectionListView, selectionModel, resources));
    selectionListView.addAction(new RetrieveSelectionAction(resources, selectionListView, selectionModel));
    trackListView.addAction(new ClearSelectionAction(resources, selectionModel));
    trackListView.addAction(new DeleteSelectionTracksAction(resources, trackListView, selectionModel));
    trackListView.addAction(new ExportSelectionTracksAction(resources, selectionModel));
    trackListView.addListSelectionListener(new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent e) {
        List<IMp3Track> trackList = new ArrayList<IMp3Track>();
        for (Object trackObject : trackListView.getSelectedItems()) {
          if (trackObject instanceof IMp3Track) {
            trackList.add((IMp3Track) trackObject);
          }
        }
        selectionModel.setMarkedTracks(trackList.toArray(new IMp3Track[trackList.size()]));
      }
    });
  }

  private void refreshTrackList() {
    selectionView.getTrackListView().refreshView();
  }

  private void updateTrackList() {
    selectionView.getTrackListView().setListItems(selectionModel.getCurrentSelection().getContent());
  }

  private void updateSelectionList() {
    selectionView.getSelectionListView().setListItems(selectionModel.getPersistedSelections());
  }
}