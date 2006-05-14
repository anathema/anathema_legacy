package net.sf.anathema.development.reporting.encoder.voidstate.subreports.combat.sequence;

import java.awt.Point;
import java.awt.Rectangle;

import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.development.reporting.encoder.AbstractCharacterSheetPageEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.format.VoidstateBasicsEncoder;

public abstract class AbstractVoidstateSequencePageEncoder extends AbstractCharacterSheetPageEncoder implements
    IVoidStateFormatConstants {

  public static Rectangle calculateBounds(VoidstateBasicsEncoder basicsEncoder) {
    return basicsEncoder.createTwoColumnBoxBoundsWithoutTitle(0, 2, new Point(0, 0));
  }
}
