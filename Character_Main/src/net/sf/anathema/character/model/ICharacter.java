package net.sf.anathema.character.model;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.impl.magic.SpellException;
import net.sf.anathema.character.generic.magic.charms.CharmException;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.framework.itemdata.model.IItemData;

public interface ICharacter extends IItemData {

  boolean hasStatistics();

  ICharacterDescription getDescription();

  ICharacterStatistics getStatistics();

  ICharacterStatistics createCharacterStatistics(ICharacterTemplate template, ICharacterGenerics generics) throws CharmException, SpellException;
}