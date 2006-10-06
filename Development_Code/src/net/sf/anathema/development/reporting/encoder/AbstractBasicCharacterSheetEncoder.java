package net.sf.anathema.development.reporting.encoder;

import java.awt.Rectangle;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import net.sf.anathema.character.generic.framework.reporting.ICharacterReportConstants;
import net.sf.anathema.character.generic.health.HealthLevelType;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.generic.traits.types.AttributeGroupType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.impl.model.traits.creation.AbilityTypeGroupFactory;
import net.sf.anathema.development.reporting.encoder.page.PageFormat;
import net.sf.anathema.development.reporting.util.AbilitiesEncoder;
import net.sf.anathema.development.reporting.util.AttributesEncoder;
import net.sf.anathema.development.reporting.util.BackgroundsEncoder;
import net.sf.anathema.development.reporting.util.HeaderData;
import net.sf.anathema.development.reporting.util.ParameterUtilities;
import net.sf.anathema.development.reporting.util.ReportDataSourceEncoder;
import net.sf.anathema.development.reporting.util.TraitEncoder;
import net.sf.anathema.development.reporting.util.WillpowerEncoder;
import net.sf.anathema.framework.reporting.ExaltedReportUtilities;
import net.sf.anathema.framework.reporting.encoding.TextEncoding;
import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.lib.lang.ArrayUtilities;

import org.dom4j.Element;

public abstract class AbstractBasicCharacterSheetEncoder extends AbstractCharacterSheetEncoder {

  protected static final int LINE_HEIGHT = 12;
  protected static final int HEALTH_RECT_SIZE = 10;
  protected static final int DEFAULT_FONT_SIZE = 8;
  protected static final int HEADER_SIZE = 24;
  protected static final int INDENT = 30;
  private final static int ABILITY_COLUMN_COUNT = 3;

  protected final TraitEncoder traitEncoder = new TraitEncoder();
  protected final AttributesEncoder attributesEncoder = new AttributesEncoder();
  protected final AbilitiesEncoder abilitiesEncoder = new AbilitiesEncoder();
  protected final BackgroundsEncoder backgroundsEncoder = new BackgroundsEncoder(traitEncoder);
  protected final WillpowerEncoder willpowerEncoder = new WillpowerEncoder();

  public AbstractBasicCharacterSheetEncoder() throws InitializationException {
    super(PageFormat.createDefault(40, 40));
  }

  @Override
  protected final void encodeContent(Element rootElement) {
    Element titleElement = rootElement.addElement("title"); //$NON-NLS-1$
    Element bandElement = titleElement.addElement("band"); //$NON-NLS-1$
    int bandHeight = 10;
    bandHeight += encodeHeadData(bandElement, bandHeight, 0);
    bandHeight += encodeAttributes(bandElement, bandHeight, 0, 12);
    bandHeight += encodeAbilityGroups(bandElement, bandHeight, 0, 12);
    bandHeight += encodeAdvantages(bandElement, bandHeight, 0, 12);
    bandHeight += encodeRest(bandElement, bandHeight, 0, 12);
    bandElement.addAttribute("height", String.valueOf(bandHeight)); //$NON-NLS-1$
  }

  protected final int encodeRest(Element bandElement, int y, int x, int spacing) {
    int restHeight = 0;
    int restColumnWidth = getColumnWidth() / 3 - spacing;
    int yOffsetCenter = 0;
    int yOffsetRight = 0;

    int yOffsetLeft = encodeLeftRestColumn(bandElement, y, x, spacing, restColumnWidth);

    yOffsetCenter += encodeWillpower(bandElement, y, x + spacing + restColumnWidth, restColumnWidth, spacing);
    yOffsetCenter += encodeHealth(
        bandElement,
        y + yOffsetCenter,
        x + spacing + restColumnWidth,
        restColumnWidth,
        spacing,
        yOffsetLeft - yOffsetCenter);
    yOffsetRight += encodeVirtues(bandElement, y, x + 2 * spacing + 2 * restColumnWidth, restColumnWidth, spacing);
    yOffsetRight += encodeEssence(
        bandElement,
        x + 2 * spacing + 2 * restColumnWidth,
        y + yOffsetRight,
        restColumnWidth,
        spacing);
    yOffsetRight += encodeExperience(
        bandElement,
        x + 2 * spacing + 2 * restColumnWidth,
        y + yOffsetRight,
        restColumnWidth,
        spacing + 5,
        yOffsetLeft - yOffsetRight);
    yOffsetRight += encodeCopyright(
        bandElement,
        x + 2 * spacing + 2 * restColumnWidth,
        y + yOffsetRight,
        restColumnWidth);
    restHeight = Math.max(yOffsetLeft, Math.max(yOffsetCenter, yOffsetRight));
    return restHeight;
  }

