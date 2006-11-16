package net.sf.anathema.development.reporting;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import net.disy.commons.core.io.IOUtilities;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.development.reporting.encoder.BasicSolarSheetEncoder;
import net.sf.anathema.development.reporting.encoder.page.IPageFormat;
import net.sf.anathema.development.reporting.encoder.page.PageFormat;
import net.sf.anathema.development.reporting.encoder.voidstate.VoidstateSheetEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.format.VoidstateBasicsEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.subreports.ability.VoidstateAbilitySubreportEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.subreports.ability.VoidstateFiveAbilityGroupSubreportEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.subreports.ability.VoidstateFiveGroupAbilitySetSubreportEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.subreports.ability.VoidstateSingleAbilitySubreportEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.subreports.ability.VoidstateTenAbilityGroupSubreportEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.subreports.ability.VoidstateThreeGroupAbilitySetSubreportEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.subreports.anima.VoidstateLunarAnimaSubreportEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.subreports.anima.VoidstateSiderealAnimaSubreportEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.subreports.attribute.VoidstateBeastformAttributeSubreportEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.subreports.attribute.VoidstateDefaultAttributeSubreportEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.subreports.attribute.VoidstateLunarAttributeSubreportEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.subreports.description.VoidstateDefaultDescriptionSubreportEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.subreports.description.VoidstateLunarDescriptionSubreportEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.subreports.health.VoidstateDefaultHealthSubreportEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.subreports.health.VoidstateLunarHealthSubreportEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.subreports.middlecolumn.BeastformMiddleColumnSubreportEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.subreports.middlecolumn.DefaultMiddleColumnSubreportEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.subreports.middlecolumn.flaw.VoidstateLunarFlawSubreportEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.subreports.middlecolumn.flaw.VoidstateSiderealFlawSubreportEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.subreports.middlecolumn.gifts.VoidstateBeastformGiftsSubreportEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.subreports.middlecolumn.virtues.VoidstateVirtuesSubreportEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.subreports.middlecolumn.willpower.VoidstateBeastformWillpowerSubreportEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.subreports.middlecolumn.willpower.VoidstateDefaultWillpowerSubreportEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.traits.VoidStateAbilityEncoder;
import net.sf.anathema.development.reporting.util.TraitEncoder;
import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;

@SuppressWarnings("unused")
public class ReportBuilder {

  public static final IPageFormat VOID_STATE_PAGE_FORMAT = PageFormat.createDefault(15, 15);
  private static final VoidstateBasicsEncoder basicsEncoder = new VoidstateBasicsEncoder(
      VOID_STATE_PAGE_FORMAT.getColumnWidth());
  private static final TraitEncoder traitEncoder = new TraitEncoder(
      IVoidStateFormatConstants.SMALL_SYMBOL_HEIGHT,
      IVoidStateFormatConstants.LINE_HEIGHT,
      IVoidStateFormatConstants.FONT_SIZE);

  public static void main(String[] args) throws InitializationException {
    buildReportDesign(new File("VoidStateCharacterSheet.xml"), new VoidstateSheetEncoder()); //$NON-NLS-1$
    createAbilitySubreports();
  }

  private static void createBasicSheets() throws InitializationException {
    buildReportDesign(new File("BasicSolarCharacterSheet.xml"), new BasicSolarSheetEncoder()); //$NON-NLS-1$
  }

  private static void createAttributeSubreports() throws InitializationException {
    buildReportDesign(
        new File("VoidstateDefaultAttributeSubreport.xml"), new VoidstateDefaultAttributeSubreportEncoder(basicsEncoder, traitEncoder)); //$NON-NLS-1$
    buildReportDesign(
        new File("VoidstateLunarAttributeSubreport.xml"), new VoidstateLunarAttributeSubreportEncoder(basicsEncoder, traitEncoder)); //$NON-NLS-1$
    buildReportDesign(
        new File("VoidstateBeastformAttributeSubreport.xml"), new VoidstateBeastformAttributeSubreportEncoder(basicsEncoder, traitEncoder)); //$NON-NLS-1$
  }

