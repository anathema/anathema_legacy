package net.sf.anathema.character.reporting.pdf.layout.simple;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.layout.AbstractPageEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.EncoderIds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.general.CopyrightEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageConfiguration;
import net.sf.anathema.lib.resources.IResources;

import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.ARSENAL;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.ESSENCE_SIMPLE;
import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.PADDING;

public class FirstPageEncoder extends AbstractPageEncoder {
  private static final float ANIMA_HEIGHT = 128;
  private static final int ATTRIBUTE_HEIGHT = 128;
  private static final int FIRST_ROW_HEIGHT = 51;
  private static final int VIRTUE_HEIGHT = 72;
  private static final int SOCIAL_COMBAT_HEIGHT = 115;
  private static final int WILLPOWER_HEIGHT = 43;
  private static final int ARMOUR_HEIGHT = 80;
  private static final int HEALTH_HEIGHT = 99;
  private final PageConfiguration configuration;

  public FirstPageEncoder(EncoderRegistry encoders, IResources resources, PageConfiguration configuration) {
    super(resources, encoders);
    this.configuration = configuration;
  }

  @Override
  public void encode(Document document, SheetGraphics graphics, ReportContent content) throws DocumentException {
    float distanceFromTop = 0;
    encodePersonalInfo(graphics, content, distanceFromTop, FIRST_ROW_HEIGHT);
    encodeEssence(graphics, content, distanceFromTop, FIRST_ROW_HEIGHT);

    distanceFromTop += FIRST_ROW_HEIGHT + PADDING;

    encodeFirstColumn(graphics, content, distanceFromTop);
    encodeAnima(graphics, content, distanceFromTop, ANIMA_HEIGHT);
    float virtueHeight = encodeVirtues(graphics, distanceFromTop, VIRTUE_HEIGHT, content);
    distanceFromTop += calculateBoxIncrement(virtueHeight);
    float greatCurseHeight = ANIMA_HEIGHT - virtueHeight - PADDING;
    encodeGreatCurse(graphics, content, distanceFromTop, greatCurseHeight);
    distanceFromTop += calculateBoxIncrement(greatCurseHeight);

    float socialCombatHeight = encodeSocialCombatOrMeritsAndFlaws(graphics, content, distanceFromTop, SOCIAL_COMBAT_HEIGHT);
    float willpowerHeight = encodeWillpower(graphics, content, distanceFromTop, WILLPOWER_HEIGHT);
    float willpowerIncrement = calculateBoxIncrement(willpowerHeight);
    distanceFromTop += willpowerIncrement;
    float intimaciesHeight = encodeIntimacies(graphics, content, distanceFromTop, socialCombatHeight - willpowerIncrement);
    distanceFromTop += calculateBoxIncrement(intimaciesHeight);
    float weaponryHeight = encodeWeaponry(graphics, content, distanceFromTop);
    distanceFromTop += calculateBoxIncrement(weaponryHeight);
    float armourHeight = encodeArmourAndSoak(graphics, content, distanceFromTop, ARMOUR_HEIGHT);
    distanceFromTop += calculateBoxIncrement(armourHeight);
    float healthHeight = encodeMovementAndHealth(graphics, content, distanceFromTop, HEALTH_HEIGHT);
    distanceFromTop += calculateBoxIncrement(healthHeight);
    float remainingHeight = CONTENT_HEIGHT - distanceFromTop;
    encodeCombat(graphics, content, distanceFromTop, remainingHeight);
    new CopyrightEncoder(configuration, CONTENT_HEIGHT).encodeCopyright(graphics);
  }

  private void encodePersonalInfo(SheetGraphics graphics, ReportContent content, float distanceFromTop, float firstRowHeight) throws DocumentException {
    Bounds bounds = configuration.getFirstColumnRectangle(distanceFromTop, firstRowHeight, 2);
    encodeBox(graphics, content, bounds, EncoderIds.PERSONAL_INFO);
  }

