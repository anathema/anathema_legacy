package net.sf.anathema.development.reporting.encoder.voidstate.subreports.attribute;

import java.awt.Point;
import java.awt.Rectangle;

import net.sf.anathema.character.reporting.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.development.reporting.encoder.AbstractCharacterSheetPageEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.format.VoidstateBasicsEncoder;

public abstract class AbstractVoidstateAttributePageEncoder extends AbstractCharacterSheetPageEncoder implements
    IVoidStateFormatConstants {

  public static Rectangle calculateExtents(VoidstateBasicsEncoder basicsEncoder) {
    return basicsEncoder.createTwoColumnBoxBoundsWithoutTitle(9, 1, new Point(0, 0));
  }
}
