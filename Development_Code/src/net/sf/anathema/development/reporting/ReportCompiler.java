package net.sf.anathema.development.reporting;

import java.io.File;
import java.util.ArrayList;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;

public class ReportCompiler {

  public static void main(String[] args) {
    java.util.List<File> xmlFiles = new ArrayList<File>();
    // xmlFiles.add(new File("BasicMortalCharacterSheet.xml"));
    // xmlFiles.add(new File("BasicSolarCharacterSheet.xml"));

    xmlFiles.add(new File("VoidStateCharacterSheet.xml"));
    // xmlFiles.add(new File("VoidstateNullPage.xml"));
    // xmlFiles.add(new File("VoidstateBeastformPage.xml"));
    // xmlFiles.add(new File("VoidstateVirtuesSubreport.xml"));
    // xmlFiles.add(new File("VoidstateDefaultAttributeSubreport.xml"));
    // xmlFiles.add(new File("VoidstateSingleAbilitySubreport.xml"));
    xmlFiles.add(new File("VoidstateAbilityGroupSubreport.xml"));
    // xmlFiles.add(new File("VoidstateAbilitySetSubreport.xml"));
    // xmlFiles.add(new File("VoidstateBeastformAttributeSubreport.xml"));
    // xmlFiles.add(new File("VoidstateDefaultWillpowerSubreport.xml"));
    // xmlFiles.add(new File("VoidstateDefaultWillpowerSubreport.xml"));
    // xmlFiles.add(new File("DefaultMiddleColumnSubreport.xml"));
    // xmlFiles.add(new File("VoidstateBeastformWillpowerSubreport.xml"));
    // xmlFiles.add(new File("VoidstateBeastformGiftsSubreport.xml"));
    // xmlFiles.add(new File("BeastformMiddleColumnSubreport.xml"));
    // xmlFiles.add(new File("VoidstateCharmPageSubreport.xml"));
    // xmlFiles.add(new File("VoidstateDefaultDescriptionSubreport.xml"));
    // xmlFiles.add(new File("VoidStateBrawlSubreport.xml"));
    // xmlFiles.add(new File("VoidStateSequenceSubreport.xml"));
    // xmlFiles.add(new File("VoidstateDefaultHealthSubreport.xml"));
    // xmlFiles.add(new File("VoidstateCombatStatsSubreport.xml"));

    // xmlFiles.add(new File("VoidStateAbyssalAbilitySubreport.xml"));
    // xmlFiles.add(new File("VoidStateAbyssalAnimaSubreport.xml"));
    // xmlFiles.add(new File("VoidStateAbyssalFlawSubreport.xml"));
    //
    // xmlFiles.add(new File("VoidStateDbAbilitySubreport.xml"));
    // xmlFiles.add(new File("VoidStateDbAnimaSubreport.xml"));
    // xmlFiles.add(new File("VoidStateDbFlawSubreport.xml"));
    //
    // xmlFiles.add(new File("VoidStateSiderealAbilitySubreport.xml"));
    // xmlFiles.add(new File("VoidStateSiderealAnimaSubreport.xml"));
    // xmlFiles.add(new File("VoidstateSiderealDescriptionSubreport.xml"));
    // xmlFiles.add(new File("VoidStateSiderealFlawSubreport.xml"));

    // xmlFiles.add(new File("VoidstateLunarAttributeSubreport.xml"));
    // xmlFiles.add(new File("VoidStateLunarAbilitySubreport.xml"));
    // xmlFiles.add(new File("VoidStateLunarAnimaSubreport.xml"));
    // xmlFiles.add(new File("VoidStateLunarFlawSubreport.xml"));
    // xmlFiles.add(new File("VoidstateLunarHealthSubreport.xml"));
    // xmlFiles.add(new File("VoidstateLunarDescriptionSubreport.xml"));

    xmlFiles.add(new File("VoidStateSolarAbilitySubreport.xml"));
    // xmlFiles.add(new File("VoidStateSolarAnimaSubreport.xml"));
    // xmlFiles.add(new File("VoidStateSolarFlawSubreport.xml"));
    // File[] xmlFiles = new File(".").listFiles(new XMLFilter(false));
    for (File reportXMLFile : xmlFiles) {
      try {
        System.err.println("Compiling file: " + reportXMLFile.getName());
        compileJasper(reportXMLFile);
      }
      catch (JRException e) {
        e.printStackTrace();
      }
    }
  }

  public static void compileJasper(File file) throws JRException {
    JasperCompileManager.compileReportToFile(file.getAbsolutePath());
  }
}