package net.sf.anathema.character.generic.magic.charms;

import net.sf.anathema.character.generic.magic.charms.type.CharmType;

public interface ICharmTypeVisitor {

  void visitSimple(CharmType visitedType);

  void visitExtraAction(CharmType visitedType);

  void visitReflexive(CharmType visitedType);

  void visitSupplemental(CharmType visitedType);

  void visitPermanent(CharmType visitedType);

  void visitSpecial(CharmType visitedType);
}