  protected abstract int encodeLeftRestColumn(Element parent, int y, int x, int spacing, int restColumnWidth);

  private final int encodeAttributes(Element bandElement, int y, int x, int spacing) {
    addLinedHeader(bandElement, "Attributes", y, x, getColumnWidth(), spacing);
    int attributeColumnWidth = getColumnWidth() / 3 - spacing;
    int attributeHeight = 0;
    attributeHeight = Math.max(attributeHeight, attributesEncoder.encodeAttributeGroup(
        bandElement,
        AttributeType.getAllFor(AttributeGroupType.Physical),
        y + HEADER_SIZE,
        x,
        attributeColumnWidth));
    attributeHeight = Math.max(attributeHeight, attributesEncoder.encodeAttributeGroup(
        bandElement,
        AttributeType.getAllFor(AttributeGroupType.Social),
        y + HEADER_SIZE,
        x + attributeColumnWidth + spacing,
        attributeColumnWidth));
    attributeHeight = Math.max(attributeHeight, attributesEncoder.encodeAttributeGroup(
        bandElement,
        AttributeType.getAllFor(AttributeGroupType.Mental),
        y + HEADER_SIZE,
        x + (attributeColumnWidth + spacing) * 2,
        attributeColumnWidth));
    return attributeHeight + HEADER_SIZE;
  }

  protected final void addLinedHeader(Element bandElement, String text, int y, int x, int width, int lineShortening) {
    addLinedHeader(bandElement, text, y, x, width, lineShortening, 0);
  }

  protected final void addLinedHeader(
      Element bandElement,
      String text,
      int y,
      int x,
      int width,
      int lineShortening,
      int secondLineOffset) {
    addNormalLineElement(bandElement, new Rectangle(x, y + HEADER_SIZE / 2, width / 3 - lineShortening, 0));
    addTextElement(bandElement, quotify(text), HEADER_SIZE - 8, VALUE_CENTER, x, y, width, HEADER_SIZE);
    addNormalLineElement(bandElement, new Rectangle(x + width * 2 / 3 + secondLineOffset, y + HEADER_SIZE / 2, width
        / 3
        - lineShortening, 0));
  }

  private final int encodeAbilityGroups(Element bandElement, int y, int x, int spacing) {
    int originalY = y;
    addLinedHeader(bandElement, "Abilities", y, x, getColumnWidth(), spacing);
    int abilityColumnWidth = getColumnWidth() / ABILITY_COLUMN_COUNT - spacing;
    ICharacterTemplate template = getDefaultTemplate(getCharacterType());
    IIdentifiedTraitTypeGroup[] abilityGroups = new AbilityTypeGroupFactory().createTraitGroups(
        template.getCasteCollection(),
        template.getAbilityGroups());
    y += HEADER_SIZE;
    int variableX = x;
    int[] columnHeights = new int[ABILITY_COLUMN_COUNT];
    for (int index = 0; index < abilityGroups.length; index++) {
      int columnIndex = index % ABILITY_COLUMN_COUNT;
      variableX = x + (abilityColumnWidth + spacing) * (columnIndex);
      columnHeights[columnIndex] = abilitiesEncoder.encodeAbilityGroup(
          bandElement,
          abilityGroups[index],
          y,
          variableX,
          abilityColumnWidth,
          LINE_HEIGHT);
      if (columnIndex == 2 && index < abilityGroups.length - 1) {
        y += ArrayUtilities.max(columnHeights);
        y += LINE_HEIGHT;
        Arrays.fill(columnHeights, 0);
      }
    }
    int specialtyRowIndex = 5;
    variableX = x + (abilityColumnWidth + spacing) * (specialtyRowIndex % ABILITY_COLUMN_COUNT);
    columnHeights[ABILITY_COLUMN_COUNT - 1] = (columnHeights[ABILITY_COLUMN_COUNT - 1] == 0)
        ? 0
        : columnHeights[ABILITY_COLUMN_COUNT - 1] + LINE_HEIGHT;
    addTextElement(bandElement, quotify("Specialties"), 8, VALUE_CENTER, variableX, y
        + columnHeights[ABILITY_COLUMN_COUNT - 1], abilityColumnWidth, LINE_HEIGHT);
    columnHeights[ABILITY_COLUMN_COUNT - 1] += LINE_HEIGHT;
    columnHeights[ABILITY_COLUMN_COUNT - 1] += abilitiesEncoder.encodeSpecialties(bandElement, y
        + columnHeights[ABILITY_COLUMN_COUNT - 1], variableX, abilityColumnWidth, getSpecialtyLineCount(), 5);
    y += ArrayUtilities.max(columnHeights);
    return y - originalY;
  }

