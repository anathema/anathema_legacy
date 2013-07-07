package net.sf.anathema.character.platform.module.perspective;

import net.sf.anathema.character.main.perspective.CharacterGridView;
import net.sf.anathema.character.main.perspective.Selector;
import net.sf.anathema.character.main.perspective.model.CharacterIdentifier;
import net.sf.anathema.character.main.perspective.model.ItemSelectionModel;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.platform.fx.InteractionView;

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
    initNewInteraction();
    initSaveInteraction();
    initQuickPrintInteraction();
    initControlledPrintInteraction();
    initExperiencedInteraction();
  }

  private void initSaveInteraction() {
    Tool tool = view.addTool();
    new SaveInteractionPresenter(model, tool, resources).initPresentation();
  }

  private void initNewInteraction() {
    new NewInteractionPresenter(model, view.addTool(), resources, gridView, selector).initPresentation();
  }

  private void initQuickPrintInteraction() {
    new QuickPrintInteractionPresenter(model, view.addTool(), resources).initPresentation();
  }

  private void initControlledPrintInteraction() {
    new ControlledPrintInteractionPresenter(model, view.addTool(), resources).initPresentation();
  }

  private void initExperiencedInteraction() {
    new ExperiencedInteractionPresenter(model, view.addToggleTool(), resources).initPresentation();
  }
}