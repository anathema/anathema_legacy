package net.sf.anathema.lib.control.legality;

import java.awt.Font;


public class LegalityFontProvider {

  public static final int NO_BONUS = Font.PLAIN;
  public static final int BONUS = Font.BOLD;

  public int getFontStyle(ValueLegalityState state) {
    final int[] style = new int[1];
    state.accept(new IValueLegalityStateVisitor() {
      public void visitLow(ValueLegalityState visitedState) {
        style[0] = NO_BONUS;
      }

      public void visitLowered(ValueLegalityState visitedState) {
        style[0] = NO_BONUS;
      }

      public void visitOkay(ValueLegalityState visitedState) {
        style[0] = NO_BONUS;
      }

      public void visitIncreased(ValueLegalityState visitedState) {
        style[0] = BONUS;
      }

      public void visitHigh(ValueLegalityState visitedState) {
        style[0] = NO_BONUS;
      }
    });
    return style[0];
  }
}