  protected abstract CharacterType getCharacterType();

  protected abstract int getSpecialtyLineCount();

  protected final int encodeHeadData(Element bandElement, int y, int x) {
    int imageWidth = 200;
    int imageHeight = 65;
    Element imageElement = addImageElement(
        bandElement,
        x,
        y,
        imageWidth,
        imageHeight,
        ParameterUtilities.parameterString(ExaltedReportUtilities.EXALTED_LOGO_URL));
    try {
      addHyperlink(imageElement, new URL("http://www.white-wolf.com/exalted")); //$NON-NLS-1$    
    }
    catch (MalformedURLException e) {
      e.printStackTrace();
    }
    HeaderData[] headerData = getHeaderData();
    int lineCount = (headerData.length + 1) / 2;
    int textHeight = Math.max(lineCount, 3) * LINE_HEIGHT;
    y += (imageHeight - textHeight) / 2;

    int xLeft = x + imageWidth;
    int xRight = xLeft + 140;
    for (int line = 0; line < lineCount; line++) {
      addHeaderData(bandElement, headerData[line * 2], y, xLeft);
      if (headerData.length > (line * 2) + 1) {
        addHeaderData(bandElement, headerData[(line * 2) + 1], y, xRight);
      }
      y += LINE_HEIGHT;
      if (lineCount < 3 && line == 0) {
        y += LINE_HEIGHT;
      }
    }
    return imageHeight;
  }

  protected abstract HeaderData[] getHeaderData();

  private final void addHeaderData(Element bandElement, HeaderData data, int y, int x) {
    if (data.getParameter() == null) {
      addLabeledLine(bandElement, data.getLabel(), y, x, 35, 100);
    }
    else {
      addLabeledTextParameter(bandElement, data.getLabel(), data.getParameter(), y, x, 35, 100, true);
    }
  }

  protected final int addLabeledTextParameter(
      Element bandElement,
      String label,
      String parameter,
      int y,
      int x,
      int labelWidth,
      int valueWidth) {
    return addLabeledTextParameter(bandElement, label, parameter, y, x, labelWidth, valueWidth, false);
  }

  protected final int addLabeledTextParameter(
      Element bandElement,
      String label,
      String parameter,
      int y,
      int x,
      int labelWidth,
      int valueWidth,
      boolean printLineIfNull) {
    int fontSize = DEFAULT_FONT_SIZE;
    addTextElement(bandElement, quotify(label + ":"), fontSize, VALUE_LEFT, x, y, labelWidth, LINE_HEIGHT); //$NON-NLS-1$
    addParameterOrLine(bandElement, parameter, y, x + labelWidth + 4, valueWidth - 4, printLineIfNull);
    return LINE_HEIGHT;
  }

  protected final void addParameterOrLine(
      Element parent,
      String parameter,
      int y,
      int x,
      int width,
      boolean printLineIfNull) {
    addTextElement(parent, ParameterUtilities.parameterString(parameter) + methodCall("toString"), //$NON-NLS-1$
        DEFAULT_FONT_SIZE,
        VALUE_LEFT,
        x,
        y,
        width,
        LINE_HEIGHT);
    if (printLineIfNull) {
      Element lineReportElement = addThinLineElement(parent, new Rectangle(x, y + LINE_HEIGHT, width - 4, 0));
      String parameterString = ParameterUtilities.parameterString(parameter);
      String printWhenExpression = parameterString + "==null||" + parameterString + methodCall("length") + "==0"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      lineReportElement.addElement(TAG_PRINT_WHEN_EXPRESSION).addCDATA(printWhenExpression); //$NON-NLS-1$ //$NON-NLS-2$
    }
  }

