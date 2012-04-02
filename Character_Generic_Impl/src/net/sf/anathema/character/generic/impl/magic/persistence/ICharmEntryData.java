package net.sf.anathema.character.generic.impl.magic.persistence;

import net.sf.anathema.character.generic.magic.ICharmData;
import net.sf.anathema.character.generic.rules.IExaltedEdition;

public interface ICharmEntryData {

  ICharmData getCoreData();

  String getName();

  int getPage();
}