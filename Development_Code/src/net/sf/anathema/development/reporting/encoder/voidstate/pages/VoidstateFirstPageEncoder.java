package net.sf.anathema.development.reporting.encoder.voidstate.pages;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import net.disy.commons.core.graphics.font.FontStyle;
import net.sf.anathema.character.generic.framework.reporting.ICharacterReportConstants;
import net.sf.anathema.character.generic.framework.reporting.template.voidstate.ExaltVoidstateReportTemplate;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.development.reporting.encoder.util.HeaderDataEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.subreports.attribute.AbstractVoidstateAttributePageEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.subreports.combat.stats.VoidstateCombatStatsPageEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.subreports.middlecolumn.DefaultMiddleColumnPageEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.traits.VoidStateAbilityEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.util.HealthRectangleEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.util.SlashSeparatedLineEncoder;
import net.sf.anathema.development.reporting.util.HeaderData;
import net.sf.anathema.development.reporting.util.ParameterUtilities;
import net.sf.anathema.development.reporting.util.TraitEncoder;
import net.sf.anathema.framework.reporting.encoding.TextEncoding;
import net.sf.anathema.framework.styledtext.model.ITextPart;
import net.sf.anathema.framework.styledtext.presentation.TextFormat;
import net.sf.anathema.framework.styledtext.presentation.TextPart;

import org.dom4j.Element;

public class VoidstateFirstPageEncoder extends AbstractVoidstatePageEncoder {

  public VoidstateFirstPageEncoder(int pageWidth) {
    super(pageWidth);
  }

  public int encodeBand(Element bandElement) {
    int height = 0;
    height += encodePersonalBox(bandElement);
    int leftHeight = height;
    leftHeight += PADDING;
    leftHeight += encodeAttributes(leftHeight, bandElement);
    leftHeight += PADDING;
    leftHeight += encodeAbilites(leftHeight, bandElement);
    leftHeight += PADDING;
    leftHeight += encodeCombatStats(leftHeight, bandElement);
    int centerHeight = height;
    centerHeight += PADDING;
    centerHeight += encodeMiddleColumn(bandElement, centerHeight);
    centerHeight += PADDING;
    int rightHeight = 0;
    rightHeight += encodeEssence(bandElement);
    rightHeight += PADDING;
    int centerRightHeight = Math.max(centerHeight, rightHeight);
    centerRightHeight += encodeWeaponry(centerRightHeight, bandElement);
    centerRightHeight += PADDING;
    centerRightHeight += encodeArmour(centerRightHeight, bandElement);
    centerRightHeight += PADDING;
    centerRightHeight += encodeHealth(centerRightHeight, bandElement);
    centerRightHeight += PADDING;
    centerRightHeight += encodeCombatSequence(centerRightHeight, bandElement);
    int totalHeight = Math.max(leftHeight, centerRightHeight);
    totalHeight += 1;
    totalHeight += encodeCopyrightNotice(leftHeight, bandElement);
    return totalHeight;
  }

  protected int encodeMiddleColumn(Element bandElement, int centerHeight) {
    Rectangle boxRectangle = DefaultMiddleColumnPageEncoder.getExtents(basicsEncoder);
    boxRectangle.setLocation(basicsEncoder.getSecondColumnX(), centerHeight);
    String subreportParameterName = ExaltVoidstateReportTemplate.PARAM_MIDDLECOLUMN_SUBREPORT;
    encodeSubreport(bandElement, boxRectangle, subreportParameterName);
    return boxRectangle.height;
  }

