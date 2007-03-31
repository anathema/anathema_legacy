package net.sf.anathema.character.generic.magic;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;

public interface IMagic extends IMagicData {

  public void accept(IMagicVisitor visitor);

  public boolean isFavored(IBasicCharacterData basicCharacter, IGenericTraitCollection traitCollection);
}