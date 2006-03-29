package net.sf.anathema.development.reporting.encoder;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.graphics.font.FontStyle;
import net.sf.anathema.development.reporting.util.AbstractJasperEncoder;
import net.sf.anathema.framework.styledtext.model.ITextPart;
import net.sf.anathema.framework.styledtext.presentation.TextFormat;
import net.sf.anathema.framework.styledtext.presentation.TextPart;

import org.dom4j.Element;

public abstract class AbstractCharacterSheetPageEncoder extends AbstractJasperEncoder implements
    ICharacterSheetPageEncoder {

  protected void addTextWithCaret(
      Element parent,
      Rectangle parentRect,
      ITextPart[] text,
      int yOffset,
      int fontSize,
      int lineHeight) {
    ITextPart caretPart = new TextPart("\\u00A8", new TextFormat("Symbol"));
    ITextPart blankPart = new TextPart(" ", new TextFormat(FontStyle.PLAIN, false));
    List<ITextPart> partList = new ArrayList<ITextPart>();
    partList.add(caretPart);
    partList.add(blankPart);
    for (ITextPart part : text) {
      partList.add(part);
    }
    addStyledTextElement(
        parent,
        partList.toArray(new ITextPart[partList.size()]),
        fontSize - 1,
        VALUE_LEFT,
        parentRect.x,
        parentRect.y + yOffset,
        parentRect.width,
        lineHeight);
  }

  protected final Element addCaret(Element parent, Point point, int fontSize, int lineHeight) {
    ITextPart[] caretPart = new ITextPart[] { new TextPart("\\u00A8", new TextFormat("Symbol")) };
    return addStyledTextElement(parent, caretPart, fontSize - 1, VALUE_LEFT, point.x, point.y, 10, lineHeight);
  }

  public String getPrintWhenExpression() {
    return "true";
  }
}