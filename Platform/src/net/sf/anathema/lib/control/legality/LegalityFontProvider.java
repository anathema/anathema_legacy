package net.sf.anathema.lib.control.legality;

public class LegalityFontProvider {

  public static final FontStyle NO_BONUS = FontStyle.Plain;
  public static final FontStyle BONUS = FontStyle.Bold;

  public FontStyle getFontStyle(ValueLegalityState state) {
    final FontStyle[] style = new FontStyle[1];
    state.accept(new IValueLegalityStateVisitor() {
      @Override
      public void visitLow(ValueLegalityState visitedState) {
        style[0] = NO_BONUS;
      }

      @Override
      public void visitLowered(ValueLegalityState visitedState) {
        style[0] = NO_BONUS;
      }

      @Override
      public void visitOkay(ValueLegalityState visitedState) {
        style[0] = NO_BONUS;
      }

      @Override
      public void visitIncreased(ValueLegalityState visitedState) {
        style[0] = BONUS;
      }

      @Override
      public void visitHigh(ValueLegalityState visitedState) {
        style[0] = NO_BONUS;
      }
    });
    return style[0];
  }
}