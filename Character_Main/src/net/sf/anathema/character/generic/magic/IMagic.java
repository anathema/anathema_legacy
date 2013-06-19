package net.sf.anathema.character.generic.magic;

import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.main.hero.Hero;

public interface IMagic extends IMagicData {

  void accept(IMagicVisitor visitor);

  boolean isFavored(Hero hero, IGenericTraitCollection traitCollection);
}