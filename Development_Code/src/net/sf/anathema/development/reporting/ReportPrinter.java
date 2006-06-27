package net.sf.anathema.development.reporting;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import net.disy.commons.core.io.IOUtilities;
import net.sf.anathema.development.reporting.generation.IGenerationData;
import net.sf.anathema.development.reporting.generation.VoidStateSiderealGenerationData;
import net.sf.anathema.development.reporting.generation.VoidstateLunarGenerationData;
import net.sf.anathema.framework.reporting.jasper.IJasperReport;
import net.sf.anathema.framework.reporting.jasper.JasperReportPrinter;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.resources.AnathemaResources;

public class ReportPrinter {

  private JasperReportPrinter printer = new JasperReportPrinter();

  protected void compileReport(IGenerationData generationData) throws Exception {
    IItem item = generationData.createFilledCharacter();
    IJasperReport report = generationData.createReport();
    File file = generationData.createFile();
    System.err.println("Started Character: " + file.getName()); //$NON-NLS-1$
    OutputStream outputStream = null;
    try {
      outputStream = new FileOutputStream(file);
      printer.printReport(item, report, outputStream);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    finally {
      IOUtilities.close(outputStream);
      System.err.println("Finished Character: " + file.getName()); //$NON-NLS-1$
      System.err.println();
    }
  }

  public static void main(String[] args) throws Exception {
    AnathemaResources resources = new AnathemaResources();
    ReportPrinter printer = new ReportPrinter();
    IGenerationData[] generationDatas = new IGenerationData[] {
//        new VoidStateAbyssalGenerationData(resources),
//        new VoidStateSolarGenerationData(resources),
//        new VoidstateDbGenerationData(resources),
        new VoidstateLunarGenerationData(resources),
        new VoidStateSiderealGenerationData(resources),
        };
    for (IGenerationData data : generationDatas) {
      printer.compileReport(data);
    }
  }
}