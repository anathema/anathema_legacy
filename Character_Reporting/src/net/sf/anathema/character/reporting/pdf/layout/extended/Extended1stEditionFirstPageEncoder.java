package net.sf.anathema.character.reporting.pdf.layout.extended;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.BoxContentEncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.abilities.AbilitiesBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.attributes.PdfAttributesEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.combat.CombatStatsContentBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.health.AbstractHealthAndMovementEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.personal.PersonalInfoEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.virtues.VirtueBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.willpower.SimpleWillpowerBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.CopyrightEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.PdfBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.page.IPdfPageEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.pdf.rendering.page.PdfPageConfiguration;
import net.sf.anathema.lib.resources.IResources;

import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.PADDING;

public class Extended1stEditionFirstPageEncoder implements IPdfPageEncoder {
  public static final int CONTENT_HEIGHT = 755;
  private final IResources resources;
  private final int essenceMax;

  private static final int ANIMA_HEIGHT = 128;
  private final PdfPageConfiguration pageConfiguration;
  private final PdfBoxEncoder boxEncoder;
  private final ExtendedEncodingRegistry registry;
  private BoxContentEncoderRegistry encoderRegistry;
  private final IExtendedPartEncoder partEncoder;

  public Extended1stEditionFirstPageEncoder(BoxContentEncoderRegistry encoderRegistry, IExtendedPartEncoder partEncoder,
    ExtendedEncodingRegistry registry, IResources resources, int essenceMax, PdfPageConfiguration pageConfiguration) {
    this.encoderRegistry = encoderRegistry;
    this.partEncoder = partEncoder;
    this.essenceMax = essenceMax;
    this.resources = resources;
    this.registry = registry;
    this.pageConfiguration = pageConfiguration;
    this.boxEncoder = new PdfBoxEncoder(resources);
  }

  public void encode(Document document, SheetGraphics graphics, ReportContent content) throws

    DocumentException {
    int distanceFromTop = 0;
    final int firstRowHeight = 51;
    encodePersonalInfo(graphics, content, distanceFromTop, firstRowHeight);
    encodeEssence(graphics, content, distanceFromTop, firstRowHeight);

    distanceFromTop += firstRowHeight + PADDING;

    encodeFirstColumn(graphics, content, distanceFromTop);
    encodeAnima(graphics, content, distanceFromTop, ANIMA_HEIGHT);
    float virtueHeight = encodeVirtues(graphics, content, distanceFromTop, 72);
    distanceFromTop += calculateBoxIncrement(virtueHeight);
    float greatCurseHeigth = ANIMA_HEIGHT - virtueHeight - IVoidStateFormatConstants.PADDING;
    encodeGreatCurse(graphics, content, distanceFromTop, greatCurseHeigth);
    distanceFromTop += calculateBoxIncrement(greatCurseHeigth);

    float socialCombatHeight = encodeSocialCombatStats(graphics, content, distanceFromTop, 115);
    float willpowerHeight = encodeWillpower(graphics, content, distanceFromTop, 43);
    float willpowerIncrement = calculateBoxIncrement(willpowerHeight);
    distanceFromTop += willpowerIncrement;
    float intimaciesHeight = encodeIntimacies(graphics, content, distanceFromTop, socialCombatHeight - willpowerIncrement);
    distanceFromTop += calculateBoxIncrement(intimaciesHeight);
    float weaponryHeight = encodeWeaponry(graphics, content, distanceFromTop, partEncoder.getWeaponryHeight());
    distanceFromTop += calculateBoxIncrement(weaponryHeight);
    float armourHeight = encodeArmourAndSoak(graphics, content, distanceFromTop, 80);
    distanceFromTop += calculateBoxIncrement(armourHeight);
    float healthHeight = encodeMovementAndHealth(graphics, content, distanceFromTop, 99);
    distanceFromTop += calculateBoxIncrement(healthHeight);
    float remainingHeight = Extended1stEditionFirstPageEncoder.CONTENT_HEIGHT - distanceFromTop;
    encodeCombatStats(graphics, content, distanceFromTop, remainingHeight);
    new CopyrightEncoder(pageConfiguration, CONTENT_HEIGHT).encodeCopyright(graphics);
  }

