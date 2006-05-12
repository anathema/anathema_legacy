package net.sf.anathema.character.generic.impl.magic.persistence;

import java.util.List;

import net.sf.anathema.character.generic.magic.ICharmData;
import net.sf.anathema.character.generic.magic.charms.ICharmAttribute;
import net.sf.anathema.character.generic.rules.IExaltedEdition;

public interface ICharmEntryData {

  public IExaltedEdition getEdition();

  public List<ICharmAttribute> getKeywords();

  public ICharmData getCoreData();
}