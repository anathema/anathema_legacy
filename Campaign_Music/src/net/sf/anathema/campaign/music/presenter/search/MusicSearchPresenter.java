package net.sf.anathema.campaign.music.presenter.search;

import net.sf.anathema.campaign.music.model.IMusicDatabase;
import net.sf.anathema.campaign.music.presenter.IMusicSearchControl;
import net.sf.anathema.campaign.music.presenter.ISearchParameter;
import net.sf.anathema.campaign.music.presenter.util.MusicCategorizationPresenter;
import net.sf.anathema.campaign.music.view.library.ILibraryControlView;
import net.sf.anathema.campaign.music.view.search.ISearchComponent;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.resources.IResources;

import java.util.HashMap;
import java.util.Map;

public class MusicSearchPresenter implements Presenter {

  private final ILibraryControlView controlView;
  private final IResources resources;
  private IMusicSearchControl searchControl;
  private final Map<ISearchComponent, ISearchParameter> parametersByView = new HashMap<>();

  public MusicSearchPresenter(ILibraryControlView controlView, IMusicDatabase dataBase, IResources resources) {
    this.controlView = controlView;
    this.resources = resources;
    this.searchControl = dataBase.getMusicSearchControl();
  }

  @Override
  public void initPresentation() {
    ISearchParameter[] searchParameters = searchControl.getSearchParameters();
    for (ISearchParameter parameter : searchParameters) {
      ISearchComponent component = controlView.addSearchParameter(resources.getString(parameter.getDisplayKey()));
      parametersByView.put(component, parameter);
    }
    controlView.whenSearchIsTriggered(new Runnable() {
      @Override
      public void run() {
        Map<ISearchParameter, String> constraintsByParamter = new HashMap<>();
        for (ISearchComponent component : parametersByView.keySet()) {
          if (component.isSelected()) {
            ISearchParameter parameter = parametersByView.get(component);
            constraintsByParamter.put(parameter, component.getSearchString());
          }
        }
        searchControl.executeSearch(constraintsByParamter);
      }
    });
    initListening();
    new MusicCategorizationPresenter(
        searchControl.getMusicCategorizationModel(),
        controlView.getSearchMusicCategorizationView()).initPresentation();
  }

  private void initListening() {
    searchControl.addSearchResultChangedListener(new IChangeListener() {
      @Override
      public void changeOccurred() {
        controlView.getTrackListView().setObjects(searchControl.getSearchResult());
        controlView.getTrackListView().setListTitle(
            resources.getString("Music.Labels.LibraryTrackView.SearchResultsTitle")); //$NON-NLS-1$
      }
    });
  }
}