  private int encodeCopyrightNotice(int y, Element parent) {
    TextFormat copyrightFormat = new TextFormat(null, FontStyle.PLAIN, false, 6, Color.DARK_GRAY);
    ITextPart[] voidstateTextPart = new ITextPart[] { new TextPart("Original sheet design by Voidstate "
        + '\u00A9'
        + "2006\\n(http://www.voidstate.com)", copyrightFormat) };
    Element voidstateElement = TextEncoding.addTextFieldElement(parent);
    int columnWidth = basicsEncoder.getSingleColumnWidth();
    addStyledTextToTextFieldElement(voidstateElement, voidstateTextPart, FONT_SIZE, VALUE_LEFT, new Rectangle(
        basicsEncoder.getFirstColumnX(),
        y,
        columnWidth,
        LINE_HEIGHT));
    ITextPart[] anathemaTextPart = new ITextPart[] { new TextPart("Created with Anathema "
        + '\u00A9'
        + "2006\\n(http://anathema.sf.net)", copyrightFormat) };
    Element anathemaElement = TextEncoding.addTextFieldElement(parent);
    addStyledTextToTextFieldElement(anathemaElement, anathemaTextPart, FONT_SIZE, VALUE_CENTER, new Rectangle(
        basicsEncoder.getSecondColumnX(),
        y,
        columnWidth,
        LINE_HEIGHT));
    Element whitewolfElement = TextEncoding.addTextFieldElement(parent);
    ITextPart[] whitewolfTextPart = new ITextPart[] { new TextPart("Exalted "
        + '\u00A9'
        + "2006 by White Wolf, Inc.\\n(http://www.white-wolf.com)", copyrightFormat) };
    addStyledTextToTextFieldElement(whitewolfElement, whitewolfTextPart, FONT_SIZE, VALUE_RIGHT, new Rectangle(
        basicsEncoder.getThirdColumnX(),
        y,
        columnWidth,
        LINE_HEIGHT));
    try {
      addHyperlink(voidstateElement, new URL("http://www.voidstate.com"));
      addHyperlink(anathemaElement, new URL("http://anathema.sf.net"));
      addHyperlink(whitewolfElement, new URL("http://www.white-wolf.com"));
    }
    catch (MalformedURLException e) {
      // Nothing to do
    }
    return LINE_HEIGHT;
  }

  private int encodeCombatSequence(int y, Element bandElement) {
    Rectangle boxRectangle = basicsEncoder.createTwoColumnBoxBoundsWithoutTitle(0, 2, new Point(
        basicsEncoder.getSecondColumnX(),
        y));
    encodeSubreport(bandElement, boxRectangle, ExaltVoidstateReportTemplate.PARAM_SEQUENCE_SUBREPORT);
    return boxRectangle.height;
  }