  protected final int addLabeledLine(Element bandElement, String label, int y, int x, int labelWidth, int lineWidth) {
    addTextElement(bandElement, quotify(label + ":"), DEFAULT_FONT_SIZE, VALUE_LEFT, x, y, labelWidth, LINE_HEIGHT); //$NON-NLS-1$
    addThinLineElement(bandElement, new Rectangle(x + labelWidth + 4, y + LINE_HEIGHT, lineWidth - 4, 0));
    return LINE_HEIGHT;
  }

  protected final int encodeAdvantages(Element bandElement, int y, int x, int spacing) {
    int advantageHeight = 0;
    addLinedHeader(bandElement, "Advantages", y, x, getColumnWidth(), spacing);
    int advantageColumnWidth = getColumnWidth() / 3 - spacing;
    y += HEADER_SIZE;
    advantageHeight += HEADER_SIZE;
    addTextElement(bandElement, quotify("Backgrounds"), 8, VALUE_CENTER, x, y, advantageColumnWidth, LINE_HEIGHT);
    advantageHeight += LINE_HEIGHT;
    advantageHeight += backgroundsEncoder.encodeBackgrounds(bandElement, y + LINE_HEIGHT, x, advantageColumnWidth, 10);
    addTextElement(
        bandElement,
        quotify(getAdvantageDataSourceTitle()),
        8,
        VALUE_CENTER,
        x + advantageColumnWidth,
        y,
        advantageColumnWidth * 2,
        LINE_HEIGHT);
    createAdvantageDataSourceEncoder().encode(
        bandElement,
        y + LINE_HEIGHT,
        x + advantageColumnWidth + spacing,
        advantageColumnWidth * 2 + spacing,
        spacing,
        10);
    return advantageHeight;
  }

  protected abstract String getAdvantageDataSourceTitle();

  protected abstract ReportDataSourceEncoder createAdvantageDataSourceEncoder();

  protected final int encodeTitledEmptyLines(
      Element parent,
      String title,
      int count,
      int y,
      int x,
      int width,
      int spacing) {
    addLinedHeader(parent, title, y, x, width, spacing, spacing);
    y += HEADER_SIZE;
    for (int index = 0; index < count; index++) {
      addThinLineElement(parent, new Rectangle(x, y + (1 + index) * LINE_HEIGHT, width, 0));
    }
    return HEADER_SIZE + count * LINE_HEIGHT;
  }

  protected int encodeExperience(Element parent, int x, int y, int width, int lineSpacing, int height) {
    addLinedHeader(parent, "Experience", y, x, width, lineSpacing, lineSpacing);
    int boxHeight = height - HEADER_SIZE / 2;
    addNormalLineElement(parent, new Rectangle(x, y + HEADER_SIZE / 2, 0, boxHeight));
    addNormalLineElement(parent, new Rectangle(x + width, y + HEADER_SIZE / 2, 0, boxHeight));
    addNormalLineElement(parent, new Rectangle(x, y + height - 1, width, 0));
    addExperienceValues(parent, new Rectangle(x + 2, y + HEADER_SIZE - LINE_HEIGHT / 2, width, boxHeight));
    return height;
  }

