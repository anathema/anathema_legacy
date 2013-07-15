package net.sf.anathema.character.main.magic.model.charm;

import net.sf.anathema.character.main.magic.model.charm.type.CharmType;

public interface ICharmTypeVisitor {

  void visitSimple(CharmType visitedType);

  void visitExtraAction(CharmType visitedType);

  void visitReflexive(CharmType visitedType);

  void visitSupplemental(CharmType visitedType);

  void visitPermanent(CharmType visitedType);

  void visitSpecial(CharmType visitedType);
}