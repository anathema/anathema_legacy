package net.sf.anathema.development.reporting.encoder.voidstate.pages;

import java.awt.Point;
import java.awt.Rectangle;

import net.disy.commons.core.text.font.FontStyle;
import net.sf.anathema.character.generic.framework.reporting.ICharacterReportConstants;
import net.sf.anathema.character.generic.framework.reporting.template.voidstate.ExaltVoidstateReportTemplate;
import net.sf.anathema.development.reporting.encoder.voidstate.subreports.description.AbstractVoidstateDescriptionPageEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.util.CombosEncoder;
import net.sf.anathema.development.reporting.util.BackgroundsEncoder;
import net.sf.anathema.development.reporting.util.ParameterUtilities;
import net.sf.anathema.development.reporting.util.ReportDataSourceEncoder;
import net.sf.anathema.framework.styledtext.model.ITextPart;
import net.sf.anathema.framework.styledtext.presentation.TextFormat;
import net.sf.anathema.framework.styledtext.presentation.TextPart;

import org.dom4j.Element;

public class VoidstateSecondPageEncoder extends AbstractVoidstatePageEncoder {

  public static final int LOWEST_LINE_COUNT = 9;
  public static final int MIDDLE_LINE_COUNT = 10;

  public VoidstateSecondPageEncoder(int pageWidth) {
    super(pageWidth);
  }

  public int encodeBand(Element bandElement) {
    int magicHeight = encodeMagic(bandElement, new Point(0, 0));
    int leftColumnHeight = encodeLeftColumn(bandElement, new Point(0, magicHeight));
    int leftX = basicsEncoder.getThirdColumnX();
    encodeRightColumn(bandElement, new Point(leftX, magicHeight));
    return magicHeight + leftColumnHeight;
  }

  private int encodeMagic(Element bandElement, Point position) {
    Rectangle boxRectangle = basicsEncoder.createThreeColumnBoxBoundsWithTitle(31, position);
    Rectangle textRect = basicsEncoder.encodeBoxAndQuotifyHeader(bandElement, boxRectangle, "Charms & Sorcery");
    ReportDataSourceEncoder magicEncoder = ReportDataSourceEncoder.createVoidstateMagicEncoder(LINE_HEIGHT);
    int height = magicEncoder.encodeWithVoidstateHeader(
        bandElement,
        textRect.y,
        textRect.x,
        textRect.width,
        TEXT_PADDING,
        30,
        FONT_SIZE);
    TextFormat plainPartFormat = new TextFormat(FontStyle.PLAIN, false, FONT_SIZE - 2);
    TextFormat italicPartFormat = new TextFormat(FontStyle.ITALIC, false, FONT_SIZE - 2);
    ITextPart[] textParts = new ITextPart[] {
        new TextPart(
            "Only a single charm may be used per turn, although the same charm may be used multiple times if it is ",
            plainPartFormat),
        new TextPart("Supplemental", italicPartFormat),
        new TextPart(" or ", plainPartFormat),
        new TextPart("Reflexive", italicPartFormat),
        new TextPart(
            ". However, by combining multiple charms into a Combo, more than one charm can be used in a turn, provided each charm used is part of that combo. Charms can only double an Attribute + Ability dice pool. Characters may not split the dice pools granted by ",
            plainPartFormat),
        new TextPart("Extra Action", italicPartFormat),
        new TextPart(" charms.", plainPartFormat) };
    addTextWithCaret(bandElement, textRect, textParts, height + TEXT_PADDING, FONT_SIZE, LINE_HEIGHT);
    return boxRectangle.height;
  }

  private int encodeLeftColumn(Element bandElement, Point position) {
    int columnHeight = PADDING;
    columnHeight += encodeBackgrounds(bandElement, new Point(position.x, position.y + columnHeight));
    columnHeight += PADDING;
    columnHeight += encodePossessions(bandElement, new Point(position.x, position.y + columnHeight));
    columnHeight += PADDING;
    columnHeight += encodeDescription(bandElement, new Point(position.x, position.y + columnHeight));
    return columnHeight;
  }

  private int encodeBackgrounds(Element bandElement, Point position) {
    Rectangle boxRectangle = calculateBackgroundBounds(position);
    Rectangle textRect = basicsEncoder.encodeBoxAndQuotifyHeader(bandElement, boxRectangle, "Backgrounds");
    BackgroundsEncoder backgroundsEncoder = new BackgroundsEncoder(traitEncoder);
    backgroundsEncoder.encodeBackgroundsWithDescription(
        bandElement,
        textRect.y,
        textRect.x,
        textRect.width,
        10,
        TEXT_PADDING);
    return boxRectangle.height;
  }

  private Rectangle calculateBackgroundBounds(Point position) {
    return basicsEncoder.createCorrectedTwoColumnBoxBoundsWithTitle(10, position);
  }

  private int encodePossessions(Element bandElement, Point position) {
    Rectangle boxRectangle = basicsEncoder.createCorrectedTwoColumnBoxBoundsWithTitle(MIDDLE_LINE_COUNT - 1, position);
    Rectangle textRect = basicsEncoder.encodeBoxAndQuotifyHeader(bandElement, boxRectangle, "Possessions");
    basicsEncoder.addEmptyLines(bandElement, textRect, MIDDLE_LINE_COUNT);
    return boxRectangle.height;
  }