  private void addExperienceValues(Element bandElement, Rectangle bounds) {
    int yOffset = 0;
    addTextElement(
        bandElement,
        quotify("Total"),
        DEFAULT_FONT_SIZE - 2,
        VALUE_LEFT,
        bounds.x,
        bounds.y + yOffset,
        bounds.width,
        LINE_HEIGHT);
    addTextElement(
        bandElement,
        ParameterUtilities.parameterString(ICharacterReportConstants.EXPERIENCE_TOTAL) + methodCall("toString"),
        DEFAULT_FONT_SIZE - 2,
        VALUE_LEFT,
        bounds.x + 60,
        bounds.y + yOffset,
        bounds.width,
        LINE_HEIGHT);
    yOffset += LINE_HEIGHT - 3;
    addTextElement(
        bandElement,
        quotify("- Spent"),
        DEFAULT_FONT_SIZE - 2,
        VALUE_LEFT,
        bounds.x,
        bounds.y + yOffset,
        bounds.width,
        LINE_HEIGHT);
    addTextElement(
        bandElement,
        ParameterUtilities.parameterString(ICharacterReportConstants.EXPERIENCE_SPENT) + methodCall("toString"),
        DEFAULT_FONT_SIZE - 2,
        VALUE_LEFT,
        bounds.x + 60,
        bounds.y + yOffset,
        bounds.width,
        LINE_HEIGHT);
    yOffset += LINE_HEIGHT - 3;
    addThinLineElement(bandElement, new Rectangle(bounds.x, bounds.y + yOffset + 3, 70, 0));
    addTextElement(
        bandElement,
        quotify("= Remaining"),
        DEFAULT_FONT_SIZE - 2,
        VALUE_LEFT,
        bounds.x,
        bounds.y + yOffset,
        bounds.width,
        LINE_HEIGHT);
    addTextElement(
        bandElement,
        ParameterUtilities.parameterString(ICharacterReportConstants.EXPERIENCE_REST) + methodCall("toString"),
        DEFAULT_FONT_SIZE - 2,
        VALUE_LEFT,
        bounds.x + 60,
        bounds.y + yOffset,
        bounds.width,
        LINE_HEIGHT);
  }

  protected int encodeHealth(Element parent, int y, int x, int width, int lineSpacing, int height) {
    int originalY = y;
    addLinedHeader(parent, "Health", y, x, width, lineSpacing, lineSpacing);
    y += HEADER_SIZE;
    y += encodeSoak(parent, y, x, width);
    y += LINE_HEIGHT;
    int rightBoxBound = x + width - INDENT;
    int boxHeight = height + originalY - y;
    addNormalLineElement(parent, new Rectangle(x + INDENT, y, width - 2 * INDENT, 0));
    addNormalLineElement(parent, new Rectangle(x + INDENT, y, 0, boxHeight));
    addNormalLineElement(parent, new Rectangle(rightBoxBound, y, 0, boxHeight));
    addNormalLineElement(parent, new Rectangle(x + INDENT, originalY + height - 1, width - 2 * INDENT, 0));
    int rectLinePadding = LINE_HEIGHT / 2 - 1;
    y += rectLinePadding - 1;
    int padding = 3;
    int leftSquareX = rightBoxBound - 5 * (HEALTH_RECT_SIZE + padding);
    x = x + INDENT + 8;
    int textYCorrection = -2;
    addTextElement(parent, quotify("-0"), DEFAULT_FONT_SIZE, VALUE_LEFT, x, y + textYCorrection, x
        + width
        - leftSquareX, LINE_HEIGHT);
    for (int i = 0; i < 5; i++) {
      HealthLevelType healthLevelType = HealthLevelType.ZERO;
      int squareX = leftSquareX + i * (padding + HEALTH_RECT_SIZE);
      encodeHealthRectPair(parent, squareX, y, i, healthLevelType);
    }
    y += HEALTH_RECT_SIZE + rectLinePadding;
    addTextElement(parent, quotify("-1"), DEFAULT_FONT_SIZE, VALUE_LEFT, x, y + textYCorrection, x
        + width
        - leftSquareX, LINE_HEIGHT);
    for (int i = 0; i < 5; i++) {
      HealthLevelType healthLevelType = HealthLevelType.ONE;
      int squareX = leftSquareX + i * (padding + HEALTH_RECT_SIZE);
      encodeHealthRectPair(parent, squareX, y, i, healthLevelType);
    }
    y += HEALTH_RECT_SIZE + rectLinePadding;
    for (int i = 0; i < 5; i++) {
      HealthLevelType healthLevelType = HealthLevelType.ONE;
      int squareX = leftSquareX + i * (padding + HEALTH_RECT_SIZE);
      encodeHealthRectPair(parent, squareX, y, i + 5, healthLevelType);
    }
    y += HEALTH_RECT_SIZE + rectLinePadding;
    addTextElement(parent, quotify("-2"), DEFAULT_FONT_SIZE, VALUE_LEFT, x, y + textYCorrection, x
        + width
        - leftSquareX, LINE_HEIGHT);
    for (int i = 0; i < 5; i++) {
      HealthLevelType healthLevelType = HealthLevelType.TWO;
      int squareX = leftSquareX + i * (padding + HEALTH_RECT_SIZE);
      encodeHealthRectPair(parent, squareX, y, i, healthLevelType);
    }
    y += HEALTH_RECT_SIZE + rectLinePadding;
    for (int i = 0; i < 5; i++) {
      HealthLevelType healthLevelType = HealthLevelType.TWO;
      int squareX = leftSquareX + i * (padding + HEALTH_RECT_SIZE);
      encodeHealthRectPair(parent, squareX, y, i + 5, healthLevelType);
    }
    y += HEALTH_RECT_SIZE + rectLinePadding;
    addTextElement(parent, quotify("-4"), DEFAULT_FONT_SIZE, VALUE_LEFT, x, y + textYCorrection, x
        + width
        - leftSquareX, LINE_HEIGHT);
    encodeHealthRectPair(parent, rightBoxBound - (HEALTH_RECT_SIZE + padding), y, 0, HealthLevelType.FOUR);
    y += HEALTH_RECT_SIZE + rectLinePadding;
    addTextElement(parent, quotify("Incapacitated"), DEFAULT_FONT_SIZE, VALUE_LEFT, x, y + textYCorrection, x
        + width
        - leftSquareX, LINE_HEIGHT);
    encodeHealthRectPair(parent, rightBoxBound - (HEALTH_RECT_SIZE + padding), y, 0, HealthLevelType.INCAPACITATED);
    return height;
  }