  private static void createMiddleColumnSubreports() throws InitializationException {
    createFlawSubreports();
    buildReportDesign(new File("VoidstateBeastformGiftsSubreport.xml"), new VoidstateBeastformGiftsSubreportEncoder(
        basicsEncoder));
    buildReportDesign(
        new File("VoidstateDefaultWillpowerSubreport.xml"),
        new VoidstateDefaultWillpowerSubreportEncoder(basicsEncoder));
    buildReportDesign(
        new File("VoidstateBeastformWillpowerSubreport.xml"),
        new VoidstateBeastformWillpowerSubreportEncoder(basicsEncoder));
    buildReportDesign(new File("VoidstateVirtuesSubreport.xml"), new VoidstateVirtuesSubreportEncoder(
        basicsEncoder,
        traitEncoder));
    buildReportDesign(new File("DefaultMiddleColumnSubreport.xml"), new DefaultMiddleColumnSubreportEncoder(
        basicsEncoder));
    buildReportDesign(new File("BeastformMiddleColumnSubreport.xml"), new BeastformMiddleColumnSubreportEncoder(
        basicsEncoder));
  }

  private static void createHealthSubreports() throws InitializationException {
    buildReportDesign(new File("VoidstateDefaultHealthSubreport.xml"), new VoidstateDefaultHealthSubreportEncoder());
    buildReportDesign(new File("VoidstateLunarHealthSubreport.xml"), new VoidstateLunarHealthSubreportEncoder());
  }

  private static void createAnimaSubreports() throws InitializationException {
    buildReportDesign(new File("VoidStateLunarAnimaSubreport.xml"), new VoidstateLunarAnimaSubreportEncoder(
        basicsEncoder));
    buildReportDesign(new File("VoidStateSiderealAnimaSubreport.xml"), new VoidstateSiderealAnimaSubreportEncoder(
        basicsEncoder));
  }

  private static void createDescriptionSubreports() throws InitializationException {
    buildReportDesign(
        new File("VoidstateLunarDescriptionSubreport.xml"),
        new VoidstateLunarDescriptionSubreportEncoder(basicsEncoder));
    buildReportDesign(
        new File("VoidstateDefaultDescriptionSubreport.xml"),
        new VoidstateDefaultDescriptionSubreportEncoder(basicsEncoder));
  }

  private static void createFlawSubreports() throws InitializationException {
    buildReportDesign(
        new File("VoidStateLunarFlawSubreport.xml"),
        new VoidstateLunarFlawSubreportEncoder(basicsEncoder));
    buildReportDesign(new File("VoidStateSiderealFlawSubreport.xml"), new VoidstateSiderealFlawSubreportEncoder(
        basicsEncoder));
  }

  private static void createAbilitySubreports() throws InitializationException {
    VoidStateAbilityEncoder abilityEncoder = new VoidStateAbilityEncoder(basicsEncoder, traitEncoder);
    buildReportDesign(new File("VoidstateAbilitySubreport.xml"), new VoidstateAbilitySubreportEncoder(abilityEncoder)); //$NON-NLS-1$
    buildReportDesign(new File("VoidstateSingleAbilitySubreport.xml"), new VoidstateSingleAbilitySubreportEncoder(
        basicsEncoder,
        traitEncoder)); //$NON-NLS-1$
    buildReportDesign(
        new File("VoidstateFiveAbilityGroupSubreport.xml"),
        new VoidstateFiveAbilityGroupSubreportEncoder(basicsEncoder)); //$NON-NLS-1$
    buildReportDesign(new File("VoidstateTenAbilityGroupSubreport.xml"), new VoidstateTenAbilityGroupSubreportEncoder(
        basicsEncoder)); //$NON-NLS-1$
    buildReportDesign(
        new File("VoidstateThreeGroupAbilitySetSubreport.xml"),
        new VoidstateThreeGroupAbilitySetSubreportEncoder(basicsEncoder)); //$NON-NLS-1$
    buildReportDesign(
        new File("VoidstateFiveGroupAbilitySetSubreport.xml"),
        new VoidstateFiveGroupAbilitySetSubreportEncoder(basicsEncoder)); //$NON-NLS-1$
  }

  private static void buildReportDesign(File file, IReportEncoder encoder) {
    System.err.println("Starting ReportDesign " + file.getAbsolutePath() + "..."); //$NON-NLS-1$ //$NON-NLS-2$
    Document designDocument = encoder.encode();
    OutputStream outputStream = null;
    try {
      outputStream = new FileOutputStream(file);
      DocumentUtilities.save(designDocument, outputStream);
      outputStream.flush();
    }
    catch (Throwable t) {
      System.err.println("Error building report " + file.getName()); //$NON-NLS-1$
      t.printStackTrace();
    }
    finally {
      System.err.println("Finished ReportDesign " + file.getAbsolutePath()); //$NON-NLS-1$
      IOUtilities.close(outputStream);
    }
  }
}