package net.sf.anathema.hero.framework.perspective.sheet;

import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.reporting.Report;
import net.sf.anathema.hero.description.HeroDescriptionFetcher;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.gui.file.FileChooserConfiguration;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

import static net.sf.anathema.hero.framework.perspective.sheet.PrintCommand.PDF_EXTENSION;

public class ControlledPrintWithSelectedReport {
  private final Environment environment;
  private final Hero hero;
  private final Report report;

  public ControlledPrintWithSelectedReport(Environment environment, Report report, Hero hero) {
    this.environment = environment;
    this.report = report;
    this.hero = hero;
  }

  public void execute() {
    String name = getFileNameSuggestion()+PDF_EXTENSION;
    FileChooserConfiguration configuration = new FileChooserConfiguration("PDF Files", "*" + PDF_EXTENSION, name);
    ControlledFileChooser fileChooser = new ControlledFileChooser(environment, configuration);
    new PrintCommand(environment, report, fileChooser, hero).execute();
  }

  private String getFileNameSuggestion() {
    ITextualDescription nameContainer = HeroDescriptionFetcher.fetch(hero).getName();
    if (nameContainer.isEmpty()) {
      return "Character";
    }
    return nameContainer.getText();
  }
}