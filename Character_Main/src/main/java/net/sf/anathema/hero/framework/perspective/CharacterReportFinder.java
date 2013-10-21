package net.sf.anathema.hero.framework.perspective;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.initialization.IReportFactory;
import net.sf.anathema.framework.reporting.Report;
import net.sf.anathema.character.main.framework.item.Item;
import net.sf.anathema.hero.framework.perspective.sheet.AllReportFinder;
import net.sf.anathema.hero.framework.perspective.sheet.DefaultReportFinder;
import net.sf.anathema.initialization.RegisteredReportFactory;
import net.sf.anathema.framework.environment.Resources;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CharacterReportFinder implements DefaultReportFinder, AllReportFinder {

  private final Resources resources;
  private final List<Report> reports = new ArrayList<>();

  public CharacterReportFinder(IApplicationModel model, Environment environment) {
    Collection<IReportFactory> factories = environment.instantiateOrdered(RegisteredReportFactory.class);
    for (IReportFactory factory : factories) {
      Collections.addAll(reports, factory.createReport(environment, model));
    }
    this.resources = environment;
  }

  public Report getDefaultReport(Item item) {
    String reportName = resources.getString("CharacterModule.Reporting.Sheet.Name");
    for (Report report : getAllReports(item)) {
      if (reportName.equals(report.toString())) {
        return report;
      }
    }
    return null;
  }

  @Override
  public List<Report> getAllReports(Item item) {
    List<Report> supportedReports = new ArrayList<>();
    for (Report report : reports) {
      if (report.supports(item)) {
        supportedReports.add(report);
      }
    }
    return supportedReports;
  }
}