package net.sf.anathema.character.generic.impl.magic.persistence;

import net.sf.anathema.character.generic.impl.magic.Charm;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.exception.PersistenceException;

public interface ICharmCache {

  public abstract ICharm[] getCharms(final CharacterType type, boolean powerCombat) throws PersistenceException;

  public abstract ICharm[] getMartialArtsCharms(boolean powerCombat) throws PersistenceException;

  public Charm searchCharm(String charmId);
}