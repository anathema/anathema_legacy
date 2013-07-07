package net.sf.anathema.character.main.magic.persistence;

import net.sf.anathema.character.main.magic.ICharmData;

public interface ICharmEntryData {

  ICharmData getCoreData();

  String getName();

  int getPage();
}