  private void encodeHealthRectPair(Element parent, int x, int y, int value, HealthLevelType healthLevelType) {
    String printWhenExpression = ParameterUtilities.parameterString(healthLevelType.getId()) + methodCall("intValue") + "<=" + value;
    addHealthRect(parent, VALUE_COLOR_GRAY, x, y, printWhenExpression);
    addHealthRect(parent, VALUE_COLOR_BLACK, x, y, "!(" + printWhenExpression + ")"); //$NON-NLS-1$ //$NON-NLS-2$
  }

  protected void addHealthRect(Element parent, String foreColor, int x, int y, String printWhenExpression) {
    Element shapeElement = parent.addElement(TAG_RECTANGLE);
    Element reportElement = addReportElement(shapeElement, x, y, HEALTH_RECT_SIZE, HEALTH_RECT_SIZE);
    reportElement.addAttribute(ATTRIB_FORECOLOR, foreColor);
    reportElement.addAttribute(ATTRIB_BACKCOLOR, VALUE_COLOR_WHITE);
    reportElement.addAttribute(ATTRIB_POSITION_TYPE, VALUE_FIX_RELATIVE_TO_TOP);
    reportElement.addElement(TAG_PRINT_WHEN_EXPRESSION).addCDATA(printWhenExpression);
    addGraphicsElement(shapeElement, VALUE_1_POINT);
  }

  private int encodeSoak(Element parent, int y, int x, int width) {
    int originalY = y;
    y += 2;
    addTextElement(parent, quotify("Soak"), 8, VALUE_CENTER, x, y, width, LINE_HEIGHT);
    y += LINE_HEIGHT;
    x += 4;
    int keyWidth = 10;
    int textElementSpacing = 4;
    int textElementWidth = width / 3 - textElementSpacing;
    int valueWidth = textElementWidth - 15;
    addLabeledTextParameter(parent, "B", "BashingSoak", y, x, keyWidth, valueWidth);
    addLabeledTextParameter(
        parent,
        "L",
        "LethalSoak",
        y,
        x + textElementWidth + textElementSpacing,
        keyWidth,
        valueWidth);
    addLabeledTextParameter(
        parent,
        "A",
        "AggravatedSoak",
        y,
        x + 2 * textElementWidth + 2 * textElementSpacing,
        keyWidth,
        valueWidth);
    y += 10;
    addThinLineElement(parent, new Rectangle(x + 10, y, valueWidth, 0));
    addThinLineElement(parent, new Rectangle(x + textElementWidth + textElementSpacing + 10, y, valueWidth, 0));
    addThinLineElement(parent, new Rectangle(x + 2 * textElementWidth + 2 * textElementSpacing + 10, y, valueWidth, 0));
    return y - originalY;
  }

