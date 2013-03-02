package net.sf.anathema.character.impl.module.perspective;

import net.sf.anathema.character.perspective.model.model.ItemSelectionModel;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.swing.character.perspective.interaction.Interaction;

public class ControlledPrintInteractionPresenter {
  private ItemSelectionModel model;
  private Interaction interaction;
  private IResources resources;

  public ControlledPrintInteractionPresenter(ItemSelectionModel model, Interaction interaction, IResources resources) {
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
    interaction.setIcon("TaskBarPDFArrow24.png");
    interaction.setTooltip("Anathema.Reporting.Menu.PrintItem.Name");
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
