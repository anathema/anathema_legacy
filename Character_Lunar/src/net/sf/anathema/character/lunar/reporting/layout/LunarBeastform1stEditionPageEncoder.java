package net.sf.anathema.character.lunar.reporting.layout;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.equipment.impl.reporting.rendering.panoply.ArmourEncoder;
import net.sf.anathema.character.equipment.impl.reporting.rendering.panoply.ArmourTableEncoder;
import net.sf.anathema.character.lunar.reporting.content.equipment.LunarArmourContent;
import net.sf.anathema.character.lunar.reporting.rendering.GiftEncoder;
import net.sf.anathema.character.lunar.reporting.rendering.LunarFaceEncoder;
import net.sf.anathema.character.lunar.reporting.rendering.beastform.AttributeBoundsEncoder;
import net.sf.anathema.character.lunar.reporting.rendering.beastform.FirstEditionDBTCombatEncoder;
import net.sf.anathema.character.lunar.reporting.rendering.beastform.FirstEditionLunarBeastformAttributesEncoder;
import net.sf.anathema.character.lunar.reporting.rendering.health.FirstEditionLunarHealthAndMovementEncoder;
import net.sf.anathema.character.lunar.reporting.rendering.heartsblood.FirstEditionLunarHeartsBloodEncoder;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.EncoderIds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncodingMetrics;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.abilities.AbilitiesEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.personal.PersonalInfoEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.virtues.VirtueEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.willpower.SimpleWillpowerEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.PdfBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.PdfHeaderBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.ITableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageConfiguration;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageEncoder;
import net.sf.anathema.lib.resources.IResources;

import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.ARSENAL;
import static net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderAttributeType.PreferredHeight;
import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.PADDING;

public class LunarBeastform1stEditionPageEncoder implements PageEncoder {
  private final IResources resources;

  private static final int ANIMA_HEIGHT = 128;
  private static final int VIRTUE_HEIGHT = 72;
  private final PageConfiguration configuration;
  private final PdfBoxEncoder boxEncoder;
  private EncoderRegistry encoders;

  public LunarBeastform1stEditionPageEncoder(EncoderRegistry encoders, IResources resources, PageConfiguration configuration) {
    this.encoders = encoders;
    this.resources = resources;
    this.configuration = configuration;
    this.boxEncoder = new PdfBoxEncoder();
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
    float remainingHeight = PageEncoder.FIXED_CONTENT_HEIGHT - distanceFromTop;
    encodeCombatStats(graphics, content, distanceFromTop, remainingHeight);
    encodeAbilities(graphics, content, abilityStartHeight, remainingHeight + PADDING);
    encodeGifts(graphics, content, distanceFromTop, remainingHeight);
  }

  private void encodeGifts(SheetGraphics graphics, ReportContent content, int distanceFromTop, float remainingHeight) throws DocumentException {
    Bounds bounds = configuration.getFirstColumnRectangle(distanceFromTop, remainingHeight, 1);
    ContentEncoder encoder = new GiftEncoder();
    boxEncoder.encodeBox(content, graphics, encoder, bounds);
  }

  private float getOverlapFreeSpaceHeight() {
    return ANIMA_HEIGHT - VIRTUE_HEIGHT - PADDING;
  }

  private float encodeEssence(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = configuration.getThirdColumnRectangle(distanceFromTop, height);
    ContentEncoder encoder = encoders.createEncoder(resources, content, EncoderIds.ESSENCE_SIMPLE);
    boxEncoder.encodeBox(content, graphics, encoder, bounds);
    return bounds.getHeight();
  }

  private String getHeaderLabel(String key) {
    return resources.getString("Sheet.Header." + key); //$NON-NLS-1$
  }

  private void encodePersonalInfo(SheetGraphics graphics, ReportContent content, int distanceFromTop, final int firstRowHeight) {
    Bounds infoBounds = configuration.getFirstColumnRectangle(distanceFromTop, firstRowHeight, 2);
    String name = content.getDescription().getName();
    String title = StringUtilities.isNullOrTrimmedEmpty(name) ? getHeaderLabel("PersonalInfo") : name; //$NON-NLS-1$
    Bounds infoContentBounds = boxEncoder.encodeBox(graphics, infoBounds, title);
    PersonalInfoEncoder encoder = new PersonalInfoEncoder(resources);
    encoder.encode(graphics, content, infoContentBounds);
  }

