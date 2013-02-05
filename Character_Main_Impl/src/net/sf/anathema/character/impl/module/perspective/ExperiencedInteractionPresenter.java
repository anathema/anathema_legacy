package net.sf.anathema.character.impl.module.perspective;

import net.sf.anathema.character.perspective.model.model.ItemSelectionModel;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.swing.character.perspective.interaction.ToggleInteraction;

public class ExperiencedInteractionPresenter {
  private ItemSelectionModel model;
  private ToggleInteraction interaction;

  public ExperiencedInteractionPresenter(ItemSelectionModel model, ToggleInteraction interaction) {

    this.model = model;
    this.interaction = interaction;
  }

  public void initPresentation() {
    initializeAppearance();
    initializeEnabling();
    initializeToggling();
    initializeCommand();
  }

  private void initializeAppearance() {
    interaction.setIcon("ToolXp.png");
    interaction.setTooltip("CharacterTool.ToExperienced.Tooltip");
  }

  private void initializeEnabling() {
    model.whenGetsSelection(new EnableInteraction(interaction));
    interaction.disable();
  }

  private void initializeToggling() {
    model.whenCurrentSelectionBecomesInexperienced(new DeselectInteraction(interaction));
    model.whenCurrentSelectionBecomesExperienced(new SelectInteraction(interaction));
  }

  private void initializeCommand() {
    interaction.setCommand(new Command() {
      @Override
      public void execute() {
        model.convertCurrentToExperienced();
        interaction.select();
      }
    });
  }
}