  private int encodeHealth(int y, Element bandElement) {
    Rectangle boxRectangle = basicsEncoder.createTwoColumnBoxBoundsWithoutTitle(5, 8, new Point(
        basicsEncoder.getSecondColumnX(),
        y));
    Rectangle textRect = basicsEncoder.encodeBoxAndQuotifyHeader(bandElement, boxRectangle, "Health");
    int halfWidth = (textRect.width - 6) / 2;
    Rectangle explanatoryTextRectangle = new Rectangle(
        textRect.x + (textRect.width / 2) + 3,
        textRect.y,
        halfWidth,
        textRect.height);
    TextFormat descriptionTextFormat = new TextFormat(FontStyle.PLAIN, false, FONT_SIZE - 1);
    ITextPart healthText = new TextPart("Health: ", DEFAULT_TEXT_FORMAT);
    ITextPart healthTextDescription = new TextPart(
        "Bashing damage heals 1 health level per 3 hours. Lethal damage healing rate varies (-0 = 6 hours; -1 = 2 days; -2 = 4 days; -4 = 1 week; Incapacitated = 1 week). Double these times if not resting. Aggravated damage heals at the same rate as lethal but cannot be healed magically.",
        descriptionTextFormat);
    addTextWithCaret(
        bandElement,
        explanatoryTextRectangle,
        new ITextPart[] { healthText, healthTextDescription },
        0,
        FONT_SIZE,
        LINE_HEIGHT);
    int explanatoryTextYOffset = LINE_HEIGHT * 3 + TEXT_PADDING;
    ITextPart bleedingText = new TextPart("Bleeding: ", DEFAULT_TEXT_FORMAT);
    ITextPart bleedingTextDescription = new TextPart(
        "Caused by L damage. Stamina (difficulty 1) to close wounds by force of will or lose 1L every few minutes.",
        descriptionTextFormat);
    addTextWithCaret(
        bandElement,
        explanatoryTextRectangle,
        new ITextPart[] { bleedingText, bleedingTextDescription },
        explanatoryTextYOffset,
        FONT_SIZE,
        LINE_HEIGHT);
    explanatoryTextYOffset += LINE_HEIGHT + TEXT_PADDING;
    ITextPart infectionText = new TextPart("Infection: ", DEFAULT_TEXT_FORMAT);
    ITextPart infectionTextDescription = new TextPart(
        "Caused by L damage. Stamina + Resistance (difficulty 1 or more) to resist.",
        descriptionTextFormat);
    addTextWithCaret(
        bandElement,
        explanatoryTextRectangle,
        new ITextPart[] { infectionText, infectionTextDescription },
        explanatoryTextYOffset,
        FONT_SIZE,
        LINE_HEIGHT);
    explanatoryTextYOffset += LINE_HEIGHT + TEXT_PADDING;
    ITextPart deathText = new TextPart("Death and Dying: ", DEFAULT_TEXT_FORMAT);
    ITextPart deathTextDescription = new TextPart(
        "When character reduced below Incapacitated, expires after (Stamina - 1 per health level below incapacitated) turns.",
        descriptionTextFormat);
    addTextWithCaret(
        bandElement,
        explanatoryTextRectangle,
        new ITextPart[] { deathText, deathTextDescription },
        explanatoryTextYOffset,
        FONT_SIZE,
        LINE_HEIGHT);
    explanatoryTextYOffset += LINE_HEIGHT * 2;
    ITextPart damageText = new TextPart("Marking Damage: ", DEFAULT_TEXT_FORMAT);
    Point bashingPoint = new Point(explanatoryTextRectangle.x + 63, explanatoryTextRectangle.y
        + 3
        + explanatoryTextYOffset);
    Dimension healthDimension = new Dimension(HEALTH_RECT_SIZE, HEALTH_RECT_SIZE);
    HealthRectangleEncoder healthRectangleEncoder = new HealthRectangleEncoder();
    healthRectangleEncoder.encodeHealthRect(bandElement, VALUE_COLOR_BLACK, bashingPoint.x, bashingPoint.y, "true");
    addLineElement(bandElement, new Rectangle(bashingPoint, healthDimension), VALUE_1_POINT, true, Color.GRAY);
    Point lethalPoint = new Point(bashingPoint.x + 32, bashingPoint.y);
    healthRectangleEncoder.encodeHealthRect(bandElement, VALUE_COLOR_BLACK, lethalPoint.x, lethalPoint.y, "true");
    addLineElement(bandElement, new Rectangle(lethalPoint, healthDimension), VALUE_1_POINT, false, Color.GRAY);
    addLineElement(bandElement, new Rectangle(lethalPoint, healthDimension), VALUE_1_POINT, true, Color.GRAY);
    Point aggravatedPoint = new Point(lethalPoint.x + 27, lethalPoint.y);
    healthRectangleEncoder.encodeHealthRect(
        bandElement,
        VALUE_COLOR_BLACK,
        aggravatedPoint.x,
        aggravatedPoint.y,
        "true");
    addLineElement(bandElement, new Rectangle(aggravatedPoint, healthDimension), VALUE_1_POINT, false, Color.GRAY);
    addLineElement(bandElement, new Rectangle(aggravatedPoint, healthDimension), VALUE_1_POINT, true, Color.GRAY);
    addLineElement(bandElement, new Rectangle(
        aggravatedPoint.x + HEALTH_RECT_SIZE / 2,
        aggravatedPoint.y,
        0,
        HEALTH_RECT_SIZE), VALUE_1_POINT, false, Color.GRAY);
    ITextPart damageTextDescription = new TextPart("    Bashing,     Lethal,      Aggravated", descriptionTextFormat);
    addTextWithCaret(
        bandElement,
        explanatoryTextRectangle,
        new ITextPart[] { damageText, damageTextDescription },
        explanatoryTextYOffset,
        FONT_SIZE,
        LINE_HEIGHT);
    return encodeHealthData(bandElement, boxRectangle, textRect, halfWidth);
  }

