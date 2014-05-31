package net.sf.anathema.hero.framework.perspective;

import net.sf.anathema.character.framework.reporting.Report;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.hero.framework.perspective.model.ItemSelectionModel;
import net.sf.anathema.hero.framework.perspective.model.ReportRegister;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.file.SingleFileChooser;
import net.sf.anathema.platform.view.MenuTool;

public class InteractionReportRegister implements ReportRegister {
  private final MenuTool interaction;
  private final ItemSelectionModel model;
  private final Environment environment;
  private final SingleFileChooser fileChooser;

  public InteractionReportRegister(MenuTool interaction, ItemSelectionModel model, Environment environment, SingleFileChooser uiEnvironment) {
    this.interaction = interaction;
    this.model = model;
    this.environment = environment;
    this.fileChooser = uiEnvironment;
  }

  @Override
  public void register(final Report report) {
    Tool tool = interaction.addMenuEntry();
    tool.setText(report.toString());
    tool.setCommand(() -> model.printCurrentItemInto(report, environment, fileChooser));
  }
}