  protected int encodeVirtues(Element parent, int y, int x, int width, int lineSpacing) {
    int originalY = y;
    addLinedHeader(parent, "Virtues", y, x, width, lineSpacing, lineSpacing);
    int virtueWidth = width / 2 - 10;
    int secondColumnX = x + width / 2 + 20;
    x += INDENT / 2;
    y += HEADER_SIZE;
    addTextElement(parent, quotify("Compassion"), 8, VALUE_LEFT, x, y, virtueWidth, LINE_HEIGHT);
    addTextElement(parent, quotify("Temperance"), 8, VALUE_LEFT, secondColumnX, y, virtueWidth, LINE_HEIGHT);
    y += LINE_HEIGHT;
    int traitHeight = traitEncoder.encodeWithoutText(parent, "Compassion", x, y, 5, false);
    traitEncoder.encodeWithoutText(parent, "Temperance", secondColumnX, y, 5, false);
    y += traitHeight;
    y += LINE_HEIGHT / 4;
    addRectangleRow(parent, x, y, 5, traitEncoder.getDotSize(), TraitEncoder.DOT_SPACING);
    addRectangleRow(parent, secondColumnX, y, 5, traitEncoder.getDotSize(), TraitEncoder.DOT_SPACING);
    y += traitHeight;
    y += LINE_HEIGHT / 2;
    addTextElement(parent, quotify("Conviction"), 8, VALUE_LEFT, x, y, virtueWidth, LINE_HEIGHT);
    addTextElement(parent, quotify("Valor"), 8, VALUE_LEFT, secondColumnX, y, virtueWidth, LINE_HEIGHT);
    y += LINE_HEIGHT;
    traitEncoder.encodeWithoutText(parent, "Conviction", x, y, 5, false);
    traitEncoder.encodeWithoutText(parent, "Valor", secondColumnX, y, 5, false);
    y += traitHeight;
    y += LINE_HEIGHT / 4;
    addRectangleRow(parent, x, y, 5, traitEncoder.getDotSize(), TraitEncoder.DOT_SPACING);
    addRectangleRow(parent, secondColumnX, y, 5, traitEncoder.getDotSize(), TraitEncoder.DOT_SPACING);
    return y + traitHeight - originalY;
  }

  protected int encodeWillpower(Element parent, int y, int x, int width, int lineSpacing) {
    addLinedHeader(parent, "Willpower", y, x, width, lineSpacing, lineSpacing);
    int yOffset = HEADER_SIZE + LINE_HEIGHT / 2;
    yOffset += willpowerEncoder.encodeWillpower(parent, y + yOffset, x, width, 4, 10);
    yOffset += LINE_HEIGHT;
    yOffset += addShapeRow(parent, TAG_RECTANGLE, y + yOffset, x, 10, width, 4);
    return yOffset + LINE_HEIGHT / 2;
  }

  protected int encodeCopyright(Element parent, int x, int y, int width) {
    int originalY = y;
    y += LINE_HEIGHT / 2;
    Element anathemaTextElement = TextEncoding.addTextFieldElement(parent);
    Element anathemaReportElement = addTextContent(
        anathemaTextElement,
        quotify("Created with Anathema (http://anathema.sf.net) " + '\u00A9' + "2005"),
        6,
        VALUE_RIGHT,
        new Rectangle(x, y, width, 8));
    anathemaReportElement.addAttribute(ATTRIB_FORECOLOR, VALUE_COLOR_DARK_GRAY);
    try {
      addHyperlink(anathemaTextElement, new URL("http://anathema.sf.net"));
    }
    catch (MalformedURLException e) {
      // nothing to do
    }
    y += 8;
    Element whitewolfReportElement = addTextElement(
        parent,
        quotify("Exalted " + '\u00A9' + "2005 by White Wolf, Inc."),
        6,
        VALUE_RIGHT,
        x,
        y,
        width,
        8);
    whitewolfReportElement.addAttribute(ATTRIB_FORECOLOR, VALUE_COLOR_DARK_GRAY);
    y += 8;
    return y - originalY;
  }