  protected int encodeHealthData(Element bandElement, Rectangle boxRectangle, Rectangle textRect, int halfWidth) {
    Rectangle subreportRectangle = new Rectangle(textRect);
    subreportRectangle.width = halfWidth;
    encodeSubreport(bandElement, subreportRectangle, ExaltVoidstateReportTemplate.PARAM_HEALTH_SUBREPORT);
    return boxRectangle.height;
  }

  private int encodeArmour(int y, Element bandElement) {
    Rectangle boxRectangle = basicsEncoder.createTwoColumnBoxBoundsWithTitle(1, new Point(
        basicsEncoder.getSecondColumnX(),
        y));
    Rectangle textRect = basicsEncoder.encodeBoxAndQuotifyHeader(bandElement, boxRectangle, "Armour");
    int xInset = 0;
    int shortColumnWidth = 37;
    int armourNameWidth = 64;
    addTextElement(
        bandElement,
        quotify("Armour"),
        HEADER_FONT_SIZE,
        VALUE_LEFT,
        textRect.x,
        textRect.y,
        textRect.width,
        LINE_HEIGHT);
    xInset += armourNameWidth;
    addTextElement(
        bandElement,
        quotify("Soak (B/L)"),
        FONT_SIZE,
        VALUE_LEFT,
        textRect.x + xInset,
        textRect.y,
        textRect.width,
        LINE_HEIGHT);
    xInset += shortColumnWidth;
    addTextElement(
        bandElement,
        quotify("Mob Pen"),
        FONT_SIZE,
        VALUE_LEFT,
        textRect.x + xInset,
        textRect.y,
        textRect.width,
        LINE_HEIGHT);
    xInset += shortColumnWidth;
    addTextElement(
        bandElement,
        quotify("Fatigue"),
        FONT_SIZE,
        VALUE_LEFT,
        textRect.x + xInset,
        textRect.y,
        textRect.width,
        LINE_HEIGHT);
    xInset += shortColumnWidth;
    addTextElement(
        bandElement,
        quotify("Hardness"),
        FONT_SIZE,
        VALUE_LEFT,
        textRect.x + xInset,
        textRect.y,
        textRect.width,
        LINE_HEIGHT);
    xInset += shortColumnWidth;
    addTextElement(
        bandElement,
        quotify("Shield"),
        HEADER_FONT_SIZE,
        VALUE_LEFT,
        textRect.x + xInset,
        textRect.y,
        textRect.width,
        LINE_HEIGHT);
    addTextElement(bandElement, quotify("Type"), FONT_SIZE - 1, VALUE_LEFT, textRect.x + xInset, textRect.y
        + LINE_HEIGHT, textRect.width, LINE_HEIGHT);
    int shieldAreaYOffset = 3 * LINE_HEIGHT - 2;
    addThinLineElement(bandElement, new Rectangle(textRect.x + xInset, textRect.y + shieldAreaYOffset, 55, 0));
    xInset += 60;
    addTextElement(
        bandElement,
        quotify("Cover Bonus"),
        FONT_SIZE,
        VALUE_LEFT,
        textRect.x + xInset,
        textRect.y,
        textRect.width,
        LINE_HEIGHT);
    addTextElement(bandElement, quotify("Melee"), FONT_SIZE - 1, VALUE_LEFT, textRect.x + xInset, textRect.y
        + LINE_HEIGHT, textRect.width, LINE_HEIGHT);
    addTextElement(bandElement, quotify("Missile"), FONT_SIZE - 1, VALUE_LEFT, textRect.x + xInset + 32, textRect.y
        + LINE_HEIGHT, textRect.width - xInset, LINE_HEIGHT);
    addThinLineElement(bandElement, new Rectangle(textRect.x + xInset, textRect.y + shieldAreaYOffset, 25, 0));
    xInset += 28;
    addTextElement(bandElement, quotify("/"), FONT_SIZE - 1, VALUE_LEFT, textRect.x + xInset, textRect.y
        + 2
        * LINE_HEIGHT, textRect.width, LINE_HEIGHT);
    xInset += 4;
    addThinLineElement(bandElement, new Rectangle(textRect.x + xInset, textRect.y + shieldAreaYOffset, 25, 0));
    int yOffset = 2 * LINE_HEIGHT - 2;
    addArmourEntry(bandElement, textRect, shortColumnWidth, armourNameWidth, yOffset);
    yOffset += LINE_HEIGHT;
    addArmourEntry(bandElement, textRect, shortColumnWidth, armourNameWidth, yOffset);
    return boxRectangle.height;
  }

