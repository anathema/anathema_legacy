package net.sf.anathema.character.platform.module.perspective;

import net.sf.anathema.character.perspective.model.ItemSelectionModel;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.resources.Resources;

public class ControlledPrintInteractionPresenter {
  private ItemSelectionModel model;
  private Tool interaction;
  private Resources resources;

  public ControlledPrintInteractionPresenter(ItemSelectionModel model, Tool interaction, Resources resources) {
    this.model = model;
    this.interaction = interaction;
    this.resources = resources;
  }

  public void initPresentation() {
    initializeAppearance();
    initializeEnabling();
    initializeCommand();
  }

  private void initializeAppearance() {
    interaction.setIcon(new RelativePath("icons/TaskBarPDFArrow24.png"));
    interaction.setTooltip(resources.getString("Anathema.Reporting.Menu.PrintItem.Name"));
  }

  private void initializeEnabling() {
    model.whenGetsSelection(new EnableInteraction(interaction));
    interaction.disable();
  }

  private void initializeCommand() {
    interaction.setCommand(new Command() {
      @Override
      public void execute() {
        model.printCurrentItemControlled(resources);
      }
    });
  }
}
