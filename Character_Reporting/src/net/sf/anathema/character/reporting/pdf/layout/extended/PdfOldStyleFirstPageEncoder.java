package net.sf.anathema.character.reporting.pdf.layout.extended;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.abilities.AbilitiesBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.attributes.PdfAttributesEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.personal.ExtendedPersonalInfoEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.virtues.VirtueBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.willpower.PdfWillpowerEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.CopyrightEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.PdfBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IPdfPageEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.pdf.rendering.page.PdfPageConfiguration;
import net.sf.anathema.lib.resources.IResources;

import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.PADDING;

public class PdfOldStyleFirstPageEncoder implements IPdfPageEncoder {
  public static final int CONTENT_HEIGHT = 755;
  private final IResources resources;
  private final int essenceMax;
  private final BaseFont baseFont;

  private static final int ANIMA_HEIGHT = 128;
  private final PdfPageConfiguration pageConfiguration;
  private final PdfBoxEncoder boxEncoder;
  private final ExtendedEncodingRegistry registry;
  private final IExtendedPartEncoder partEncoder;

  public PdfOldStyleFirstPageEncoder(IExtendedPartEncoder partEncoder, ExtendedEncodingRegistry registry, IResources resources, int essenceMax,
    PdfPageConfiguration pageConfiguration) {
    this.partEncoder = partEncoder;
    this.baseFont = registry.getBaseFont();
    this.essenceMax = essenceMax;
    this.resources = resources;
    this.registry = registry;
    this.pageConfiguration = pageConfiguration;
    this.boxEncoder = new PdfBoxEncoder(resources, baseFont);
  }

  public void encode(Document document, PdfContentByte directContent, ReportContent content) throws

    DocumentException {
    int distanceFromTop = 0;
    final int firstRowHeight = 51;
    encodePersonalInfo(directContent, content, distanceFromTop, firstRowHeight);
    encodeEssence(directContent, content, distanceFromTop, firstRowHeight);

    distanceFromTop += firstRowHeight + PADDING;

    encodeFirstColumn(directContent, content, distanceFromTop);
    encodeAnima(directContent, content, distanceFromTop, ANIMA_HEIGHT);
    float virtueHeight = encodeVirtues(directContent, content, distanceFromTop, 72);
    distanceFromTop += calculateBoxIncrement(virtueHeight);
    float greatCurseHeigth = ANIMA_HEIGHT - virtueHeight - IVoidStateFormatConstants.PADDING;
    encodeGreatCurse(directContent, content, distanceFromTop, greatCurseHeigth);
    distanceFromTop += calculateBoxIncrement(greatCurseHeigth);

    float socialCombatHeight = encodeSocialCombatStats(directContent, content, distanceFromTop, 115);
    float willpowerHeight = encodeWillpower(directContent, content, distanceFromTop, 43);
    float willpowerIncrement = calculateBoxIncrement(willpowerHeight);
    distanceFromTop += willpowerIncrement;
    float intimaciesHeight = encodeIntimacies(directContent, content, distanceFromTop, socialCombatHeight - willpowerIncrement);
    distanceFromTop += calculateBoxIncrement(intimaciesHeight);
    float weaponryHeight = encodeWeaponry(directContent, content, distanceFromTop, partEncoder.getWeaponryHeight());
    distanceFromTop += calculateBoxIncrement(weaponryHeight);
    float armourHeight = encodeArmourAndSoak(directContent, content, distanceFromTop, 80);
    distanceFromTop += calculateBoxIncrement(armourHeight);
    float healthHeight = encodeMovementAndHealth(directContent, content, distanceFromTop, 99);
    distanceFromTop += calculateBoxIncrement(healthHeight);
    float remainingHeight = PdfOldStyleFirstPageEncoder.CONTENT_HEIGHT - distanceFromTop;
    encodeCombatStats(directContent, content, distanceFromTop, remainingHeight);
    new CopyrightEncoder(baseFont, pageConfiguration, CONTENT_HEIGHT).encodeCopyright(directContent);
  }

  private float encodeEssence(PdfContentByte directContent, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds essenceBounds = pageConfiguration.getThirdColumnRectangle(distanceFromTop, height);
    IBoxContentEncoder encoder = partEncoder.getEssenceEncoder();
    boxEncoder.encodeBox(content, directContent, encoder, essenceBounds);
    return height;
  }

