package net.sf.anathema.character.platform.module.perspective;

import net.sf.anathema.character.perspective.model.model.ItemSelectionModel;
import net.sf.anathema.framework.reporting.AbstractPrintAction;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.swing.character.perspective.interaction.Interaction;

public class QuickPrintInteractionPresenter {

  private ItemSelectionModel model;
  private Interaction interaction;
  private IResources resources;

  public QuickPrintInteractionPresenter(ItemSelectionModel model, Interaction interaction, IResources resources) {
    this.model = model;
    this.interaction = interaction;
    this.resources = resources;
  }

  public void initPresentation() {
    if (!AbstractPrintAction.isAutoOpenSupported()) {
      return;
    }
    initializeAppearance();
    initializeEnabling();
    initializeCommand();
  }

  private void initializeAppearance() {
    interaction.setIcon("TaskBarPDF24.png");
    interaction.setTooltip("Anathema.Reporting.Menu.QuickPrint.Name");
  }

  private void initializeEnabling() {
    model.whenGetsSelection(new EnableInteraction(interaction));
    interaction.disable();
  }

  private void initializeCommand() {
    interaction.setCommand(new Command() {
      @Override
      public void execute() {
        model.printCurrentItemQuickly(resources);
      }
    });
  }
}
