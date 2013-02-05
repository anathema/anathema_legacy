package net.sf.anathema.character.impl.module.perspective;

import net.sf.anathema.character.perspective.model.ItemSelectionModel;
import net.sf.anathema.swing.character.perspective.InteractionView;

public class InteractionPresenter {

  private ItemSelectionModel model;
  private InteractionView view;

  public InteractionPresenter(ItemSelectionModel model, InteractionView view) {
    this.model = model;
    this.view = view;
  }

  public void initPresentation() {
    initSaveInteraction();
    initExperiencedInteraction();
  }

  private void initExperiencedInteraction() {
    new ExperiencedInteractionPresenter(model, view.getExperiencedInteraction()).initPresentation();
  }

  private void initSaveInteraction() {
    new SaveInteractionPresenter(model, view.getSaveInteraction()).initPresentation();
  }
}
