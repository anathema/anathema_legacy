package net.sf.anathema.development.reporting.encoder.voidstate.subreports.description;

import java.awt.Point;
import java.awt.Rectangle;

import net.sf.anathema.development.reporting.encoder.AbstractCharacterSheetPageEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.format.VoidstateBasicsEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.pages.VoidstateSecondPageEncoder;

public abstract class AbstractVoidstateDescriptionPageEncoder extends AbstractCharacterSheetPageEncoder {
  public static Rectangle calculateExtents(VoidstateBasicsEncoder basicsEncoder) {
    Rectangle box = basicsEncoder.createCorrectedTwoColumnBoxBoundsWithoutTitle(
        VoidstateSecondPageEncoder.LOWEST_LINE_COUNT,
        new Point(0, 0));
    box.height = box.height + 1;
    return box;
  }
}