package net.sf.anathema.character.lunar.reporting.layout;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.equipment.impl.reporting.rendering.panoply.ArmourEncoder;
import net.sf.anathema.character.lunar.reporting.rendering.GiftEncoder;
import net.sf.anathema.character.lunar.reporting.rendering.LunarFaceEncoder;
import net.sf.anathema.character.lunar.reporting.rendering.beastform.BeastformAttributeBoundsEncoder;
import net.sf.anathema.character.lunar.reporting.rendering.beastform.FirstEditionDBTCombatEncoder;
import net.sf.anathema.character.lunar.reporting.rendering.beastform.FirstEditionLunarBeastformAttributesEncoder;
import net.sf.anathema.character.lunar.reporting.rendering.equipment.LunarEquipmentEncoders;
import net.sf.anathema.character.lunar.reporting.rendering.health.FirstEditionLunarHealthAndMovementEncoder;
import net.sf.anathema.character.lunar.reporting.rendering.heartsblood.FirstEditionLunarHeartsBloodEncoder;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.layout.simple.ISimplePartEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.EncoderIds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.BoxContentEncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.abilities.AbilitiesBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.personal.PersonalInfoBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.virtues.VirtueBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.willpower.SimpleWillpowerEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.PdfBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.PdfHeaderBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.ITableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.page.IPdfPageEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.PdfPageConfiguration;
import net.sf.anathema.lib.resources.IResources;

import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.ARSENAL;
import static net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderAttributeType.PreferredHeight;
import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.PADDING;

public class Simple1stEditionLunarBeastformPageEncoder implements IPdfPageEncoder {
  private final IResources resources;

  private static final int ANIMA_HEIGHT = 128;
  private static final int VIRTUE_HEIGHT = 72;
  private final PdfPageConfiguration pageConfiguration;
  private final PdfBoxEncoder boxEncoder;
  private BoxContentEncoderRegistry encoderRegistry;
  private final ISimplePartEncoder partEncoder;

  public Simple1stEditionLunarBeastformPageEncoder(BoxContentEncoderRegistry encoderRegistry, ISimplePartEncoder partEncoder, IResources resources,
    PdfPageConfiguration pageConfiguration) {
    this.encoderRegistry = encoderRegistry;
    this.partEncoder = partEncoder;
    this.resources = resources;
    this.pageConfiguration = pageConfiguration;
    this.boxEncoder = new PdfBoxEncoder(resources);
  }

  public void encode(Document document, SheetGraphics graphics, ReportContent content) throws DocumentException {
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
    float weaponryHeight = encodeWeaponry(graphics, content, distanceFromTop);
    distanceFromTop += calculateBoxIncrement(weaponryHeight);
    float armourHeight = encodeArmourAndSoak(graphics, content, distanceFromTop, 80);
    distanceFromTop += calculateBoxIncrement(armourHeight);
    float healthHeight = encodeMovementAndHealth(graphics, content, distanceFromTop, 99);
    distanceFromTop += calculateBoxIncrement(healthHeight);
    float remainingHeight = Simple1stEditionLunarBeastformPageEncoder.CONTENT_HEIGHT - distanceFromTop;
    encodeCombatStats(graphics, content, distanceFromTop, remainingHeight);
    encodeAbilities(graphics, content, abilityStartHeight, remainingHeight + PADDING);
    encodeGifts(graphics, content, distanceFromTop, remainingHeight);
  }

