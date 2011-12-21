package net.sf.anathema.character.reporting.pdf.layout.extended;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.CopyrightEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.HorizontalLineBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
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

  public void encode(Document document, SheetGraphics graphics, ReportContent content) throws
    DocumentException {
    int distanceFromTop = 0;

    float healthHeight = encodeMovementAndHealth(graphics, content, distanceFromTop, 99);
    distanceFromTop += calculateBoxIncrement(healthHeight);
    float socialCombatHeight = encodeSocialCombatStats(graphics, content, distanceFromTop, 125);
    encodeCombatStats(graphics, content, distanceFromTop, socialCombatHeight);
    distanceFromTop += calculateBoxIncrement(socialCombatHeight);
    encodeInventory(graphics, content, distanceFromTop, CONTENT_HEIGHT - distanceFromTop);

    float weaponryHeight = encodeWeaponry(graphics, content, distanceFromTop, 120);
    distanceFromTop += calculateBoxIncrement(weaponryHeight);
    float armourHeight = encodeArmourAndSoak(graphics, content, distanceFromTop, 80);
    distanceFromTop += calculateBoxIncrement(armourHeight);
    encodeMassCombat(graphics, content, distanceFromTop, 120);

    new CopyrightEncoder(pageConfiguration, CONTENT_HEIGHT).encodeCopyright(graphics);
  }

  private float calculateBoxIncrement(float height) {
    return height + IVoidStateFormatConstants.PADDING;
  }

  private void encodeInventory(SheetGraphics graphics, ReportContent content, float distanceFromTop, int height) throws DocumentException {
    Bounds bounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, height, 1);
    IBoxContentEncoder encoder = registry.getPossessionsEncoder();
    boxEncoder.encodeBox(content, graphics, encoder, bounds);
  }

  private float encodeMassCombat(SheetGraphics graphics, ReportContent content, float distanceFromTop, int height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    IBoxContentEncoder encoder = new HorizontalLineBoxContentEncoder(1, "MassCombat");
    boxEncoder.encodeBox(content, graphics, encoder, bounds);
    return height;
  }

  private float encodeArmourAndSoak(SheetGraphics graphics, ReportContent content, float distanceFromTop,
    float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    IBoxContentEncoder contentEncoder = registry.getArmourContentEncoder();
    boxEncoder.encodeBox(content, graphics, contentEncoder, bounds);
    return height;
  }

  private float encodeSocialCombatStats(SheetGraphics graphics, ReportContent content, float distanceFromTop,
    float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, height, 1);
    IBoxContentEncoder encoder = partEncoder.getSocialCombatEncoder();
    boxEncoder.encodeBox(content, graphics, encoder, bounds);
    return height;
  }

  private float encodeCombatStats(SheetGraphics graphics, ReportContent content, float distanceFromTop,
    float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    IBoxContentEncoder encoder = partEncoder.getCombatStatsEncoder();
    boxEncoder.encodeBox(content, graphics, encoder, bounds);
    return height;
  }

  private float encodeMovementAndHealth(SheetGraphics graphics, ReportContent content, float distanceFromTop,
    float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, height, 3);
    IBoxContentEncoder encoder = partEncoder.getHealthAndMovementEncoder();
    boxEncoder.encodeBox(content, graphics, encoder, bounds);
    return height;
  }

  private float encodeWeaponry(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    IBoxContentEncoder weaponryEncoder = registry.getWeaponContentEncoder();
    boxEncoder.encodeBox(content, graphics, weaponryEncoder, bounds);
    return height;
  }
}
