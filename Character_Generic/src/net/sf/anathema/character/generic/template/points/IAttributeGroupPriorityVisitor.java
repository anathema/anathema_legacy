package net.sf.anathema.character.generic.template.points;

public interface IAttributeGroupPriorityVisitor {

  void acceptPrimary();

  void acceptSecondary();

  void acceptTertiary();
}