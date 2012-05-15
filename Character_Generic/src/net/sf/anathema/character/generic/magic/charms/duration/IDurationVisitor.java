package net.sf.anathema.character.generic.magic.charms.duration;

public interface IDurationVisitor {

  void visitSimpleDuration(SimpleDuration visitedDuration);

  void visitQualifiedAmountDuration(QualifiedAmountDuration visitedDuration);

  void acceptUntilEventDuration(UntilEventDuration visitedDuration);
}