  private void encodeGifts(SheetGraphics graphics, ReportContent content, int distanceFromTop, float remainingHeight) throws DocumentException {
    Bounds bounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, remainingHeight, 1);
    ContentEncoder encoder = new GiftEncoder();
    boxEncoder.encodeBox(content, graphics, encoder, bounds);
  }

  private float getOverlapFreeSpaceHeight() {
    return ANIMA_HEIGHT - VIRTUE_HEIGHT - PADDING;
  }

  private float encodeEssence(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds essenceBounds = pageConfiguration.getThirdColumnRectangle(distanceFromTop, height);
    ContentEncoder encoder = encoderRegistry.createEncoder(resources, content, EncoderIds.ESSENCE_SIMPLE);
    boxEncoder.encodeBox(content, graphics, encoder, essenceBounds);
    return height;
  }

  private String getHeaderLabel(String key) {
    return resources.getString("Sheet.Header." + key); //$NON-NLS-1$
  }

  private void encodePersonalInfo(SheetGraphics graphics, ReportContent content, int distanceFromTop, final int firstRowHeight) {
    Bounds infoBounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, firstRowHeight, 2);
    String name = content.getDescription().getName();
    String title = StringUtilities.isNullOrTrimEmpty(name) ? getHeaderLabel("PersonalInfo") : name; //$NON-NLS-1$
    Bounds infoContentBounds = boxEncoder.encodeBox(graphics, infoBounds, title);
    encodePersonalInfo(graphics, content, infoContentBounds);
  }

  private void encodeAbilities(SheetGraphics graphics, ReportContent content, float distanceFromTop, float remainingHeightRequired)
    throws DocumentException {
    float abilitiesHeight = CONTENT_HEIGHT - distanceFromTop - remainingHeightRequired;
    Bounds boxBounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, abilitiesHeight, 1);
    ContentEncoder encoder = AbilitiesBoxContentEncoder.createWithSpecialtiesOnly(resources, 11);
    boxEncoder.encodeBox(content, graphics, encoder, boxBounds);
  }

  private int encodeAttributes(SheetGraphics graphics, ReportContent content, int distanceFromTop) throws DocumentException {
    int attributeHeight = 128;
    Bounds attributeBounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, attributeHeight, 2);
    float smallWidth = pageConfiguration.getColumnWidth();
    BeastformAttributeBoundsEncoder beastBoxEncoder = new BeastformAttributeBoundsEncoder(smallWidth, getOverlapFreeSpaceHeight());
    FirstEditionLunarBeastformAttributesEncoder encoder =
      new FirstEditionLunarBeastformAttributesEncoder(resources, boxEncoder.calculateInsettedWidth(smallWidth));
    new PdfHeaderBoxEncoder()
      .encodeHeaderBox(graphics, attributeBounds, resources.getString("Sheet.Header." + encoder.getHeaderKey(content))); //$NON-NLS-1$
    boxEncoder.encodeBox(graphics, encoder, beastBoxEncoder, content, attributeBounds);
    return attributeHeight;
  }

  private float calculateBoxIncrement(float height) {
    return height + PADDING;
  }

  private void encodeAnima(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds animaBounds = pageConfiguration.getThirdColumnRectangle(distanceFromTop, height);
    ContentEncoder encoder = partEncoder.getAnimaEncoder();
    boxEncoder.encodeBox(content, graphics, encoder, animaBounds);
  }

  private float encodeArmourAndSoak(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    ITableEncoder armourEncoder = LunarEquipmentEncoders.CreateArmourEncoder();
    ContentEncoder contentEncoder = new ArmourEncoder(armourEncoder);
    boxEncoder.encodeBox(content, graphics, contentEncoder, bounds);
    return height;
  }

  private float encodeAnimalForms(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getThirdColumnRectangle(distanceFromTop, height);
    ContentEncoder encoder = new FirstEditionLunarHeartsBloodEncoder(resources);
    boxEncoder.encodeBox(content, graphics, encoder, bounds);
    return height;
  }

  private float encodeCombatStats(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    FirstEditionDBTCombatEncoder encoder = new FirstEditionDBTCombatEncoder(resources);
    boxEncoder.encodeBox(content, graphics, encoder, bounds);
    return height;
  }

  private float encodeMovementAndHealth(SheetGraphics graphics, ReportContent content, float distanceFromTop,
    float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    ContentEncoder encoder = new FirstEditionLunarHealthAndMovementEncoder(resources);
    boxEncoder.encodeBox(content, graphics, encoder, bounds);
    return height;
  }

  private void encodePersonalInfo(SheetGraphics graphics, ReportContent content, Bounds infoBounds) {
    PersonalInfoBoxEncoder encoder = new PersonalInfoBoxEncoder(resources);
    encoder.encode(graphics, content, infoBounds);
  }

  private float encodeVirtues(SheetGraphics graphics, float distanceFromTop, float height, ReportContent content) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 1);
    ContentEncoder encoder = new VirtueBoxContentEncoder();
    boxEncoder.encodeBox(content, graphics, encoder, bounds);
    return height;
  }

  private float encodeWeaponry(SheetGraphics graphics, ReportContent content, float distanceFromTop) throws DocumentException {
    float height = encoderRegistry.getValue(PreferredHeight, content, ARSENAL);
    ContentEncoder weaponryEncoder = encoderRegistry.createEncoder(resources, content, ARSENAL);
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    boxEncoder.encodeBox(content, graphics, weaponryEncoder, bounds);
    return height;
  }

  private float encodeWillpower(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds willpowerBounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 1);
    boxEncoder.encodeBox(content, graphics, new SimpleWillpowerEncoder(), willpowerBounds);
    return height;
  }

  private float encodeFace(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 1);
    ContentEncoder encoder = new LunarFaceEncoder(resources);
    boxEncoder.encodeBox(content, graphics, encoder, bounds);
    return height;
  }
}