  private int encodeDescription(Element bandElement, Point position) {
    Rectangle reportRect = AbstractVoidstateDescriptionPageEncoder.calculateExtents(basicsEncoder);
    reportRect.setLocation(basicsEncoder.getFirstColumnX(), position.y);
    String subreportParameterName = ExaltVoidstateReportTemplate.PARAM_DESCRIPTION_SUBREPORT;
    encodeSubreport(bandElement, reportRect, subreportParameterName);
    return reportRect.height;
  }

  private int encodeRightColumn(Element bandElement, Point position) {
    int columnHeight = PADDING;
    int languageHeight = encodeLanguages(bandElement, new Point(position.x, position.y + columnHeight));
    languageHeight += PADDING;
    columnHeight += languageHeight;
    Rectangle experienceBounds = basicsEncoder.createOneColumnBox(
        new Point(position.x, position.y + columnHeight),
        calculateBackgroundBounds(position).height - languageHeight);
    columnHeight += encodeExperience(bandElement, experienceBounds);
    columnHeight += PADDING;
    columnHeight += encodeCombos(bandElement, new Point(position.x, position.y + columnHeight));
    return columnHeight;
  }

  private int encodeLanguages(Element bandElement, Point position) {
    int lineCount = 4;
    Rectangle boxRectangle = basicsEncoder.createCorrectedOneColumnBoxBoundsWithTitle(lineCount - 1, 0, position);
    Rectangle textRect = basicsEncoder.encodeBoxAndQuotifyHeader(bandElement, boxRectangle, "Languages");
    int languagePadding = PADDING / 2;
    int lineWidth = (textRect.width - languagePadding) / 2;
    basicsEncoder.addEmptyLines(bandElement, textRect.getLocation(), lineWidth, lineCount);
    basicsEncoder.addEmptyLines(
        bandElement,
        new Point(textRect.x + languagePadding + lineWidth, textRect.y),
        lineWidth,
        lineCount);
    return boxRectangle.height;
  }

  private int encodeExperience(Element bandElement, Rectangle bounds) {
    Rectangle textRect = basicsEncoder.encodeBoxAndQuotifyHeader(bandElement, bounds, "Experience");
    addExperienceValues(bandElement, textRect);
    return bounds.height;
  }

  private void addExperienceValues(Element bandElement, Rectangle bounds) {
    int yOffset = 0;
    addTextElement(
        bandElement,
        quotify("Total"),
        FONT_SIZE,
        VALUE_LEFT,
        bounds.x,
        bounds.y + yOffset,
        bounds.width,
        LINE_HEIGHT);
    addTextElement(bandElement, ParameterUtilities.parameterString(ICharacterReportConstants.EXPERIENCE_TOTAL)
        + methodCall("toString"), FONT_SIZE, VALUE_LEFT, bounds.x + 60, bounds.y + yOffset, bounds.width, LINE_HEIGHT);
    yOffset += LINE_HEIGHT;
    addTextElement(
        bandElement,
        quotify("- Spent"),
        FONT_SIZE,
        VALUE_LEFT,
        bounds.x,
        bounds.y + yOffset,
        bounds.width,
        LINE_HEIGHT);
    addTextElement(bandElement, ParameterUtilities.parameterString(ICharacterReportConstants.EXPERIENCE_SPENT)
        + methodCall("toString"), FONT_SIZE, VALUE_LEFT, bounds.x + 60, bounds.y + yOffset, bounds.width, LINE_HEIGHT);
    yOffset += LINE_HEIGHT;
    addThinLineElement(bandElement, new Rectangle(bounds.x, bounds.y + yOffset, 70, 0));
    addTextElement(
        bandElement,
        quotify("= Remaining"),
        FONT_SIZE,
        VALUE_LEFT,
        bounds.x,
        bounds.y + yOffset,
        bounds.width,
        LINE_HEIGHT);
    addTextElement(bandElement, ParameterUtilities.parameterString(ICharacterReportConstants.EXPERIENCE_REST)
        + methodCall("toString"), FONT_SIZE, VALUE_LEFT, bounds.x + 60, bounds.y + yOffset, bounds.width, LINE_HEIGHT);
  }

  private int encodeCombos(Element bandElement, Point position) {
    int comboLineCount = MIDDLE_LINE_COUNT + LOWEST_LINE_COUNT + 3;
    Rectangle boxRectangle = basicsEncoder.createCorrectedOneColumnBoxBoundsWithoutTitle(comboLineCount, 0, position);
    Rectangle textRect = basicsEncoder.encodeBoxAndQuotifyHeader(bandElement, boxRectangle, "Combos");
    new CombosEncoder(FONT_SIZE, basicsEncoder).encodeWithStyledText(bandElement, textRect, comboLineCount);
    return boxRectangle.height;
  }

  public String getGroupName() {
    return "VoidStateSecondPage";
  }
}