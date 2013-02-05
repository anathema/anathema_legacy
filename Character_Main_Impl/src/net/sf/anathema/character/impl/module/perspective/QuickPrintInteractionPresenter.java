package net.sf.anathema.character.impl.module.perspective;

import net.sf.anathema.character.perspective.model.model.ItemSelectionModel;
import net.sf.anathema.framework.reporting.AbstractPrintAction;
import net.sf.anathema.swing.character.perspective.interaction.Interaction;

public class QuickPrintInteractionPresenter {

  private ItemSelectionModel model;
  private Interaction interaction;

  public QuickPrintInteractionPresenter(ItemSelectionModel model, Interaction interaction) {
    this.model = model;
    this.interaction = interaction;
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
    //To change body of created methods use File | Settings | File Templates.
  }
}
