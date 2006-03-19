package net.sf.anathema.campaign.music.presenter;

import java.util.Map;

import javax.swing.event.ChangeListener;

import net.sf.anathema.campaign.music.model.track.IMp3Track;
import net.sf.anathema.campaign.music.model.util.IMusicCategorizationModel;

public interface IMusicSearchControl {
  public IMp3Track[] getTracks(String libraryName);

  public ISearchParameter[] getSearchParameters();

  public void executeSearch(Map<ISearchParameter, String> constraintsByParameter);

  public void addSearchResultChangedListener(ChangeListener listener);

  public IMp3Track[] getSearchResult();
  
  public IMusicCategorizationModel getMusicCategorizationModel();
}