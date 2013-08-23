package net.sf.anathema.hero.framework.perspective;

import net.sf.anathema.framework.reporting.Report;
import net.sf.anathema.hero.framework.perspective.model.ItemSelectionModel;
import net.sf.anathema.hero.framework.perspective.model.ReportRegister;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.platform.fx.MenuTool;

public class InteractionReportRegister implements ReportRegister {
  private MenuTool interaction;
  private ItemSelectionModel model;
  private Resources resources;

  public InteractionReportRegister(MenuTool interaction, ItemSelectionModel model, Resources resources) {
    this.interaction = interaction;
    this.model = model;
    this.resources = resources;
  }

  @Override
  public void register(final Report report) {
    Tool tool = interaction.addMenuEntry();
    tool.setText(report.toString());
    tool.setCommand(new Command() {
      @Override
      public void execute() {
        model.printCurrentItemInto(report, resources);
      }
    });
  }
}