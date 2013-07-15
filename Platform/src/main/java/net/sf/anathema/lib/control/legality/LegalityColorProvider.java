package net.sf.anathema.lib.control.legality;

import net.sf.anathema.framework.ui.RGBColor;

public class LegalityColorProvider {

  public static final RGBColor COLOR_HIGH = RGBColor.Red;
  public static final RGBColor COLOR_LOW = new RGBColor(165, 0, 165);
  public static final RGBColor COLOR_OKAY = RGBColor.Black;

  public RGBColor getTextColor(ValueLegalityState state) {
    final RGBColor[] color = new RGBColor[1];
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