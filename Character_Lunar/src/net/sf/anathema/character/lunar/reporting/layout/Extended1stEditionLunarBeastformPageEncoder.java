package net.sf.anathema.character.lunar.reporting.layout;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.equipment.impl.reporting.ArmourEncoder;
import net.sf.anathema.character.equipment.impl.reporting.WeaponryEncoder;
import net.sf.anathema.character.lunar.reporting.rendering.GiftEncoder;
import net.sf.anathema.character.lunar.reporting.rendering.LunarFaceEncoder;
import net.sf.anathema.character.lunar.reporting.rendering.beastform.BeastformAttributeBoxEncoder;
import net.sf.anathema.character.lunar.reporting.rendering.beastform.FirstEditionDBTCombatEncoder;
import net.sf.anathema.character.lunar.reporting.rendering.beastform.FirstEditionLunarBeastformAttributesEncoder;
import net.sf.anathema.character.lunar.reporting.rendering.equipment.LunarArmourTableEncoder;
import net.sf.anathema.character.lunar.reporting.rendering.equipment.LunarWeaponTableEncoder;
import net.sf.anathema.character.lunar.reporting.rendering.health.FirstEditionLunarHealthAndMovementEncoder;
import net.sf.anathema.character.lunar.reporting.rendering.heartsblood.FirstEditionLunarHeartsBloodEncoder;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.layout.extended.ExtendedEncodingRegistry;
import net.sf.anathema.character.reporting.pdf.layout.extended.IExtendedPartEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.abilities.AbilitiesBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.personal.ExtendedPersonalInfoEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.virtues.VirtueBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.willpower.SimpleWillpowerBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.PdfBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.PdfHeaderBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.ITableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IPdfPageEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.pdf.rendering.page.PdfPageConfiguration;
import net.sf.anathema.lib.resources.IResources;

import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.PADDING;

public class Extended1stEditionLunarBeastformPageEncoder implements IPdfPageEncoder {
  public static final int CONTENT_HEIGHT = 755;
  private final IResources resources;
  private final int essenceMax;
  private final BaseFont baseFont;
  private final BaseFont symbolFont;

  private static final int ANIMA_HEIGHT = 128;
  private static final int VIRTUE_HEIGHT = 72;
  private final PdfPageConfiguration pageConfiguration;
  private final PdfBoxEncoder boxEncoder;
  private final IExtendedPartEncoder partEncoder;

  public Extended1stEditionLunarBeastformPageEncoder(IExtendedPartEncoder partEncoder, ExtendedEncodingRegistry registry, IResources resources,
    int essenceMax, PdfPageConfiguration pageConfiguration) {
    this.partEncoder = partEncoder;
    this.baseFont = registry.getBaseFont();
    this.essenceMax = essenceMax;
    this.resources = resources;
    this.pageConfiguration = pageConfiguration;
    this.boxEncoder = new PdfBoxEncoder(resources, baseFont);
    this.symbolFont = registry.getSymbolBaseFont();
  }

