package net.sf.anathema.character.model;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.impl.magic.SpellException;
import net.sf.anathema.character.generic.magic.charms.CharmException;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.framework.itemdata.model.IItemData;

public interface ICharacter extends IItemData {

  public boolean hasStatistics();

  public ICharacterDescription getDescription();

  public ICharacterStatistics getStatistics();

  public ICharacterStatistics createCharacterStatistics(
      ICharacterTemplate template,
      ICharacterGenerics generics,
      IExaltedRuleSet rules) throws CharmException, SpellException;
}