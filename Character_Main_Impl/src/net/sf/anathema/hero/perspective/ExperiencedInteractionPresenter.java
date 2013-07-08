package net.sf.anathema.hero.perspective;

import net.sf.anathema.character.main.perspective.model.ItemSelectionModel;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.ToggleTool;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.resources.Resources;

public class ExperiencedInteractionPresenter {
  private ItemSelectionModel model;
  private ToggleTool interaction;
  private Resources resources;

  public ExperiencedInteractionPresenter(ItemSelectionModel model, ToggleTool interaction, Resources resources) {
    this.model = model;
    this.interaction = interaction;
    this.resources = resources;
  }

  public void initPresentation() {
    initializeAppearance();
    initializeEnabling();
    initializeToggling();
    initializeCommand();
  }

  private void initializeAppearance() {
    interaction.setIcon(new RelativePath("icons/ToolXp.png"));
    interaction.setTooltip(resources.getString("CharacterTool.ToExperienced.Tooltip"));
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
