package net.sf.anathema.character.impl.module.perspective;

import net.sf.anathema.character.perspective.model.model.ItemSelectionModel;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.swing.character.perspective.InteractionView;

public class InteractionPresenter {

  private ItemSelectionModel model;
  private InteractionView view;
  private IResources resources;

  public InteractionPresenter(ItemSelectionModel model, InteractionView view, IResources resources) {
    this.model = model;
    this.view = view;
    this.resources = resources;
  }

  public void initPresentation() {
    initSaveInteraction();
    initExperiencedInteraction();
    initQuickPrintInteraction();
    initControlledPrintInteraction();
  }

  private void initQuickPrintInteraction() {
    new QuickPrintInteractionPresenter(model, view.getQuickPrintInteraction(), resources).initPresentation();
  }

  private void initControlledPrintInteraction() {
    new ControlledPrintInteractionPresenter(model, view.getControlledPrintInteraction(), resources).initPresentation();
  }

  private void initExperiencedInteraction() {
    new ExperiencedInteractionPresenter(model, view.getExperiencedInteraction()).initPresentation();
  }

  private void initSaveInteraction() {
    new SaveInteractionPresenter(model, view.getSaveInteraction()).initPresentation();
  }
}
