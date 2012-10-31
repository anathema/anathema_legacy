package net.sf.anathema.campaign.music.presenter.util;

import net.sf.anathema.campaign.music.model.util.IMusicCategorizationModel;
import net.sf.anathema.campaign.music.presenter.IMusicEvent;
import net.sf.anathema.campaign.music.presenter.IMusicMood;
import net.sf.anathema.campaign.music.presenter.IMusicTheme;
import net.sf.anathema.campaign.music.presenter.SelectionContainerPresenter;
import net.sf.anathema.campaign.music.view.categorization.IMusicCategorizationView;
import net.sf.anathema.lib.gui.Presenter;

public class MusicCategorizationPresenter implements Presenter {

  private final IMusicCategorizationModel model;
  private final IMusicCategorizationView view;

  public MusicCategorizationPresenter(IMusicCategorizationModel model, IMusicCategorizationView view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public void initPresentation() {
    new SelectionContainerPresenter<>(model.getThemesModel(), view.getThemesView(), IMusicTheme.class).initPresentation();
    new SelectionContainerPresenter<>(model.getMoodsModel(), view.getMoodsView(), IMusicMood.class).initPresentation();
    new SelectionContainerPresenter<>(model.getEventsModel(), view.getEventsView(), IMusicEvent.class).initPresentation();
  }
}
