package net.sf.anathema.character.generic.magic.charms;

import net.sf.anathema.character.generic.magic.charms.type.CharmType;

public interface ICharmTypeVisitor {

  public void visitSimple(CharmType visitedType);

  public void visitExtraAction(CharmType visitedType);

  public void visitReflexive(CharmType visitedType);

  public void visitSupplemental(CharmType visitedType);

  public void visitSpecial(CharmType visitedType);
}