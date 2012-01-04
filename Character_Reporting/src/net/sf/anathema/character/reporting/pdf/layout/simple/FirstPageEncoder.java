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

import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.ABILITIES_WITH_CRAFTS_AND_SPECIALTIES;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.ARSENAL;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.ATTRIBUTES;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.ESSENCE_SIMPLE;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.PERSONAL_INFO;
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
    float fromTop = 0;
    fromTop += getYIncrement(encodePersonalInfoAndEssenceRow(graphics, content, fromTop));
    encodeRemainingPageContent(graphics, content, fromTop);
    new CopyrightEncoder(configuration, CONTENT_HEIGHT).encodeCopyright(graphics);
  }

  private void encodeRemainingPageContent(SheetGraphics graphics, ReportContent content, float fromTop) {
    encodeAttributesAndAbilitiesColumn(graphics, content, fromTop);
    encodeAnimaAndSocialCombatColumn(graphics, content, fromTop);
    fromTop += getYIncrement(encodeVirtueAndWillpowerColumn(graphics, content, fromTop));
    encodeWeaponryAndCombatBlock(graphics, content, fromTop);
  }

  private void encodeAnimaAndSocialCombatColumn(SheetGraphics graphics, ReportContent content, float fromTop) {
    fromTop += getYIncrement(encodeAnima(graphics, content, fromTop, ANIMA_HEIGHT));
    encodeSocialCombatOrMeritsAndFlaws(graphics, content, fromTop, SOCIAL_COMBAT_HEIGHT);
  }

  private float encodePersonalInfoAndEssenceRow(SheetGraphics graphics, ReportContent content, float fromTop) {
    Bounds personalBounds = configuration.getFirstColumnRectangle(fromTop, FIRST_ROW_HEIGHT, 2);
    encodeBox(graphics, content, personalBounds, PERSONAL_INFO);
    Bounds essenceBounds = configuration.getThirdColumnRectangle(fromTop, FIRST_ROW_HEIGHT);
    encodeBox(graphics, content, essenceBounds, ESSENCE_SIMPLE);
    return FIRST_ROW_HEIGHT;
  }

  private void encodeAttributesAndAbilitiesColumn(SheetGraphics graphics, ReportContent content, float fromTop) {
    fromTop += getYIncrement(encodeAttributes(graphics, content, fromTop));
    encodeAbilities(graphics, content, fromTop);
  }

  private void encodeWeaponryAndCombatBlock(SheetGraphics graphics, ReportContent content, float fromTop) {
    fromTop += getYIncrement(encodeWeaponry(graphics, content, fromTop));
    fromTop += getYIncrement(encodeArmourAndSoak(graphics, content, fromTop, ARMOUR_HEIGHT));
    fromTop += getYIncrement(encodeMovementAndHealth(graphics, content, fromTop, HEALTH_HEIGHT));
    encodeCombat(graphics, content, fromTop, CONTENT_HEIGHT - fromTop);
  }

  private float encodeVirtueAndWillpowerColumn(SheetGraphics graphics, ReportContent content, float fromTop) {
    float columnHeight = 0;
    float virtuesIncrement = getYIncrement(encodeVirtues(graphics, fromTop + columnHeight, VIRTUE_HEIGHT, content));
    columnHeight += virtuesIncrement;
    columnHeight += getYIncrement(encodeGreatCurse(graphics, content, fromTop + columnHeight, ANIMA_HEIGHT - virtuesIncrement));

    float willpowerIncrement = getYIncrement(encodeWillpower(graphics, content, fromTop + columnHeight, WILLPOWER_HEIGHT));
    columnHeight += willpowerIncrement;
    columnHeight += encodeIntimacies(graphics, content, fromTop + columnHeight, SOCIAL_COMBAT_HEIGHT - willpowerIncrement);
    return columnHeight;
  }

  private void encodeAbilities(SheetGraphics graphics, ReportContent content, float fromTop) {
    Bounds bounds = configuration.getFirstColumnRectangle(fromTop, CONTENT_HEIGHT - fromTop, 1);
    encodeBox(graphics, content, bounds, ABILITIES_WITH_CRAFTS_AND_SPECIALTIES);
  }

  private float encodeAnima(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) {
    Bounds animaBounds = configuration.getThirdColumnRectangle(distanceFromTop, height);
    encodeOptionalBox(graphics, content, animaBounds, EncoderIds.ANIMA);
    return animaBounds.getHeight();
  }

  private float encodeAttributes(SheetGraphics graphics, ReportContent content, float fromTop) {
    Bounds attributeBounds = configuration.getFirstColumnRectangle(fromTop, ATTRIBUTE_HEIGHT, 1);
    return encodeBox(graphics, content, attributeBounds, ATTRIBUTES);
  }

  private float encodeArmourAndSoak(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) {
    Bounds bounds = configuration.getSecondColumnRectangle(distanceFromTop, height, 2);
    return encodeBox(graphics, content, bounds, EncoderIds.PANOPLY);
  }

  private float encodeCombat(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) {
    Bounds bounds = configuration.getSecondColumnRectangle(distanceFromTop, height, 2);
    return encodeBox(graphics, content, bounds, EncoderIds.COMBAT);
  }

  private float encodeGreatCurse(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) {
    Bounds bounds = configuration.getSecondColumnRectangle(distanceFromTop, height, 1);
    return encodeOptionalBox(graphics, content, bounds, EncoderIds.GREAT_CURSE);
  }

  private float encodeIntimacies(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) {
    Bounds bounds = configuration.getSecondColumnRectangle(distanceFromTop, height, 1);
    return encodeBox(graphics, content, bounds, EncoderIds.INTIMACIES_SIMPLE, EncoderIds.NOTES);
  }

  private float encodeMovementAndHealth(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) {
    Bounds bounds = configuration.getSecondColumnRectangle(distanceFromTop, height, 2);
    return encodeBox(graphics, content, bounds, EncoderIds.HEALTH_AND_MOVEMENT);
  }

  private float encodeSocialCombatOrMeritsAndFlaws(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) {
    Bounds bounds = configuration.getThirdColumnRectangle(distanceFromTop, height);
    return encodeBox(graphics, content, bounds, EncoderIds.SOCIAL_COMBAT, EncoderIds.MERITS_AND_FLAWS);
  }

  private float encodeVirtues(SheetGraphics graphics, float distanceFromTop, float height, ReportContent content) {
    Bounds bounds = configuration.getSecondColumnRectangle(distanceFromTop, height, 1);
    return encodeBox(graphics, content, bounds, EncoderIds.VIRTUES);
  }

  private float encodeWeaponry(SheetGraphics graphics, ReportContent content, float distanceFromTop) {
    float height = getPreferredEncoderHeight(content, ARSENAL);
    Bounds bounds = configuration.getSecondColumnRectangle(distanceFromTop, height, 2);
    return encodeBox(graphics, content, bounds, ARSENAL);
  }

  private float encodeWillpower(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) {
    Bounds bounds = configuration.getSecondColumnRectangle(distanceFromTop, height, 1);
    return encodeBox(graphics, content, bounds, EncoderIds.WILLPOWER_SIMPLE);
  }

  private float getYIncrement(float height) {
    return height + PADDING;
  }
}
