package net.sf.anathema.character.main.magic.parser.charms;

import net.sf.anathema.character.main.magic.model.charm.CharmData;

public interface ICharmEntryData {

  CharmData getCoreData();

  String getName();

  int getPage();
}