  private void encodeAbilities(SheetGraphics graphics, ReportContent content, float distanceFromTop, float remainingHeightRequired) throws DocumentException {
    float abilitiesHeight = FIXED_CONTENT_HEIGHT - distanceFromTop - remainingHeightRequired;
    Bounds boxBounds = configuration.getFirstColumnRectangle(distanceFromTop, abilitiesHeight, 1);
    ContentEncoder encoder = AbilitiesEncoder.createWithSpecialtiesOnly(resources, 11);
    boxEncoder.encodeBox(content, graphics, encoder, boxBounds);
  }

  private int encodeAttributes(SheetGraphics graphics, ReportContent content, int distanceFromTop) throws DocumentException {
    int attributeHeight = 128;
    Bounds attributeBounds = configuration.getFirstColumnRectangle(distanceFromTop, attributeHeight, 2);
    float smallWidth = configuration.getColumnWidth();
    AttributeBoundsEncoder beastBoxEncoder = new AttributeBoundsEncoder(smallWidth, getOverlapFreeSpaceHeight());
    FirstEditionLunarBeastformAttributesEncoder encoder = new FirstEditionLunarBeastformAttributesEncoder(resources, boxEncoder.calculateWidthWithinInset(smallWidth));
    new PdfHeaderBoxEncoder().encodeHeaderBox(graphics, attributeBounds, encoder.getHeader(content)); //$NON-NLS-1$
    boxEncoder.encodeBox(graphics, encoder, beastBoxEncoder, content, attributeBounds);
    return attributeHeight;
  }

  private float calculateBoxIncrement(float height) {
    return height + IVoidStateFormatConstants.PADDING;
  }

  private void encodeAnima(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds animaBounds = configuration.getThirdColumnRectangle(distanceFromTop, height);
    ContentEncoder encoder = encoders.createEncoder(resources, content, EncoderIds.ANIMA);
    boxEncoder.encodeBox(content, graphics, encoder, animaBounds);
  }

  private float encodeArmourAndSoak(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = configuration.getSecondColumnRectangle(distanceFromTop, height, 2);
    ITableEncoder armourEncoder = new ArmourTableEncoder(LunarArmourContent.class);
    ContentEncoder contentEncoder = new ArmourEncoder(resources, armourEncoder);
    boxEncoder.encodeBox(content, graphics, contentEncoder, bounds);
    return bounds.getHeight();
  }

  private float encodeAnimalForms(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = configuration.getThirdColumnRectangle(distanceFromTop, height);
    ContentEncoder encoder = new FirstEditionLunarHeartsBloodEncoder(resources);
    boxEncoder.encodeBox(content, graphics, encoder, bounds);
    return bounds.getHeight();
  }

  private float encodeCombatStats(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = configuration.getSecondColumnRectangle(distanceFromTop, height, 2);
    FirstEditionDBTCombatEncoder encoder = new FirstEditionDBTCombatEncoder(resources);
    boxEncoder.encodeBox(content, graphics, encoder, bounds);
    return bounds.getHeight();
  }

  private float encodeMovementAndHealth(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = configuration.getSecondColumnRectangle(distanceFromTop, height, 2);
    ContentEncoder encoder = new FirstEditionLunarHealthAndMovementEncoder(resources);
    boxEncoder.encodeBox(content, graphics, encoder, bounds);
    return bounds.getHeight();
  }

  private float encodeVirtues(SheetGraphics graphics, float distanceFromTop, float height, ReportContent content) throws DocumentException {
    Bounds bounds = configuration.getSecondColumnRectangle(distanceFromTop, height, 1);
    ContentEncoder encoder = new VirtueEncoder();
    boxEncoder.encodeBox(content, graphics, encoder, bounds);
    return bounds.getHeight();
  }

  private float encodeWeaponry(SheetGraphics graphics, ReportContent content, float distanceFromTop) throws DocumentException {
    EncodingMetrics metrics = EncodingMetrics.From(graphics, content);
    ;
    float height = encoders.getValue(PreferredHeight, metrics, ARSENAL);
    ContentEncoder weaponryEncoder = encoders.createEncoder(resources, content, ARSENAL);
    Bounds bounds = configuration.getSecondColumnRectangle(distanceFromTop, height, 2);
    boxEncoder.encodeBox(content, graphics, weaponryEncoder, bounds);
    return bounds.getHeight();
  }

  private float encodeWillpower(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = configuration.getSecondColumnRectangle(distanceFromTop, height, 1);
    boxEncoder.encodeBox(content, graphics, new SimpleWillpowerEncoder(), bounds);
    return bounds.getHeight();
  }

  private float encodeFace(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = configuration.getSecondColumnRectangle(distanceFromTop, height, 1);
    ContentEncoder encoder = new LunarFaceEncoder(resources);
    boxEncoder.encodeBox(content, graphics, encoder, bounds);
    return bounds.getHeight();
  }
}