  private void addArmourEntry(
      Element bandElement,
      Rectangle textRect,
      int shortColumnWidth,
      int armourNameWidth,
      int yOffset) {
    Rectangle longLineRectangle = new Rectangle(textRect.x, textRect.y + yOffset, armourNameWidth - 6, 0);
    addThinLineElement(bandElement, longLineRectangle);
    new SlashSeparatedLineEncoder().encodeSlashSeparatedLines(bandElement, textRect.x + armourNameWidth, textRect.y
        + yOffset
        - LINE_HEIGHT, LINE_HEIGHT, 1, shortColumnWidth / 2 - 3);
    Rectangle shortLineRectangle = new Rectangle(
        textRect.x + armourNameWidth + shortColumnWidth,
        longLineRectangle.y,
        shortColumnWidth - 6,
        0);
    for (int lineNumber = 0; lineNumber < 3; lineNumber++) {
      addThinLineElement(bandElement, shortLineRectangle);
      shortLineRectangle.x += shortColumnWidth;
    }
  }

  private int encodeWeaponry(int y, Element bandElement) {
    Rectangle boxRectangle = basicsEncoder.createTwoColumnBoxBoundsWithTitle(12, new Point(
        basicsEncoder.getSecondColumnX(),
        y));
    Rectangle textRect = basicsEncoder.encodeBoxAndQuotifyHeader(bandElement, boxRectangle, "Weaponry");
    int shortColumnWidth = 35;
    int weaponNameWidth = 160;
    encodeMeleeWeapons(bandElement, textRect, shortColumnWidth, weaponNameWidth);
    encodeMissileWeapons(bandElement, textRect, 7 * LINE_HEIGHT, shortColumnWidth, weaponNameWidth);
    int yOffset = LINE_HEIGHT * 12;
    addBrawlWeapons(bandElement, textRect, yOffset);
    return boxRectangle.height;
  }

  protected void addBrawlWeapons(Element parent, Rectangle textRect, int yOffset) {
    Rectangle bounds = new Rectangle(textRect.x, textRect.y + yOffset, textRect.width, textRect.height - yOffset);
    encodeSubreport(parent, bounds, ExaltVoidstateReportTemplate.PARAM_BRAWL_SUBREPORT);
  }

  private void encodeMissileWeapons(
      Element bandElement,
      Rectangle textRect,
      int yOffset,
      int shortColumnWidth,
      int weaponNameWidth) {
    int xInset = 0;
    int initialY = textRect.y + yOffset;
    addTextElement(
        bandElement,
        quotify("Missile Weapons"),
        HEADER_FONT_SIZE,
        VALUE_LEFT,
        textRect.x,
        initialY,
        textRect.width,
        LINE_HEIGHT);
    xInset += weaponNameWidth;
    addTextElement(
        bandElement,
        quotify("Accuracy"),
        FONT_SIZE,
        VALUE_LEFT,
        textRect.x + xInset,
        initialY,
        textRect.width,
        LINE_HEIGHT);
    xInset += shortColumnWidth;
    addTextElement(
        bandElement,
        quotify("Damage"),
        FONT_SIZE,
        VALUE_LEFT,
        textRect.x + xInset,
        initialY,
        textRect.width,
        LINE_HEIGHT);
    xInset += shortColumnWidth;
    addTextElement(
        bandElement,
        quotify("Rate"),
        FONT_SIZE,
        VALUE_LEFT,
        textRect.x + xInset,
        initialY,
        textRect.width,
        LINE_HEIGHT);
    xInset += shortColumnWidth;
    addTextElement(
        bandElement,
        quotify("Range"),
        FONT_SIZE,
        VALUE_LEFT,
        textRect.x + xInset,
        initialY,
        textRect.width,
        LINE_HEIGHT);
    String[] backgroundTexts = createMissileBackgroundTexts();
    addWeaponEntry(
        bandElement,
        textRect.x,
        initialY + 2 * LINE_HEIGHT,
        shortColumnWidth,
        weaponNameWidth,
        4,
        backgroundTexts);
    addWeaponEntry(
        bandElement,
        textRect.x,
        initialY + 4 * LINE_HEIGHT,
        shortColumnWidth,
        weaponNameWidth,
        4,
        backgroundTexts);

  }

