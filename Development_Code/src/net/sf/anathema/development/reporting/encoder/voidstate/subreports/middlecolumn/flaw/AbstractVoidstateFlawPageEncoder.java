package net.sf.anathema.development.reporting.encoder.voidstate.subreports.middlecolumn.flaw;

import java.awt.Point;
import java.awt.Rectangle;

import net.sf.anathema.development.reporting.encoder.AbstractCharacterSheetPageEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.columns.IOneColumnEncoder;

public abstract class AbstractVoidstateFlawPageEncoder extends AbstractCharacterSheetPageEncoder {

  public static Rectangle calculateBounds(IOneColumnEncoder columnEncoder) {
    Rectangle boxRectangle = columnEncoder.createOneColumnBoxBoundsWithTitle(0, 10, new Point(0, 0));
    return boxRectangle;
  }
}