  private void encodeAbilities(SheetGraphics graphics, ReportContent content, float distanceFromTop) throws DocumentException {
    Bounds bounds = configuration.getFirstColumnRectangle(distanceFromTop, CONTENT_HEIGHT - distanceFromTop, 1);
    encodeBox(graphics, content, bounds, EncoderIds.ABILITIES_WITH_CRAFTS_AND_SPECIALTIES);
  }

  private float encodeVirtues(SheetGraphics graphics, float distanceFromTop, float height, ReportContent content) throws DocumentException {
    Bounds bounds = configuration.getSecondColumnRectangle(distanceFromTop, height, 1);
    return encodeBox(graphics, content, bounds, EncoderIds.VIRTUES);
  }

  private float encodeEssence(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds essenceBounds = configuration.getThirdColumnRectangle(distanceFromTop, height);
    return encodeBox(graphics, content, essenceBounds, ESSENCE_SIMPLE);
  }

  private void encodeFirstColumn(SheetGraphics graphics, ReportContent content, float distanceFromTop) throws DocumentException {
    float attributeHeight = encodeAttributes(graphics, content, distanceFromTop);
    encodeAbilities(graphics, content, distanceFromTop + attributeHeight + PADDING);
  }

  private float encodeAttributes(SheetGraphics graphics, ReportContent content, float distanceFromTop) throws DocumentException {
    Bounds attributeBounds = configuration.getFirstColumnRectangle(distanceFromTop, ATTRIBUTE_HEIGHT, 1);
    return encodeBox(graphics, content, attributeBounds, EncoderIds.ATTRIBUTES);
  }

  private void encodeAnima(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds animaBounds = configuration.getThirdColumnRectangle(distanceFromTop, height);
    encodeOptionalBox(graphics, content, animaBounds, EncoderIds.ANIMA);
  }

  private float encodeSocialCombatOrMeritsAndFlaws(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = configuration.getThirdColumnRectangle(distanceFromTop, height);
    return encodeBox(graphics, content, bounds, EncoderIds.SOCIAL_COMBAT, EncoderIds.MERITS_AND_FLAWS);
  }

  private float encodeArmourAndSoak(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = configuration.getSecondColumnRectangle(distanceFromTop, height, 2);
    return encodeBox(graphics, content, bounds, EncoderIds.PANOPLY);
  }

  private float encodeCombat(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = configuration.getSecondColumnRectangle(distanceFromTop, height, 2);
    return encodeBox(graphics, content, bounds, EncoderIds.COMBAT);
  }

  private float encodeMovementAndHealth(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = configuration.getSecondColumnRectangle(distanceFromTop, height, 2);
    return encodeBox(graphics, content, bounds, EncoderIds.HEALTH_AND_MOVEMENT);
  }

  private float encodeWeaponry(SheetGraphics graphics, ReportContent content, float distanceFromTop) throws DocumentException {
    float height = getPreferredEncoderHeight(content, ARSENAL);
    Bounds bounds = configuration.getSecondColumnRectangle(distanceFromTop, height, 2);
    return encodeBox(graphics, content, bounds, ARSENAL);
  }

  private float encodeWillpower(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = configuration.getSecondColumnRectangle(distanceFromTop, height, 1);
    return encodeBox(graphics, content, bounds, EncoderIds.WILLPOWER_SIMPLE);
  }

  private float encodeGreatCurse(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = configuration.getSecondColumnRectangle(distanceFromTop, height, 1);
    return encodeOptionalBox(graphics, content, bounds, EncoderIds.GREAT_CURSE);
  }

  private float encodeIntimacies(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = configuration.getSecondColumnRectangle(distanceFromTop, height, 1);
    return encodeBox(graphics, content, bounds, EncoderIds.INTIMACIES_SIMPLE, EncoderIds.NOTES);
  }

  private float calculateBoxIncrement(float height) {
    return height + PADDING;
  }
}