  private String[] createMissileBackgroundTexts() {
    return new String[] { "+Dex+Ability", "+Strength" };
  }

  private void encodeMeleeWeapons(Element bandElement, Rectangle textRect, int shortColumnWidth, int weaponNameWidth) {
    int xInset = 0;
    addTextElement(
        bandElement,
        quotify("Melee Weapons"),
        HEADER_FONT_SIZE,
        VALUE_LEFT,
        textRect.x,
        textRect.y,
        textRect.width,
        LINE_HEIGHT);
    xInset += weaponNameWidth;
    addTextElement(
        bandElement,
        quotify("Speed"),
        FONT_SIZE,
        VALUE_LEFT,
        textRect.x + xInset,
        textRect.y,
        textRect.width,
        LINE_HEIGHT);
    xInset += shortColumnWidth;
    addTextElement(
        bandElement,
        quotify("Accuracy"),
        FONT_SIZE,
        VALUE_LEFT,
        textRect.x + xInset,
        textRect.y,
        textRect.width,
        LINE_HEIGHT);
    xInset += shortColumnWidth;
    addTextElement(
        bandElement,
        quotify("Damage"),
        FONT_SIZE,
        VALUE_LEFT,
        textRect.x + xInset,
        textRect.y,
        textRect.width,
        LINE_HEIGHT);
    xInset += shortColumnWidth;
    addTextElement(
        bandElement,
        quotify("Defence"),
        FONT_SIZE,
        VALUE_LEFT,
        textRect.x + xInset,
        textRect.y,
        textRect.width,
        LINE_HEIGHT);
    xInset += shortColumnWidth;
    addTextElement(
        bandElement,
        quotify("Rate"),
        FONT_SIZE,
        VALUE_LEFT,
        textRect.x + xInset,
        textRect.y,
        textRect.width,
        LINE_HEIGHT);
    String[] backgroundTexts = createMeleeBackgroundTexts();
    addWeaponEntry(
        bandElement,
        textRect.x,
        textRect.y + 2 * LINE_HEIGHT,
        shortColumnWidth,
        weaponNameWidth,
        5,
        backgroundTexts);
    addWeaponEntry(
        bandElement,
        textRect.x,
        textRect.y + 4 * LINE_HEIGHT,
        shortColumnWidth,
        weaponNameWidth,
        5,
        backgroundTexts);
    addWeaponEntry(
        bandElement,
        textRect.x,
        textRect.y + 6 * LINE_HEIGHT,
        shortColumnWidth,
        weaponNameWidth,
        5,
        backgroundTexts);
  }

  private void addWeaponEntry(
      Element bandElement,
      int x,
      int y,
      int shortColumnWidth,
      int weaponNameWidth,
      int attributeCount,
      String[] modifierTexts) {
    Rectangle longLineRectangle = new Rectangle(x, y, weaponNameWidth - 6, 0);
    addThinLineElement(bandElement, longLineRectangle);
    Rectangle shortLineRectangle = new Rectangle(x + weaponNameWidth, longLineRectangle.y, shortColumnWidth - 6, 0);
    int originalShortRectX = shortLineRectangle.x;
    for (int lineNumber = 0; lineNumber < attributeCount; lineNumber++) {
      addThinLineElement(bandElement, shortLineRectangle);
      shortLineRectangle.x += shortColumnWidth;
    }
    shortLineRectangle.x = originalShortRectX;
    addTextElement(
        bandElement,
        quotify("inc. modifiers:"),
        FONT_SIZE - 1,
        VALUE_RIGHT,
        longLineRectangle.x,
        longLineRectangle.y,
        longLineRectangle.width,
        LINE_HEIGHT);
    Rectangle deepShortRectangle = new Rectangle(shortLineRectangle);
    deepShortRectangle.y += LINE_HEIGHT;
    for (String backgroundText : modifierTexts) {
      addWeaponBackgroundText(bandElement, shortLineRectangle, backgroundText);
      addThinLineElement(bandElement, deepShortRectangle);
      shortLineRectangle.x += shortColumnWidth;
      deepShortRectangle.x += shortColumnWidth;
    }
  }

