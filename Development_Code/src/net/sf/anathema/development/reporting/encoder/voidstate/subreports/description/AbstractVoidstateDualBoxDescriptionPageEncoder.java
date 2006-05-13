package net.sf.anathema.development.reporting.encoder.voidstate.subreports.description;

import java.awt.Rectangle;

import net.sf.anathema.character.reporting.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.development.reporting.encoder.voidstate.format.VoidstateBasicsEncoder;

import org.dom4j.Element;

public abstract class AbstractVoidstateDualBoxDescriptionPageEncoder extends AbstractVoidstateDescriptionPageEncoder implements
    IVoidStateFormatConstants {

  private final VoidstateBasicsEncoder basicsEncoder;

  public AbstractVoidstateDualBoxDescriptionPageEncoder(VoidstateBasicsEncoder basicsEncoder) {
    this.basicsEncoder = basicsEncoder;
  }

  public int encodeBand(Element bandElement) {
    Rectangle boxRectangle = calculateExtents(basicsEncoder);
    int firstBoxWidth = (boxRectangle.width * 3) / 5;
    Rectangle firstBoxBounds = new Rectangle(boxRectangle.x, boxRectangle.y, firstBoxWidth, boxRectangle.height);
    int collegeX = boxRectangle.x + firstBoxWidth + PADDING;
    int collegeWidth = boxRectangle.width - collegeX;
    Rectangle secondBoxBounds = new Rectangle(collegeX, boxRectangle.y, collegeWidth, boxRectangle.height);
    encodeSecondBox(bandElement, secondBoxBounds);
    encodeFirstBox(bandElement, firstBoxBounds);
    return boxRectangle.height;
  }

  public VoidstateBasicsEncoder getBasicsEncoder() {
    return basicsEncoder;
  }

  protected abstract void encodeFirstBox(Element bandElement, Rectangle firstBoxBounds);

  protected abstract void encodeSecondBox(Element bandElement, Rectangle secondBoxBounds);
}