  private String getHeaderLabel(String key) {
    return resources.getString("Sheet.Header." + key); //$NON-NLS-1$
  }

  private void encodePersonalInfo(PdfContentByte directContent, ReportContent content, int distanceFromTop, final int firstRowHeight) {
    Bounds infoBounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, firstRowHeight, 2);
    String name = content.getDescription().getName();
    String title = StringUtilities.isNullOrTrimEmpty(name) ? getHeaderLabel("PersonalInfo") : name; //$NON-NLS-1$
    Bounds infoContentBounds = boxEncoder.encodeBox(directContent, infoBounds, title);
    encodePersonalInfos(directContent, content, infoContentBounds);
  }

  private void encodeFirstColumn(PdfContentByte directContent, ReportContent content, float distanceFromTop) throws DocumentException {
    float attributeHeight = encodeAttributes(directContent, content, distanceFromTop);
    encodeAbilities(directContent, content, distanceFromTop + attributeHeight + PADDING);
  }

  private void encodeAbilities(PdfContentByte directContent, ReportContent content, float distanceFromTop) throws DocumentException {
    float abilitiesHeight = CONTENT_HEIGHT - distanceFromTop;
    Bounds boxBounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, abilitiesHeight, 1);
    IBoxContentEncoder encoder = AbilitiesBoxContentEncoder.createWithCraftsAndSpecialties(baseFont, resources, essenceMax, 9, 9);
    boxEncoder.encodeBox(content, directContent, encoder, boxBounds);
  }

  private float encodeAttributes(PdfContentByte directContent, ReportContent content, float distanceFromTop) throws DocumentException {
    float attributeHeight = 128;
    Bounds attributeBounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, attributeHeight, 1);
    IBoxContentEncoder encoder = new PdfAttributesEncoder(baseFont, resources, essenceMax, partEncoder.isEncodeAttributeAsFavorable());
    boxEncoder.encodeBox(content, directContent, encoder, attributeBounds);
    return attributeHeight;
  }

  private float calculateBoxIncrement(float height) {
    return height + IVoidStateFormatConstants.PADDING;
  }

  private void encodeAnima(PdfContentByte directContent, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds animaBounds = pageConfiguration.getThirdColumnRectangle(distanceFromTop, height);
    IBoxContentEncoder encoder = partEncoder.getAnimaEncoder();
    boxEncoder.encodeBox(content, directContent, encoder, animaBounds);
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
    Bounds bounds = pageConfiguration.getThirdColumnRectangle(distanceFromTop, height);
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
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    IBoxContentEncoder encoder = partEncoder.getHealthAndMovementEncoder();
    boxEncoder.encodeBox(content, directContent, encoder, bounds);
    return height;
  }

  private void encodePersonalInfos(PdfContentByte directContent, ReportContent content, Bounds infoBounds) {
    ExtendedPersonalInfoEncoder encoder = new ExtendedPersonalInfoEncoder(baseFont, resources);
    encoder.encodePersonalInfos(directContent, content, infoBounds);
  }

  private float encodeVirtues(PdfContentByte directContent, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 1);
    IBoxContentEncoder encoder = new VirtueBoxContentEncoder();
    boxEncoder.encodeBox(content, directContent, encoder, bounds);
    return height;
  }

  private float encodeWeaponry(PdfContentByte directContent, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    IBoxContentEncoder weaponryEncoder = registry.getWeaponContentEncoder();
    boxEncoder.encodeBox(content, directContent, weaponryEncoder, bounds);
    return height;
  }

  private float encodeWillpower(PdfContentByte directContent, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds willpowerBounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 1);
    boxEncoder.encodeBox(content, directContent, new PdfWillpowerEncoder(baseFont), willpowerBounds);
    return height;
  }

  private float encodeGreatCurse(PdfContentByte directContent, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 1);
    IBoxContentEncoder encoder = partEncoder.getGreatCurseEncoder();
    boxEncoder.encodeBox(content, directContent, encoder, bounds);
    return height;
  }

  private float encodeIntimacies(PdfContentByte directContent, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 1);
    IBoxContentEncoder encoder = partEncoder.getIntimaciesEncoder(registry);
    boxEncoder.encodeBox(content, directContent, encoder, bounds);
    return height;
  }
}
