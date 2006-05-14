package net.sf.anathema.development.reporting.encoder.voidstate.subreports.anima;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;

import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.development.reporting.encoder.AbstractCharacterSheetPageEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.columns.IOneColumnEncoder;
import net.sf.anathema.framework.styledtext.model.ITextPart;

import org.dom4j.Element;

public abstract class AbstractVoidstateAnimaPageEncoder extends AbstractCharacterSheetPageEncoder implements
    IVoidStateFormatConstants {

  private final IOneColumnEncoder columnEncoder;

  public AbstractVoidstateAnimaPageEncoder(IOneColumnEncoder columnEncoder) {
    this.columnEncoder = columnEncoder;
  }

  public int encodeBand(Element bandElement) {
    Rectangle bounds = calculateBounds();
    int yOffset = columnEncoder.addCentralHeader(bandElement, bounds, bounds.y, "Anima Effects");
    List<ITextPart[]> animaEffectTextParts = getAnimaEffectTextParts();
    for (ITextPart[] partArray : animaEffectTextParts) {
      addTextWithCaret(bandElement, bounds, partArray, yOffset, FONT_SIZE, LINE_HEIGHT);
      yOffset += LINE_HEIGHT;
    }
    addCaret(bandElement, new Point(bounds.x, bounds.y + yOffset), FONT_SIZE, LINE_HEIGHT);
    columnEncoder.addEmptyLines(bandElement, new Point(bounds.x + 7, bounds.y + yOffset), bounds.width - 7, 1);
    yOffset += LINE_HEIGHT;
    int numberEmptyLines = 6 - (animaEffectTextParts.size());
    columnEncoder.addEmptyLines(bandElement, new Point(bounds.x, bounds.y + yOffset), bounds.width, numberEmptyLines);
    yOffset += LINE_HEIGHT * numberEmptyLines + 2;
    addAnimaTable(bandElement, bounds, yOffset);
    return bounds.height;
  }

  protected void addAnimaTable(Element bandElement, Rectangle parentRect, int yOffset) {
    addTextElement(
        bandElement,
        quotify("Motes spent"),
        FONT_SIZE,
        VALUE_LEFT,
        parentRect.x + 4,
        parentRect.y + yOffset,
        20,
        LINE_HEIGHT);
    addTextElement(bandElement, quotify("Banner Flare"), FONT_SIZE, VALUE_LEFT, parentRect.x + 40, parentRect.y
        + yOffset, 25, LINE_HEIGHT);
    addTextElement(bandElement, quotify("Stealth Difficulty"), FONT_SIZE, VALUE_LEFT, parentRect.x + 120, parentRect.y
        + yOffset, 30, LINE_HEIGHT);
    yOffset += LINE_HEIGHT * 2 - 3;
    int tableHeight = LINE_HEIGHT * 5 + 1;
    addShape(
        bandElement,
        TAG_RECTANGLE,
        VALUE_COLOR_WHITE,
        parentRect.x,
        parentRect.y + yOffset,
        parentRect.width,
        tableHeight,
        "true");
    addNormalLineElement(bandElement, new Rectangle(parentRect.x + 30, parentRect.y + yOffset, 0, tableHeight));
    addNormalLineElement(bandElement, new Rectangle(parentRect.x + 110, parentRect.y + yOffset, 0, tableHeight));
    fillAnimaTable(bandElement, parentRect, yOffset);
  }

  protected abstract void fillAnimaTable(Element parent, Rectangle bounds, int yOffset);

  protected abstract List<ITextPart[]> getAnimaEffectTextParts();

  public static Rectangle calculateBounds() {
    return new Rectangle(0, 0, 155, 165);
  }

  protected int addAnimaTableRow(
      Element bandElement,
      Rectangle parentRect,
      int y,
      String range,
      String anima,
      String stealth,
      boolean drawLine) {
    addMoteRangeTextElement(bandElement, parentRect, y, range);
    addFlareLevelTextElement(bandElement, parentRect, y, anima);
    addStealthModifierTextElement(bandElement, parentRect, y, stealth);
    if (drawLine) {
      drawTableRowBottomLine(bandElement, parentRect, y);
    }
    return LINE_HEIGHT;
  }

  protected final void drawTableRowBottomLine(Element bandElement, Rectangle parentRect, int y) {
    addNormalLineElement(bandElement, new Rectangle(parentRect.x, y + LINE_HEIGHT, parentRect.width - 1, 0));
  }

  protected final void addStealthModifierTextElement(Element bandElement, Rectangle parentRect, int y, String stealth) {
    addTextElement(
        bandElement,
        quotify(stealth),
        FONT_SIZE - 1,
        VALUE_LEFT,
        parentRect.x + 120,
        y,
        parentRect.width,
        LINE_HEIGHT);
  }

  protected void addFlareLevelTextElement(Element bandElement, Rectangle parentRect, int y, String anima) {
    addTextElement(
        bandElement,
        quotify(anima),
        FONT_SIZE - 1,
        VALUE_LEFT,
        parentRect.x + 40,
        y,
        parentRect.width,
        LINE_HEIGHT);
  }

  protected Element addMoteRangeTextElement(Element bandElement, Rectangle parentRect, int y, String range) {
    return addTextElement(
        bandElement,
        quotify(range),
        FONT_SIZE - 1,
        VALUE_LEFT,
        parentRect.x + 4,
        y,
        parentRect.width,
        LINE_HEIGHT);
  }
}