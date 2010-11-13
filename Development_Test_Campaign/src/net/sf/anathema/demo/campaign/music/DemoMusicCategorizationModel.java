package net.sf.anathema.demo.campaign.music;

import net.sf.anathema.campaign.music.impl.persistence.categorization.EventProvider;
import net.sf.anathema.campaign.music.impl.persistence.categorization.MoodProvider;
import net.sf.anathema.campaign.music.impl.persistence.categorization.ThemeProvider;
import net.sf.anathema.campaign.music.model.util.IMusicCategorizationModel;
import net.sf.anathema.campaign.music.presenter.IMusicEvent;
import net.sf.anathema.campaign.music.presenter.IMusicMood;
import net.sf.anathema.campaign.music.presenter.IMusicTheme;
import net.sf.anathema.lib.workflow.container.ISelectionContainerModel;
import net.sf.anathema.lib.workflow.container.model.SelectionContainerModel;

public class DemoMusicCategorizationModel implements IMusicCategorizationModel {

  private final ISelectionContainerModel<IMusicMood> moodsModel = SelectionContainerModel.createDefault(
      IMusicMood.class,
      MoodProvider.createDefaultFeelings());
  private final ISelectionContainerModel<IMusicTheme> themesModel = SelectionContainerModel.createDefault(
      IMusicTheme.class,
      ThemeProvider.createDefaultThemes());
  private final ISelectionContainerModel<IMusicEvent> eventModel = SelectionContainerModel.createDefault(
      IMusicEvent.class,
      EventProvider.createDefaultMoods());

  public ISelectionContainerModel<IMusicMood> getMoodsModel() {
    return moodsModel;
  }

  public ISelectionContainerModel<IMusicTheme> getThemesModel() {
    return themesModel;
  }

  public ISelectionContainerModel<IMusicEvent> getEventsModel() {
    return eventModel;
  }
}
