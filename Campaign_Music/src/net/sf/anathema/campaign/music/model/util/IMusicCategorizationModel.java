package net.sf.anathema.campaign.music.model.util;

import net.sf.anathema.campaign.music.presenter.IMusicEvent;
import net.sf.anathema.campaign.music.presenter.IMusicMood;
import net.sf.anathema.campaign.music.presenter.IMusicTheme;
import net.sf.anathema.lib.workflow.container.ISelectionContainerModel;

public interface IMusicCategorizationModel {

  public ISelectionContainerModel<IMusicMood> getMoodsModel();

  public ISelectionContainerModel<IMusicTheme> getThemesModel();

  public ISelectionContainerModel<IMusicEvent> getEventsModel();
}
