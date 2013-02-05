package net.sf.anathema.character.impl.module.perspective;

import net.sf.anathema.character.perspective.model.ItemSelectionModel;
import net.sf.anathema.swing.character.perspective.interaction.Interaction;

public class ExperiencedInteractionPresenter {
  private ItemSelectionModel model;
  private Interaction interaction;

  public ExperiencedInteractionPresenter(ItemSelectionModel model, Interaction interaction) {

    this.model = model;
    this.interaction = interaction;
  }

  public void initPresentation() {
    initializeAppearance();
    initializeEnabling();
  }

  private void initializeAppearance() {
    interaction.setIcon("ToolXp.png");
    interaction.setTooltip("CharacterTool.ToExperienced.Tooltip");
  }

  private void initializeEnabling() {
    model.whenGetsSelection(new EnableInteraction(interaction));
    interaction.disable();;
  }
}
