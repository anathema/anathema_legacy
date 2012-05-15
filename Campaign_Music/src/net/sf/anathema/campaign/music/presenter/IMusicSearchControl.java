package net.sf.anathema.campaign.music.presenter;

import net.sf.anathema.campaign.music.model.track.IMp3Track;
import net.sf.anathema.campaign.music.model.util.IMusicCategorizationModel;
import net.sf.anathema.lib.control.IChangeListener;

import java.util.Map;

public interface IMusicSearchControl {
  IMp3Track[] getTracks(String libraryName);

  ISearchParameter[] getSearchParameters();

  void executeSearch(Map<ISearchParameter, String> constraintsByParameter);

  void addSearchResultChangedListener(IChangeListener listener);

  IMp3Track[] getSearchResult();

  IMusicCategorizationModel getMusicCategorizationModel();
}