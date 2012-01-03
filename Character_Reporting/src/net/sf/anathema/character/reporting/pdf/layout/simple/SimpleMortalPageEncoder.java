package net.sf.anathema.character.reporting.pdf.layout.simple;

import com.lowagie.text.Anchor;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.EncoderIds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.BoxContentEncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.abilities.AbilitiesBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.personal.PersonalInfoBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.virtues.VirtueBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.willpower.SimpleWillpowerBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.PdfBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.HorizontalAlignment;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.page.IPdfPageEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.pdf.rendering.page.PdfPageConfiguration;
import net.sf.anathema.lib.resources.IResources;

import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.ARSENAL;
import static net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderAttributeType.PreferredHeight;

public class SimpleMortalPageEncoder implements IPdfPageEncoder {
  public static final float CONTENT_HEIGHT = 755;
  private final IResources resources;
  private final int essenceMax;

  private static final float ANIMA_HEIGHT = 128;
  private final PdfPageConfiguration pageConfiguration;
  private final PdfBoxEncoder boxEncoder;
  private final SimpleEncodingRegistry registry;
  private BoxContentEncoderRegistry encoderRegistry;
  private final ISimplePartEncoder partEncoder;

  public SimpleMortalPageEncoder(BoxContentEncoderRegistry encoderRegistry, ISimplePartEncoder partEncoder, SimpleEncodingRegistry registry,
    IResources resources, int essenceMax, PdfPageConfiguration pageConfiguration) {
    this.encoderRegistry = encoderRegistry;
    this.partEncoder = partEncoder;
    this.essenceMax = essenceMax;
    this.resources = resources;
    this.registry = registry;
    this.pageConfiguration = pageConfiguration;
    this.boxEncoder = new PdfBoxEncoder(resources);
  }

  @Override
  public void encode(Document document, SheetGraphics graphics, ReportContent content) throws DocumentException {
    float distanceFromTop = 0;
    final float firstRowHeight = 51;
    encodePersonalInfo(graphics, content, distanceFromTop, firstRowHeight);
    encodeExperience(graphics, content, distanceFromTop, firstRowHeight);

    distanceFromTop += firstRowHeight + IVoidStateFormatConstants.PADDING;

    encodeFirstColumn(graphics, content, distanceFromTop);
    encodeAnima(graphics, content, distanceFromTop, ANIMA_HEIGHT);
    float virtueHeight = encodeVirtues(graphics, distanceFromTop, 72, content);
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
    float weaponryHeight = encodeWeaponry(graphics, content, distanceFromTop);
    distanceFromTop += calculateBoxIncrement(weaponryHeight);
    float armourHeight = encodeArmourAndSoak(graphics, content, distanceFromTop, 80);
    distanceFromTop += calculateBoxIncrement(armourHeight);
    float healthHeight = encodeMovementAndHealth(graphics, content, distanceFromTop, 99);
    distanceFromTop += calculateBoxIncrement(healthHeight);
    float remainingHeight = SimpleMortalPageEncoder.CONTENT_HEIGHT - distanceFromTop;
    encodeCombatStats(graphics, content, distanceFromTop, remainingHeight);
    encodeCopyright(graphics);
  }

  private void encodeCopyright(SheetGraphics graphics) throws DocumentException {
    float lineHeight = IVoidStateFormatConstants.COMMENT_FONT_SIZE + 2;
    Font copyrightFont = graphics.createCommentFont();
    float copyrightHeight = pageConfiguration.getPageHeight() - pageConfiguration.getContentHeight();
    Bounds firstColumnBounds = pageConfiguration.getFirstColumnRectangle(CONTENT_HEIGHT, copyrightHeight, 1);
    Anchor voidstatePhrase = new Anchor("Inspired by Voidstate\nhttp://www.voidstate.com", copyrightFont); //$NON-NLS-1$
    voidstatePhrase.setReference("http://www.voidstate.com"); //$NON-NLS-1$
    graphics.createSimpleColumn(firstColumnBounds).withLeading(lineHeight).andTextPart(voidstatePhrase).encode();

    Anchor anathemaPhrase = new Anchor("Created with Anathema \u00A92011\nhttp://anathema.sf.net", copyrightFont); //$NON-NLS-1$
    anathemaPhrase.setReference("http://anathema.sf.net"); //$NON-NLS-1$
    Bounds anathemaBounds = pageConfiguration.getSecondColumnRectangle(CONTENT_HEIGHT, copyrightHeight, 1);
    graphics.createSimpleColumn(anathemaBounds).withLeading(lineHeight).andAlignment(HorizontalAlignment.Center).andTextPart(anathemaPhrase).encode
      ();
    Anchor whitewolfPhrase = new Anchor("Exalted \u00A92011 by White Wolf, Inc.\nhttp://www.white-wolf.com", //$NON-NLS-1$
      copyrightFont);
    whitewolfPhrase.setReference("http://www.white-wolf.com"); //$NON-NLS-1$
    Bounds whitewolfBounds = pageConfiguration.getThirdColumnRectangle(CONTENT_HEIGHT, copyrightHeight);
    graphics.createSimpleColumn(whitewolfBounds).withLeading(lineHeight).andAlignment(HorizontalAlignment.Right).andTextPart(whitewolfPhrase)
      .encode();
  }

