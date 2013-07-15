package net.sf.anathema.character.main.template.points;

public interface IAttributeGroupPriorityVisitor {

  void acceptPrimary();

  void acceptSecondary();

  void acceptTertiary();
}