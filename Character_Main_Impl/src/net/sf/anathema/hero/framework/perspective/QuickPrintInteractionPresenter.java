package net.sf.anathema.hero.framework.perspective;

import net.sf.anathema.framework.environment.SwingEnvironment;
import net.sf.anathema.hero.framework.perspective.model.ItemSelectionModel;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.resources.Resources;

public class QuickPrintInteractionPresenter {

  private ItemSelectionModel model;
  private Tool interaction;
  private Resources resources;

  public QuickPrintInteractionPresenter(ItemSelectionModel model, Tool interaction, Resources resources) {
    this.model = model;
    this.interaction = interaction;
    this.resources = resources;
  }

  public void initPresentation() {
    if (!SwingEnvironment.isAutoOpenSupported()) {
      return;
    }
    initializeAppearance();
    initializeEnabling();
    initializeCommand();
  }

  private void initializeAppearance() {
    interaction.setIcon(new RelativePath("icons/TaskBarPDF24.png"));
    interaction.setTooltip(resources.getString("Anathema.Reporting.Menu.QuickPrint.Name"));
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