  private float encodeExperience(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds essenceBounds = pageConfiguration.getThirdColumnRectangle(distanceFromTop, height);
    IBoxContentEncoder encoder = encoderRegistry.createEncoder(resources, content, EncoderIds.EXPERIENCE);
    boxEncoder.encodeBox(content, graphics, encoder, essenceBounds);
    return height;
  }

  private String getHeaderLabel(String key) {
    return resources.getString("Sheet.Header." + key); //$NON-NLS-1$
  }

  private void encodePersonalInfo(SheetGraphics graphics, ReportContent content, float distanceFromTop, float firstRowHeight) {
    Bounds infoBounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, firstRowHeight, 2);
    String name = content.getDescription().getName();
    String title = StringUtilities.isNullOrTrimEmpty(name) ? getHeaderLabel("PersonalInfo") : name; //$NON-NLS-1$
    Bounds infoContentBounds = boxEncoder.encodeBox(graphics, infoBounds, title);
    encodePersonalInfo(graphics, content, infoContentBounds);
  }

  private void encodeFirstColumn(SheetGraphics graphics, ReportContent content, float distanceFromTop) throws DocumentException {
    float attributeHeight = encodeAttributes(graphics, content, distanceFromTop);
    encodeAbilities(graphics, content, distanceFromTop + attributeHeight + IVoidStateFormatConstants.PADDING);
  }

  private void encodeAbilities(SheetGraphics graphics, ReportContent content, float distanceFromTop) throws DocumentException {
    float abilitiesHeight = CONTENT_HEIGHT - distanceFromTop;
    Bounds boxBounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, abilitiesHeight, 1);
    IBoxContentEncoder encoder = AbilitiesBoxContentEncoder.createWithCraftsAndSpecialties(resources, 9, 9);
    boxEncoder.encodeBox(content, graphics, encoder, boxBounds);
  }

  private float encodeAttributes(SheetGraphics graphics, ReportContent content, float distanceFromTop) throws DocumentException {
    float attributeHeight = 128;
    Bounds attributeBounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, attributeHeight, 1);
    IBoxContentEncoder encoder = encoderRegistry.createEncoder(resources, content, EncoderIds.ATTRIBUTES);
    boxEncoder.encodeBox(content, graphics, encoder, attributeBounds);
    return attributeHeight;
  }

  private float calculateBoxIncrement(float height) {
    return height + IVoidStateFormatConstants.PADDING;
  }

  private void encodeAnima(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds animaBounds = pageConfiguration.getThirdColumnRectangle(distanceFromTop, height);
    IBoxContentEncoder encoder = partEncoder.getAnimaEncoder();
    if (encoder != null) {
      boxEncoder.encodeBox(content, graphics, encoder, animaBounds);
    }
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
    return encode(graphics, content, bounds, EncoderIds.SOCIAL_COMBAT, EncoderIds.MERITS_AND_FLAWS);
  }

  private float encodeCombatStats(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    IBoxContentEncoder encoder = encoderRegistry.createEncoder(resources, content, EncoderIds.COMBAT);
    boxEncoder.encodeBox(content, graphics, encoder, bounds);
    return height;
  }

  private float encodeMovementAndHealth(SheetGraphics graphics, ReportContent content, float distanceFromTop,
    float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    IBoxContentEncoder encoder = encoderRegistry.createEncoder(resources, content, EncoderIds.HEALTH_AND_MOVEMENT);
    boxEncoder.encodeBox(content, graphics, encoder, bounds);
    return height;
  }

  private void encodePersonalInfo(SheetGraphics graphics, ReportContent content, Bounds infoBounds) {
    PersonalInfoBoxEncoder encoder = new PersonalInfoBoxEncoder(resources);
    encoder.encode(graphics, content, infoBounds);
  }

  private float encodeVirtues(SheetGraphics graphics, float distanceFromTop, float height, ReportContent content) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 1);
    IBoxContentEncoder encoder = new VirtueBoxContentEncoder();
    boxEncoder.encodeBox(content, graphics, encoder, bounds);
    return height;
  }

  private float encodeWeaponry(SheetGraphics graphics, ReportContent content, float distanceFromTop) throws DocumentException {
    float height = encoderRegistry.getValue(PreferredHeight, content, ARSENAL);
    IBoxContentEncoder weaponryEncoder = encoderRegistry.createEncoder(resources, content, ARSENAL);
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
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
    if (encoder == null) {
      return 0;
    }
    boxEncoder.encodeBox(content, graphics, encoder, bounds);
    return height;
  }

  private float encodeIntimacies(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 1);
    IBoxContentEncoder encoder = partEncoder.getIntimaciesEncoder(registry);
    boxEncoder.encodeBox(content, graphics, encoder, bounds);
    return height;
  }

  private float encode(SheetGraphics graphics, ReportContent content, Bounds bounds, String... encoderIds) throws DocumentException {
    IBoxContentEncoder encoder = encoderRegistry.createEncoder(resources, content, encoderIds);
    boxEncoder.encodeBox(content, graphics, encoder, bounds);
    return bounds.getHeight();
  }
}
