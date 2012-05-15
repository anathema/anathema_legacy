package net.sf.anathema.campaign.music.model.util;

import net.sf.anathema.campaign.music.presenter.IMusicEvent;
import net.sf.anathema.campaign.music.presenter.IMusicMood;
import net.sf.anathema.campaign.music.presenter.IMusicTheme;
import net.sf.anathema.lib.workflow.container.ISelectionContainerModel;

public interface IMusicCategorizationModel {

  ISelectionContainerModel<IMusicMood> getMoodsModel();

  ISelectionContainerModel<IMusicTheme> getThemesModel();

  ISelectionContainerModel<IMusicEvent> getEventsModel();
}
