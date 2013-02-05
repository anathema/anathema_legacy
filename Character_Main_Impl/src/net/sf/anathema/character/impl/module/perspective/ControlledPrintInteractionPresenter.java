package net.sf.anathema.character.impl.module.perspective;

import net.sf.anathema.character.perspective.model.model.ItemSelectionModel;
import net.sf.anathema.swing.character.perspective.interaction.Interaction;

public class ControlledPrintInteractionPresenter {
  private ItemSelectionModel model;
  private Interaction interaction;

  public ControlledPrintInteractionPresenter(ItemSelectionModel model, Interaction interaction) {
    this.model = model;
    this.interaction = interaction;
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
    //To change body of created methods use File | Settings | File Templates.
  }
}
