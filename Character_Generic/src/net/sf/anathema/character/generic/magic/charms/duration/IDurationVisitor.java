package net.sf.anathema.character.generic.magic.charms.duration;

public interface IDurationVisitor {

  public void visitSimpleDuration(SimpleDuration visitedDuration);

  public void visitQualifiedAmountDuration(QualifiedAmountDuration visitedDuration);

  public void acceptUntilEventDuration(UntilEventDuration visitedDuration);
}