  private float encodeEssence(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds essenceBounds = pageConfiguration.getThirdColumnRectangle(distanceFromTop, height);
    IBoxContentEncoder encoder = partEncoder.getEssenceEncoder();
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

  private void encodeFirstColumn(SheetGraphics graphics, ReportContent content, float distanceFromTop) throws DocumentException {
    float attributeHeight = encodeAttributes(graphics, content, distanceFromTop);
    encodeAbilities(graphics, content, distanceFromTop + attributeHeight + PADDING);
  }

  private void encodeAbilities(SheetGraphics graphics, ReportContent content, float distanceFromTop) throws DocumentException {
    float abilitiesHeight = CONTENT_HEIGHT - distanceFromTop;
    Bounds boxBounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, abilitiesHeight, 1);
    IBoxContentEncoder encoder = AbilitiesBoxContentEncoder.createWithCraftsAndSpecialties(resources, essenceMax, 9, 9);
    boxEncoder.encodeBox(content, graphics, encoder, boxBounds);
  }

  private float encodeAttributes(SheetGraphics graphics, ReportContent content, float distanceFromTop) throws DocumentException {
    float attributeHeight = 128;
    Bounds attributeBounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, attributeHeight, 1);
    IBoxContentEncoder encoder = new PdfAttributesEncoder(resources, essenceMax, partEncoder.isEncodeAttributeAsFavorable());
    boxEncoder.encodeBox(content, graphics, encoder, attributeBounds);
    return attributeHeight;
  }

  private float calculateBoxIncrement(float height) {
    return height + PADDING;
  }

  private void encodeAnima(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds animaBounds = pageConfiguration.getThirdColumnRectangle(distanceFromTop, height);
    IBoxContentEncoder encoder = partEncoder.getAnimaEncoder();
    boxEncoder.encodeBox(content, graphics, encoder, animaBounds);
  }

  private float encodeArmourAndSoak(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    IBoxContentEncoder contentEncoder = registry.getArmourContentEncoder();
    boxEncoder.encodeBox(content, graphics, contentEncoder, bounds);
    return height;
  }

  private float encodeSocialCombatStats(SheetGraphics graphics, ReportContent content, float distanceFromTop,
    float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getThirdColumnRectangle(distanceFromTop, height);
    IBoxContentEncoder encoder = partEncoder.getSocialCombatEncoder();
    boxEncoder.encodeBox(content, graphics, encoder, bounds);
    return height;
  }

  private float encodeCombatStats(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    IBoxContentEncoder encoder = encoderRegistry.createEncoder(CombatStatsContentBoxEncoder.ID, resources, content);
    boxEncoder.encodeBox(content, graphics, encoder, bounds);
    return height;
  }

  private float encodeMovementAndHealth(SheetGraphics graphics, ReportContent content, float distanceFromTop,
    float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    IBoxContentEncoder encoder = encoderRegistry.createEncoder(AbstractHealthAndMovementEncoder.ID, resources, content);
    boxEncoder.encodeBox(content, graphics, encoder, bounds);
    return height;
  }

  private void encodePersonalInfo(SheetGraphics graphics, ReportContent content, Bounds infoBounds) {
    PersonalInfoEncoder encoder = new PersonalInfoEncoder(resources);
    encoder.encodePersonalInfo(graphics, content, infoBounds);
  }

  private float encodeVirtues(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 1);
    IBoxContentEncoder encoder = new VirtueBoxContentEncoder();
    boxEncoder.encodeBox(content, graphics, encoder, bounds);
    return height;
  }

  private float encodeWeaponry(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    IBoxContentEncoder weaponryEncoder = registry.getWeaponContentEncoder();
    boxEncoder.encodeBox(content, graphics, weaponryEncoder, bounds);
    return height;
  }

  private float encodeWillpower(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds willpowerBounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 1);
    boxEncoder.encodeBox(content, graphics, new SimpleWillpowerBoxContentEncoder(), willpowerBounds);
    return height;
  }

  private float encodeGreatCurse(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 1);
    IBoxContentEncoder encoder = partEncoder.getGreatCurseEncoder();
    boxEncoder.encodeBox(content, graphics, encoder, bounds);
    return height;
  }

  private float encodeIntimacies(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 1);
    IBoxContentEncoder encoder = partEncoder.getIntimaciesEncoder(registry);
    boxEncoder.encodeBox(content, graphics, encoder, bounds);
    return height;
  }
}