  protected int encodeEssence(Element parent, int x, int y, int width, int lineSpacing) {
    int originalY = y;
    addLinedHeader(parent, "Essence", y, x, width, lineSpacing, lineSpacing);
    y += HEADER_SIZE;
    int betweenLineSpacing = LINE_HEIGHT / 3 - 1;
    y += betweenLineSpacing;
    x += INDENT;
    int dotSize = 12;
    int dotCount = 5;
    int essenceWidth = width - 2 * INDENT;
    int dotSpacing = (essenceWidth - dotCount * dotSize) / (dotCount - 1);
    y += traitEncoder.encodeWithoutText(
        parent,
        OtherTraitType.Essence.getId(),
        x,
        y,
        dotCount,
        dotSize,
        dotSpacing,
        false);
    y += betweenLineSpacing;
    addTextElement(parent, quotify("Personal"), DEFAULT_FONT_SIZE, VALUE_LEFT, x, y, essenceWidth / 2, LINE_HEIGHT);
    addThinLineElement(parent, new Rectangle(x + essenceWidth / 2, y + 11, essenceWidth / 4, 0));
    addTextElement(
        parent,
        quotify("|"), DEFAULT_FONT_SIZE, VALUE_CENTER, x + (essenceWidth * 6) / 8, y, essenceWidth / 8, LINE_HEIGHT); //$NON-NLS-1$//$NON-NLS-2$
    String personalPool = ParameterUtilities.parameterString(ICharacterReportConstants.ESSENCE_PERSONAL_POOL);
    addTextElement(
        parent,
        personalPool + methodCall("toString"), DEFAULT_FONT_SIZE, VALUE_RIGHT, x + (essenceWidth * 7) / 8, y, essenceWidth / 8, LINE_HEIGHT); //$NON-NLS-1$//$NON-NLS-2$
    Element personalLineReportElement = addThinLineElement(parent, new Rectangle(x + (essenceWidth * 7) / 8, y
        + LINE_HEIGHT, essenceWidth / 8, 0));
    String personalPrintWhenExpression = personalPool + "==null"; //$NON-NLS-1$
    personalLineReportElement.addElement(TAG_PRINT_WHEN_EXPRESSION).addCDATA(personalPrintWhenExpression); //$NON-NLS-1$ //$NON-NLS-2$
    y += LINE_HEIGHT;
    y += betweenLineSpacing;
    addTextElement(parent, quotify("Peripheral"), DEFAULT_FONT_SIZE, VALUE_LEFT, x, y, essenceWidth / 2, LINE_HEIGHT);
    addThinLineElement(parent, new Rectangle(x + essenceWidth / 2, y + 11, essenceWidth / 4, 0));
    addTextElement(
        parent,
        quotify("|"), DEFAULT_FONT_SIZE, VALUE_CENTER, x + (essenceWidth * 6) / 8, y, essenceWidth / 8, LINE_HEIGHT); //$NON-NLS-1$//$NON-NLS-2$
    String peripheralPool = ParameterUtilities.parameterString(ICharacterReportConstants.ESSENCE_PERIPHERAL_POOL);
    addTextElement(
        parent,
        peripheralPool + methodCall("toString"), DEFAULT_FONT_SIZE, VALUE_RIGHT, x + (essenceWidth * 7) / 8, y, essenceWidth / 8, LINE_HEIGHT); //$NON-NLS-1$//$NON-NLS-2$
    Element peripheralLineReportElement = addThinLineElement(parent, new Rectangle(x + (essenceWidth * 7) / 8, y
        + LINE_HEIGHT, essenceWidth / 8, 0));
    String peripheralPrintWhenExpression = peripheralPool + "==null"; //$NON-NLS-1$
    peripheralLineReportElement.addElement(TAG_PRINT_WHEN_EXPRESSION).addCDATA(peripheralPrintWhenExpression); //$NON-NLS-1$ //$NON-NLS-2$
    y += LINE_HEIGHT;
    y += betweenLineSpacing;
    addTextElement(parent, quotify("Committed"), DEFAULT_FONT_SIZE, VALUE_LEFT, x, y, essenceWidth / 2, LINE_HEIGHT);
    addThinLineElement(parent, new Rectangle(x + essenceWidth / 2, y + 11, essenceWidth / 2, 0));
    y += LINE_HEIGHT;
    return y - originalY;
  }
}