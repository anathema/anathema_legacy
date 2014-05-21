package net.sf.anathema.hero.framework.perspective;

import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.character.framework.reporting.Report;
import net.sf.anathema.hero.framework.perspective.model.ItemSelectionModel;
import net.sf.anathema.hero.framework.perspective.model.ReportRegister;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.platform.view.MenuTool;

public class InteractionReportRegister implements ReportRegister {
  private final MenuTool interaction;
  private final ItemSelectionModel model;
  private final Environment environment;

  public InteractionReportRegister(MenuTool interaction, ItemSelectionModel model, Environment environment) {
    this.interaction = interaction;
    this.model = model;
    this.environment = environment;
  }

  @Override
  public void register(final Report report) {
    Tool tool = interaction.addMenuEntry();
    tool.setText(report.toString());
    tool.setCommand(new Command() {
      @Override
      public void execute() {
        model.printCurrentItemInto(report, environment);
      }
    });
  }
}