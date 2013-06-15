package net.sf.anathema.character.generic.magic;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;

public interface IMagic extends IMagicData {

  void accept(IMagicVisitor visitor);

  boolean isFavored(IBasicCharacterData basicCharacter, IGenericTraitCollection traitCollection);
}