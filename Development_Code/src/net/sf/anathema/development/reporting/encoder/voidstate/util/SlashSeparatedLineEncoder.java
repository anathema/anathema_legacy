package net.sf.anathema.development.reporting.encoder.voidstate.util;

import java.awt.Rectangle;

import net.sf.anathema.character.reporting.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.development.reporting.util.AbstractJasperEncoder;
import net.sf.anathema.framework.reporting.jasper.IJasperXmlConstants;

import org.dom4j.Element;

public class SlashSeparatedLineEncoder extends AbstractJasperEncoder implements
    IVoidStateFormatConstants,
    IJasperXmlConstants {

  public void encodeSlashSeparatedLines(Element parent, int x, int y, int lineHeight, int numberOfSlashes, int width) {
    Rectangle lineRectangle = new Rectangle(x, y + lineHeight, width, 0);
    addThinLineElement(parent, lineRectangle);
    for (int slashes = 0; slashes < numberOfSlashes; slashes++) {
      lineRectangle.x += lineRectangle.width + 1;
      addTextElement(parent, quotify("/"), FONT_SIZE - 1, VALUE_LEFT, lineRectangle.x - 1, y + 1, width, lineHeight);
      lineRectangle.x += 1;
      addThinLineElement(parent, lineRectangle);
    }
  }
}