  public void encode(Document document, SheetGraphics graphics, ReportContent content) throws
    DocumentException {
    int distanceFromTop = 0;
    final int firstRowHeight = 51;
    encodePersonalInfo(graphics, content, distanceFromTop, firstRowHeight);
    encodeEssence(graphics, content, distanceFromTop, firstRowHeight);

    distanceFromTop += firstRowHeight + PADDING;

    float abilityStartHeight = encodeAttributes(graphics, content, distanceFromTop) + distanceFromTop + PADDING;
    encodeAnima(graphics, content, distanceFromTop, ANIMA_HEIGHT);
    float freeSpace = getOverlapFreeSpaceHeight();
    distanceFromTop += freeSpace;
    distanceFromTop += PADDING;
    encodeVirtues(graphics, distanceFromTop, VIRTUE_HEIGHT, content);
    distanceFromTop += calculateBoxIncrement(VIRTUE_HEIGHT);

    float animalFormHeight = encodeAnimalForms(graphics, content, distanceFromTop, 115);
    float willpowerHeight = encodeWillpower(graphics, content, distanceFromTop, 43);
    float willpowerIncrement = calculateBoxIncrement(willpowerHeight);
    distanceFromTop += willpowerIncrement;
    float faceHeight = encodeFace(graphics, content, distanceFromTop, animalFormHeight - willpowerIncrement);
    distanceFromTop += calculateBoxIncrement(faceHeight);
    float weaponryHeight = encodeWeaponry(graphics, content, distanceFromTop, partEncoder.getWeaponryHeight());
    distanceFromTop += calculateBoxIncrement(weaponryHeight);
    float armourHeight = encodeArmourAndSoak(graphics, content, distanceFromTop, 80);
    distanceFromTop += calculateBoxIncrement(armourHeight);
    float healthHeight = encodeMovementAndHealth(graphics, content, distanceFromTop, 99);
    distanceFromTop += calculateBoxIncrement(healthHeight);
    float remainingHeight = Extended1stEditionLunarBeastformPageEncoder.CONTENT_HEIGHT - distanceFromTop;
    encodeCombatStats(graphics, content, distanceFromTop, remainingHeight);
    encodeAbilities(graphics, content, abilityStartHeight, remainingHeight + PADDING);
    encodeGifts(graphics, content, distanceFromTop, remainingHeight);
  }

