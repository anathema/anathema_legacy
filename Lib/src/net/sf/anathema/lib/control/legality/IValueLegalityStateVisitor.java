package net.sf.anathema.lib.control.legality;

public interface IValueLegalityStateVisitor {

  public void visitLow(ValueLegalityState visitedState);

  public void visitLowered(ValueLegalityState state);

  public void visitOkay(ValueLegalityState visitedState);

  public void visitIncreased(ValueLegalityState state);

  public void visitHigh(ValueLegalityState visitedState);
}