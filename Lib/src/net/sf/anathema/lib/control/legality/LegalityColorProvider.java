package net.sf.anathema.lib.control.legality;

import java.awt.Color;

public class LegalityColorProvider {

  public static final Color COLOR_HIGH = Color.RED;
  public static final Color COLOR_LOW = new Color(165, 0, 165);
  public static final Color COLOR_OKAY = Color.BLACK;

  public Color getTextColor(ValueLegalityState state) {
    final Color[] color = new Color[1];
    state.accept(new IValueLegalityStateVisitor() {
      @Override
      public void visitLow(ValueLegalityState visitedState) {
        color[0] = COLOR_LOW;
      }

      @Override
      public void visitLowered(ValueLegalityState visitedState) {
        color[0] = COLOR_LOW;
      }

      @Override
      public void visitOkay(ValueLegalityState visitedState) {
        color[0] = COLOR_OKAY;
      }

      @Override
      public void visitIncreased(ValueLegalityState visitedState) {
        color[0] = COLOR_OKAY;
      }

      @Override
      public void visitHigh(ValueLegalityState visitedState) {
        color[0] = COLOR_HIGH;
      }
    });
    return color[0];
  }
}