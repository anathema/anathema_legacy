package net.sf.anathema.character.impl.module.perspective;

import net.sf.anathema.character.perspective.model.ItemSelectionModel;
import net.sf.anathema.swing.character.perspective.interaction.Command;
import net.sf.anathema.swing.character.perspective.interaction.Interaction;

import java.io.IOException;

public class SaveInteractionPresenter {

  private final ItemSelectionModel model;
  private final Interaction interaction;

  public SaveInteractionPresenter(ItemSelectionModel model, Interaction interaction) {
    this.model = model;
    this.interaction = interaction;
  }

  public void initPresentation() {
    initializeAppearance();
    initializeEnabling();
    initializeCommand();
  }

  private void initializeAppearance() {
    interaction.setTooltip("AnathemaPersistence.SaveAction.Tooltip");
    interaction.setIcon("TaskBarSave24.png");
  }

  private void initializeEnabling() {
    model.whenCurrentSelectionBecomesDirty(new EnableInteraction(interaction));
    model.whenCurrentSelectionBecomesClean(new DisableInteraction(interaction));
    interaction.disable();
  }

  private void initializeCommand() {
    interaction.setCommand(new SaveCurrent());
  }

  private class SaveCurrent implements Command {
    @Override
    public void execute() {
      try {
        model.saveCurrent();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
