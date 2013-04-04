package net.sf.anathema.character.platform.module.perspective;

import net.sf.anathema.character.perspective.CharacterGridView;
import net.sf.anathema.character.perspective.Selector;
import net.sf.anathema.character.perspective.model.model.CharacterIdentifier;
import net.sf.anathema.character.perspective.model.model.ItemSelectionModel;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.swing.character.perspective.InteractionView;

public class InteractionPresenter {

  private ItemSelectionModel model;
  private InteractionView view;
  private Resources resources;
  private CharacterGridView gridView;
  private Selector<CharacterIdentifier> selector;

  public InteractionPresenter(ItemSelectionModel model, InteractionView view, Resources resources, CharacterGridView gridView,
                              Selector<CharacterIdentifier> selector) {
    this.model = model;
    this.view = view;
    this.resources = resources;
    this.gridView = gridView;
    this.selector = selector;
  }

  public void initPresentation() {
    initSaveInteraction();
    initNewInteraction();
    initExperiencedInteraction();
    initQuickPrintInteraction();
    initControlledPrintInteraction();
  }

  private void initSaveInteraction() {
    new SaveInteractionPresenter(model, view.getSaveInteraction()).initPresentation();
  }

  private void initNewInteraction() {
    new NewInteractionPresenter(model, view.getNewInteraction(), resources, gridView, selector).initPresentation();
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
}
