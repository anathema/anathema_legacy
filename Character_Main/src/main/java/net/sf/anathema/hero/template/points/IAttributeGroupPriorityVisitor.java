package net.sf.anathema.hero.template.points;

public interface IAttributeGroupPriorityVisitor {

  void acceptPrimary();

  void acceptSecondary();

  void acceptTertiary();
}