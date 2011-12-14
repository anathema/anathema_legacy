package net.sf.anathema.demo.campaign.music.view;

import net.disy.commons.core.util.ObjectUtilities;
import net.sf.anathema.campaign.music.impl.model.selection.AbstractTrackDetailModel;
import net.sf.anathema.campaign.music.model.util.IMusicCategorizationModel;
import net.sf.anathema.campaign.music.presenter.IMusicEvent;
import net.sf.anathema.campaign.music.presenter.IMusicMood;
import net.sf.anathema.campaign.music.presenter.IMusicTheme;
import net.sf.anathema.demo.campaign.music.DemoMusicCategorizationModel;
import net.sf.anathema.lib.workflow.container.ISelectionContainerModel;

public class DemoTrackDetailModel extends AbstractTrackDetailModel {
  
  private IMusicCategorizationModel musicCategorizationModel = new DemoMusicCategorizationModel();

  public void updateGivenName(String givenName) {
    if (ObjectUtilities.equals(givenName, getSelectedTrack().getGivenName())) {
      return;
    }
    getSelectedTrack().setGivenName(givenName);
    fireGivenNamesChangedEvent();
  }

  public ISelectionContainerModel<IMusicMood> getMoodsModel() {
    return musicCategorizationModel.getMoodsModel();
  }

  public ISelectionContainerModel<IMusicTheme> getThemesModel() {
    return musicCategorizationModel.getThemesModel();
  }

  public ISelectionContainerModel<IMusicEvent> getEventsModel() {
    return musicCategorizationModel.getEventsModel();
  }
}