  private String[] createMeleeBackgroundTexts() {
    return new String[] { "+Initiative", "+Dex+Ability", "+Strength", "+Dex+Ability" };
  }

  private void addWeaponBackgroundText(Element bandElement, Rectangle shortLineRectangle, String text) {
    addStyledTextElement(
        bandElement,
        new ITextPart[] { new TextPart(text, new TextFormat(Color.LIGHT_GRAY)) },
        FONT_SIZE - 2,
        VALUE_LEFT,
        shortLineRectangle.x,
        shortLineRectangle.y,
        shortLineRectangle.width,
        LINE_HEIGHT);
  }

  private int encodeEssence(Element bandElement) {
    Rectangle boxRectangle = basicsEncoder.createOneColumnBoxBoundsWithTitle(25, 6, new Point(
        basicsEncoder.getThirdColumnX(),
        0));
    Rectangle textRect = basicsEncoder.encodeBoxAndQuotifyHeader(bandElement, boxRectangle, "Essence & Anima");
    int yOffset = 3;
    yOffset += basicsEncoder.addCentralHeader(bandElement, textRect, textRect.y, "Permanent Essence");
    int dotSize = 11;
    int dotCount = TraitEncoder.MAX_DOT_COUNT;
    int dotSpacing = (textRect.width - 20 - dotCount * dotSize) / (dotCount - 1);
    yOffset += traitEncoder.encodeWithoutText(bandElement, OtherTraitType.Essence.getId(), textRect.x + 10, textRect.y
        + yOffset, dotCount, dotSize, dotSpacing, false);
    yOffset += 3;
    TextFormat smallDefaultTextFormat = new TextFormat(FontStyle.PLAIN, false, FONT_SIZE - 1);
    ITextPart totalPersonalEssencePart = new TextPart("\"+$P{"
        + ICharacterReportConstants.ESSENCE_PERSONAL_POOL
        + "}.toString()+\"", DEFAULT_TEXT_FORMAT);
    ITextPart totalTextPart = new TextPart(" Total / ", smallDefaultTextFormat);
    ITextPart AvailableEssencePart = new TextPart("_____", new TextFormat(FontStyle.PLAIN, true));
    ITextPart AvailableTextPart = new TextPart(" Available", smallDefaultTextFormat);
    addStyledTextElement(bandElement, new ITextPart[] {
        totalPersonalEssencePart,
        totalTextPart,
        AvailableEssencePart,
        AvailableTextPart }, FONT_SIZE, VALUE_RIGHT, textRect.x, textRect.y + yOffset, textRect.width, LINE_HEIGHT);
    yOffset += addTitledRectangle(bandElement, textRect, textRect.y + yOffset, 35, "Personal");
    yOffset += 3;
    ITextPart totalPeripheralEssencePart = new TextPart("\"+$P{"
        + ICharacterReportConstants.ESSENCE_PERIPHERAL_POOL
        + "}.toString()+\"", DEFAULT_TEXT_FORMAT);
    addStyledTextElement(bandElement, new ITextPart[] {
        totalPeripheralEssencePart,
        totalTextPart,
        AvailableEssencePart,
        AvailableTextPart }, FONT_SIZE, VALUE_RIGHT, textRect.x, textRect.y + yOffset, textRect.width, LINE_HEIGHT);
    yOffset += addTitledRectangle(bandElement, textRect, textRect.y + yOffset, 35, "Peripheral");
    yOffset += 3;
    yOffset += addTitledRectangle(bandElement, textRect, textRect.y + yOffset, 20, "Committed");
    yOffset += 3;
    addAnima(bandElement, textRect, yOffset);
    return boxRectangle.height;
  }

