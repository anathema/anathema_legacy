package net.sf.anathema.hero.framework.perspective;

import net.sf.anathema.hero.framework.perspective.model.ItemSelectionModel;
import net.sf.anathema.hero.framework.perspective.model.ReportRegister;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.platform.fx.MenuTool;

public class PrintInteractionPresenter {
  private ItemSelectionModel model;
  private MenuTool interaction;
  private Resources resources;

  public PrintInteractionPresenter(ItemSelectionModel model, MenuTool interaction, Resources resources) {
    this.model = model;
    this.interaction = interaction;
    this.resources = resources;
  }

  public void initPresentation() {
    initializeAppearance();
    initializeEnabling();
    initializeUpdate();
    initializeCommand();
  }

  private void initializeAppearance() {
    interaction.setIcon(new RelativePath("icons/TaskBarPDF24.png"));
    interaction.setTooltip(resources.getString("Anathema.Reporting.Menu.PrintItem.Name"));
  }

  private void initializeEnabling() {
    model.whenGetsSelection(new EnableInteraction(interaction));
    interaction.disable();
  }

  private void initializeUpdate() {
    model.whenGetsSelection(new ChangeListener() {
      @Override
      public void changeOccurred() {
        interaction.clearMenu();
        ReportRegister reportRegister = new InteractionReportRegister(interaction, model, resources);
        model.registerAllReportsOn(reportRegister, resources);
      }
    });
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
