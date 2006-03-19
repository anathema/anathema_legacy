package net.sf.anathema.character.generic.template.points;

public interface IAttributeGroupPriorityVisitor {

  public void acceptPrimary();

  public void acceptSecondary();

  public void acceptTertiary();
}