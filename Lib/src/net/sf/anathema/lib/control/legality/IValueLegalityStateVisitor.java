package net.sf.anathema.lib.control.legality;

public interface IValueLegalityStateVisitor {

  void visitLow(ValueLegalityState visitedState);

  void visitLowered(ValueLegalityState state);

  void visitOkay(ValueLegalityState visitedState);

  void visitIncreased(ValueLegalityState state);

  void visitHigh(ValueLegalityState visitedState);
}