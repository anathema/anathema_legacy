package net.sf.anathema.hero.framework.perspective;

import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.hero.framework.perspective.model.ItemSelectionModel;
import net.sf.anathema.hero.framework.perspective.model.ReportRegister;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.platform.view.MenuTool;

public class PrintInteractionPresenter {
  private final ItemSelectionModel model;
  private final MenuTool interaction;
  private final Environment environment;

  public PrintInteractionPresenter(ItemSelectionModel model, MenuTool interaction, Environment environment) {
    this.model = model;
    this.interaction = interaction;
    this.environment = environment;
  }

  public void initPresentation() {
    initializeAppearance();
    initializeEnabling();
    initializeUpdate();
    initializeCommand();
  }

  private void initializeAppearance() {
    interaction.setIcon(new RelativePath("icons/TaskBarPDF24.png"));
    interaction.setTooltip(environment.getString("Anathema.Reporting.Menu.PrintItem.Name"));
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
        ReportRegister reportRegister = new InteractionReportRegister(interaction, model, environment);
        model.registerAllReportsOn(reportRegister, environment);
      }
    });
  }

  private void initializeCommand() {
    interaction.setCommand(new Command() {
      @Override
      public void execute() {
        model.printCurrentItemQuickly(environment);
      }
    });
  }
}