  private void encodeGifts(SheetGraphics graphics, ReportContent content, int distanceFromTop,
    float remainingHeight) throws DocumentException {
    Bounds bounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, remainingHeight, 1);
    IBoxContentEncoder encoder = new GiftEncoder(baseFont, resources);
    boxEncoder.encodeBox(content, graphics, encoder, bounds);
  }

  private float getOverlapFreeSpaceHeight() {
    return ANIMA_HEIGHT - VIRTUE_HEIGHT - PADDING;
  }

  private float encodeEssence(SheetGraphics graphics, ReportContent content, float distanceFromTop,
    float height) throws DocumentException {
    Bounds essenceBounds = pageConfiguration.getThirdColumnRectangle(distanceFromTop, height);
    IBoxContentEncoder encoder = partEncoder.getEssenceEncoder();
    boxEncoder.encodeBox(content, graphics, encoder, essenceBounds);
    return height;
  }

  private String getHeaderLabel(String key) {
    return resources.getString("Sheet.Header." + key); //$NON-NLS-1$
  }

  private void encodePersonalInfo(SheetGraphics graphics, ReportContent content, int distanceFromTop,
    final int firstRowHeight) {
    Bounds infoBounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, firstRowHeight, 2);
    String name = content.getDescription().getName();
    String title = StringUtilities.isNullOrTrimEmpty(name) ? getHeaderLabel("PersonalInfo") : name; //$NON-NLS-1$
    Bounds infoContentBounds = boxEncoder.encodeBox(graphics, infoBounds, title);
    encodePersonalInfos(graphics, content, infoContentBounds);
  }

  private void encodeAbilities(SheetGraphics graphics, ReportContent content, float distanceFromTop,
    float remainingHeightRequired) throws DocumentException {
    float abilitiesHeight = CONTENT_HEIGHT - distanceFromTop - remainingHeightRequired;
    Bounds boxBounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, abilitiesHeight, 1);
    IBoxContentEncoder encoder = AbilitiesBoxContentEncoder.createWithSpecialtiesOnly(baseFont, resources, essenceMax, 11);
    boxEncoder.encodeBox(content, graphics, encoder, boxBounds);
  }

  private int encodeAttributes(SheetGraphics graphics, ReportContent content,
    int distanceFromTop) throws DocumentException {
    int attributeHeight = 128;
    Bounds attributeBounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, attributeHeight, 2);
    float smallWidth = pageConfiguration.getColumnWidth();
    BeastformAttributeBoxEncoder beastBoxEncoder = new BeastformAttributeBoxEncoder(baseFont, smallWidth, getOverlapFreeSpaceHeight());
    FirstEditionLunarBeastformAttributesEncoder encoder = new FirstEditionLunarBeastformAttributesEncoder(baseFont, resources,
      boxEncoder.calculateInsettedWidth(smallWidth));
    new PdfHeaderBoxEncoder(baseFont).encodeHeaderBox(graphics, attributeBounds, resources.getString("Sheet.Header." + encoder.getHeaderKey
      (content))); //$NON-NLS-1$
    boxEncoder.encodeBox(graphics, encoder, beastBoxEncoder, content, attributeBounds);
    return attributeHeight;
  }

  private float calculateBoxIncrement(float height) {
    return height + IVoidStateFormatConstants.PADDING;
  }

  private void encodeAnima(SheetGraphics graphics, ReportContent content, float distanceFromTop,
    float height) throws DocumentException {
    Bounds animaBounds = pageConfiguration.getThirdColumnRectangle(distanceFromTop, height);
    IBoxContentEncoder encoder = partEncoder.getAnimaEncoder();
    boxEncoder.encodeBox(content, graphics, encoder, animaBounds);
  }

  private float encodeArmourAndSoak(SheetGraphics graphics, ReportContent content, float distanceFromTop,
    float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    ITableEncoder armourEncoder = new LunarArmourTableEncoder(baseFont, resources);
    IBoxContentEncoder contentEncoder = new ArmourEncoder(resources, baseFont, armourEncoder);
    boxEncoder.encodeBox(content, graphics, contentEncoder, bounds);
    return height;
  }

  private float encodeAnimalForms(SheetGraphics graphics, ReportContent content, float distanceFromTop,
    float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getThirdColumnRectangle(distanceFromTop, height);
    IBoxContentEncoder encoder = new FirstEditionLunarHeartsBloodEncoder(baseFont, resources);
    boxEncoder.encodeBox(content, graphics, encoder, bounds);
    return height;
  }

  private float encodeCombatStats(SheetGraphics graphics, ReportContent content, float distanceFromTop,
    float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    FirstEditionDBTCombatEncoder encoder = new FirstEditionDBTCombatEncoder(resources, baseFont);
    boxEncoder.encodeBox(content, graphics, encoder, bounds);
    return height;
  }

  private float encodeMovementAndHealth(SheetGraphics graphics, ReportContent content, float distanceFromTop,
    float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    IBoxContentEncoder encoder = new FirstEditionLunarHealthAndMovementEncoder(resources, baseFont, symbolFont, content.getCharacter());
    boxEncoder.encodeBox(content, graphics, encoder, bounds);
    return height;
  }

  private void encodePersonalInfos(SheetGraphics graphics, ReportContent content, Bounds infoBounds) {
    ExtendedPersonalInfoEncoder encoder = new ExtendedPersonalInfoEncoder(baseFont, resources);
    encoder.encodePersonalInfos(graphics, content, infoBounds);
  }

  private float encodeVirtues(SheetGraphics graphics, float distanceFromTop, float height, ReportContent content) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 1);
    IBoxContentEncoder encoder = new VirtueBoxContentEncoder();
    boxEncoder.encodeBox(content, graphics, encoder, bounds);
    return height;
  }

  private float encodeWeaponry(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    IBoxContentEncoder weaponryEncoder = new WeaponryEncoder(resources, baseFont, LunarWeaponTableEncoder.Create(baseFont));
    boxEncoder.encodeBox(content, graphics, weaponryEncoder, bounds);
    return height;
  }

  private float encodeWillpower(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds willpowerBounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 1);
    boxEncoder.encodeBox(content, graphics, new SimpleWillpowerBoxContentEncoder(), willpowerBounds);
    return height;
  }

  private float encodeFace(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 1);
    IBoxContentEncoder encoder = new LunarFaceEncoder(baseFont, resources);
    boxEncoder.encodeBox(content, graphics, encoder, bounds);
    return height;
  }
}
