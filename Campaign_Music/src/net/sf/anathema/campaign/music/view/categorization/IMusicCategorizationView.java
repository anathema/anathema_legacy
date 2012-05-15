package net.sf.anathema.campaign.music.view.categorization;

import net.sf.anathema.campaign.music.presenter.IMusicEvent;
import net.sf.anathema.campaign.music.presenter.IMusicMood;
import net.sf.anathema.campaign.music.presenter.IMusicTheme;
import net.sf.anathema.lib.workflow.container.ISelectionContainerView;

public interface IMusicCategorizationView {

  ISelectionContainerView<IMusicMood> getMoodsView();

  ISelectionContainerView<IMusicEvent> getEventsView();

  ISelectionContainerView<IMusicTheme> getThemesView();
}