  private void addAnima(Element bandElement, Rectangle textRect, int yOffset) {
    Rectangle bounds = new Rectangle(textRect.x, textRect.y + yOffset, textRect.width, textRect.height - yOffset);
    encodeSubreport(bandElement, bounds, ExaltVoidstateReportTemplate.PARAM_ANIMA_SUBREPORT);
  }

  private int addTitledRectangle(Element bandElement, Rectangle parentRectangle, int y, int height, String title) {
    int yOffset = 0;
    addTextElement(
        bandElement,
        quotify(title),
        FONT_SIZE,
        VALUE_LEFT,
        parentRectangle.x,
        y,
        parentRectangle.width,
        LINE_HEIGHT);
    yOffset += LINE_HEIGHT;
    addShape(
        bandElement,
        TAG_RECTANGLE,
        VALUE_COLOR_WHITE,
        parentRectangle.x,
        y + yOffset,
        parentRectangle.width,
        height,
        "true");
    return yOffset + height;
  }

  protected int encodeCombatStats(int y, Element bandElement) {
    Rectangle boxRectangle = VoidstateCombatStatsPageEncoder.calculateExtents(basicsEncoder);
    boxRectangle.setLocation(basicsEncoder.getFirstColumnX(), y);
    String subreportParameterName = ExaltVoidstateReportTemplate.PARAM_COMBAT_STATS_SUBREPORT;
    encodeSubreport(bandElement, boxRectangle, subreportParameterName);
    return boxRectangle.height;
  }

  private int encodeAbilites(int y, Element bandElement) {
    Point position = new Point(0, y);
    VoidStateAbilityEncoder abilityEncoder = new VoidStateAbilityEncoder(basicsEncoder, traitEncoder);
    Rectangle abiltyRect = abilityEncoder.calculateAbilityExtents(position);
    String subreportParameterName = ExaltVoidstateReportTemplate.PARAM_ABILITY_SUBREPORT;
    encodeSubreport(bandElement, abiltyRect, subreportParameterName);
    return abiltyRect.height;
  }

  protected int encodeAttributes(int y, Element bandElement) {
    Rectangle boxRectangle = AbstractVoidstateAttributePageEncoder.calculateExtents(basicsEncoder);
    boxRectangle.setLocation(basicsEncoder.getFirstColumnX(), y);
    String subreportParameterName = ExaltVoidstateReportTemplate.PARAM_ATTRIBUTE_SUBREPORT;
    Map<String, String> subreportParameterMap = new HashMap<String, String>();
    for (AttributeType attributeType : AttributeType.values()) {
      subreportParameterMap.put(attributeType.getId(), attributeType.getId());
    }
    encodeSubreportWithParameters(bandElement, boxRectangle, subreportParameterName, null, subreportParameterMap);
    return boxRectangle.height;
  }

  protected int encodePersonalBox(Element bandElement) {
    HeaderDataEncoder headerDataEncoder = new HeaderDataEncoder(basicsEncoder.getTextLineEncoder(), LINE_HEIGHT);
    HeaderData[] headerData = getHeaderData();
    Rectangle boxRectangle = basicsEncoder.createTwoColumnBoxBoundsWithoutTitle(2, 1, new Point(0, 0));
    Rectangle textRectangle = basicsEncoder.encodeBox(bandElement, boxRectangle, ParameterUtilities.parameterString(ICharacterReportConstants.CHARACTER_NAME));
    textRectangle.height += 8;
    headerDataEncoder.encode(bandElement, textRectangle, headerData);
    return boxRectangle.height;
  }

  public String getGroupName() {
    return "VoidStateFirstPage";
  }

  protected HeaderData[] getHeaderData() {
    return new HeaderData[] {
        new HeaderData("Rules", ICharacterReportConstants.RULESET),
        new HeaderData("Player"),
        new HeaderData("Concept", ICharacterReportConstants.CONCEPT),
        new HeaderData("Campaign"),
        new HeaderData("Caste", "caste"),
        new HeaderData("Anima") };
  }
}