package net.sf.anathema.character.generic.impl.magic.persistence;

import net.sf.anathema.character.generic.magic.ICharmData;
import net.sf.anathema.character.generic.rules.IExaltedEdition;

public interface ICharmEntryData {

  public IExaltedEdition getEdition();

  public ICharmData getCoreData();

  public String getName();

  public int getPage();
}