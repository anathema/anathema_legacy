package net.sf.anathema.development.reporting.encoder.voidstate.subreports.anima;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.text.font.FontStyle;
import net.sf.anathema.development.reporting.encoder.voidstate.columns.IOneColumnEncoder;
import net.sf.anathema.framework.styledtext.model.ITextFormat;
import net.sf.anathema.framework.styledtext.model.ITextPart;
import net.sf.anathema.framework.styledtext.presentation.TextFormat;
import net.sf.anathema.framework.styledtext.presentation.TextPart;

import org.dom4j.Element;

public class VoidstateLunarAnimaPageEncoder extends AbstractVoidstateAnimaPageEncoder {
  public VoidstateLunarAnimaPageEncoder(IOneColumnEncoder columnEncoder) {
    super(columnEncoder);
  }

  @Override
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
    addTextElement(bandElement, quotify("Forms Locked"), FONT_SIZE, VALUE_LEFT, parentRect.x + 100, parentRect.y
        + yOffset, 30, LINE_HEIGHT);
    addTextElement(bandElement, quotify("Stealth Difficulty"), FONT_SIZE, VALUE_LEFT, parentRect.x + 125, parentRect.y
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
    addNormalLineElement(bandElement, new Rectangle(parentRect.x + 24, parentRect.y + yOffset, 0, tableHeight));
    addNormalLineElement(bandElement, new Rectangle(parentRect.x + 105, parentRect.y + yOffset, 0, tableHeight));
    addNormalLineElement(bandElement, new Rectangle(parentRect.x + 117, parentRect.y + yOffset, 0, tableHeight));
    fillAnimaTable(bandElement, parentRect, yOffset);
  }

  @Override
  protected void fillAnimaTable(Element parent, Rectangle bounds, int yOffset) {
    yOffset += addAnimaTableRow(parent, bounds, bounds.y + yOffset, "1-3", "Caste Mark glitters", false, "Normal", true);
    yOffset += addAnimaTableRow(
        parent,
        bounds,
        bounds.y + yOffset,
        "4-7",
        "Caste Mark & tattoos burn",
        false,
        "+2",
        true);
    yOffset += addAnimaTableRow(parent, bounds, bounds.y + yOffset, "8-10", "Coruscant Aura", true, "Impossible", true);
    yOffset += addAnimaTableRow(
        parent,
        bounds,
        bounds.y + yOffset,
        "11-15",
        "Brilliant Bonfire",
        true,
        "Impossible",
        true);
    yOffset += addAnimaTableRow(parent, bounds, bounds.y + yOffset, "16+", "Totemic Aura", true, "Impossible", false);
  }

  private int addAnimaTableRow(
      Element bandElement,
      Rectangle parentRect,
      int y,
      String range,
      String anima,
      boolean formLock,
      String stealth,
      boolean drawLine) {
    addMoteRangeTextElement(bandElement, parentRect, y, range);
    addFlareLevelTextElement(bandElement, parentRect, y, anima);
    addStealthModifierTextElement(bandElement, parentRect, y, stealth);
    if (formLock) {
      addCaret(bandElement, new Point(109,y),FONT_SIZE-1,LINE_HEIGHT);
    }
    if (drawLine) {
      drawTableRowBottomLine(bandElement, parentRect, y);
    }
    return LINE_HEIGHT;
  }
  
  @Override
  protected final void addFlareLevelTextElement(Element bandElement, Rectangle parentRect, int y, String anima) {
    addTextElement(
        bandElement,
        quotify(anima),
        FONT_SIZE - 1,
        VALUE_LEFT,
        parentRect.x + 27,
        y,
        parentRect.width,
        LINE_HEIGHT);
  }

  @Override
  protected List<ITextPart[]> getAnimaEffectTextParts() {
    ITextFormat smallTextFormat = new TextFormat(FontStyle.PLAIN, false, FONT_SIZE - 1);
    List<ITextPart[]> textParts = new ArrayList<ITextPart[]>();
    textParts.add(new ITextPart[] { new TextPart(
        "Cause Caste Mark and tattoos to glow brightly (1 mote)",
        smallTextFormat) });
    textParts.add(new ITextPart[] { new TextPart("Cause Tell to become unmistakeable (1 mote)", smallTextFormat) });
    textParts.add(new ITextPart[] { new TextPart(
        "Cause anima to glow bright enough to read by (1 mote)",
        smallTextFormat) });
    textParts.add(new ITextPart[] { new TextPart("Know precise time and moon phase (1 mote)", smallTextFormat) });
    return textParts;
  }

  public String getGroupName() {
    return "VoidStateSiderealAnimaSubreport";
  }
}