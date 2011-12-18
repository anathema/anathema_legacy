package net.sf.anathema.character.reporting.pdf.layout.extended;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.CopyrightEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.PdfHorizontalLineContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.PdfBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IPdfPageEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.pdf.rendering.page.PdfPageConfiguration;
import net.sf.anathema.lib.resources.IResources;

public class PdfSecondPageEncoder implements IPdfPageEncoder {
  public static final int CONTENT_HEIGHT = 755;
  private final BaseFont baseFont;

  private final PdfPageConfiguration pageConfiguration;
  private final PdfBoxEncoder boxEncoder;
  private final ExtendedEncodingRegistry registry;
  private final IExtendedPartEncoder partEncoder;

  public PdfSecondPageEncoder(IExtendedPartEncoder partEncoder, ExtendedEncodingRegistry registry, IResources resources, int essenceMax,
    PdfPageConfiguration pageConfiguration) {
    this.partEncoder = partEncoder;
    this.baseFont = registry.getBaseFont();
    this.registry = registry;
    this.pageConfiguration = pageConfiguration;
    this.boxEncoder = new PdfBoxEncoder(resources, baseFont);
  }

  public void encode(Document document, PdfContentByte directContent, ReportContent content) throws

    DocumentException {
    int distanceFromTop = 0;

    /*   encodeFirstColumn(directContent, character, distanceFromTop);
  encodeAnima(directContent, character, distanceFromTop, ANIMA_HEIGHT);
  float virtueHeight = encodeVirtues(directContent, distanceFromTop, 72, character);
  distanceFromTop += calculateBoxIncrement(virtueHeight);
  float greatCurseHeigth = ANIMA_HEIGHT - virtueHeight - IVoidStateFormatConstants.PADDING;
  encodeGreatCurse(directContent, character, distanceFromTop, greatCurseHeigth);
  distanceFromTop += calculateBoxIncrement(greatCurseHeigth);*/

    float healthHeight = encodeMovementAndHealth(directContent, content, distanceFromTop, 99);
    distanceFromTop += calculateBoxIncrement(healthHeight);
    float socialCombatHeight = encodeSocialCombatStats(directContent, content, distanceFromTop, 125);
    encodeCombatStats(directContent, content, distanceFromTop, socialCombatHeight);

    /*    float willpowerHeight = encodeWillpower(directContent, character, distanceFromTop, 43);
float willpowerIncrement = calculateBoxIncrement(willpowerHeight);
distanceFromTop += willpowerIncrement;
float intimaciesHeight = encodeIntimacies(directContent, character, distanceFromTop, socialCombatHeight
   - willpowerIncrement);*/
    distanceFromTop += calculateBoxIncrement(socialCombatHeight);

    encodeInventory(directContent, content, distanceFromTop, CONTENT_HEIGHT - distanceFromTop);

    float weaponryHeight = encodeWeaponry(directContent, content, distanceFromTop, 120);
    distanceFromTop += calculateBoxIncrement(weaponryHeight);
    float armourHeight = encodeArmourAndSoak(directContent, content, distanceFromTop, 80);
    distanceFromTop += calculateBoxIncrement(armourHeight);
    encodeMassCombat(directContent, content, distanceFromTop, 120);

    new CopyrightEncoder(baseFont, pageConfiguration, CONTENT_HEIGHT).encodeCopyright(directContent);
  }

  private float calculateBoxIncrement(float height) {
    return height + IVoidStateFormatConstants.PADDING;
  }

  private void encodeInventory(PdfContentByte directContent, ReportContent content, float distanceFromTop, int height) throws DocumentException {
    Bounds bounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, height, 1);
    IBoxContentEncoder encoder = registry.getPossessionsEncoder();
    boxEncoder.encodeBox(content, directContent, encoder, bounds);
  }

  private float encodeMassCombat(PdfContentByte directContent, ReportContent content, float distanceFromTop, int height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    IBoxContentEncoder encoder = new PdfHorizontalLineContentEncoder(1, "MassCombat");
    boxEncoder.encodeBox(content, directContent, encoder, bounds);
    return height;
  }

  private float encodeArmourAndSoak(PdfContentByte directContent, ReportContent content, float distanceFromTop,
    float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    IBoxContentEncoder contentEncoder = registry.getArmourContentEncoder();
    boxEncoder.encodeBox(content, directContent, contentEncoder, bounds);
    return height;
  }

  private float encodeSocialCombatStats(PdfContentByte directContent, ReportContent content, float distanceFromTop,
    float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, height, 1);
    IBoxContentEncoder encoder = partEncoder.getSocialCombatEncoder();
    boxEncoder.encodeBox(content, directContent, encoder, bounds);
    return height;
  }

  private float encodeCombatStats(PdfContentByte directContent, ReportContent content, float distanceFromTop,
    float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    IBoxContentEncoder encoder = partEncoder.getCombatStatsEncoder();
    boxEncoder.encodeBox(content, directContent, encoder, bounds);
    return height;
  }

  private float encodeMovementAndHealth(PdfContentByte directContent, ReportContent content, float distanceFromTop,
    float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, height, 3);
    IBoxContentEncoder encoder = partEncoder.getHealthAndMovementEncoder();
    boxEncoder.encodeBox(content, directContent, encoder, bounds);
    return height;
  }

  private float encodeWeaponry(PdfContentByte directContent, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    IBoxContentEncoder weaponryEncoder = registry.getWeaponContentEncoder();
    boxEncoder.encodeBox(content, directContent, weaponryEncoder, bounds);
    return height;
  }
}
