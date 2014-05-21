package net.sf.anathema.hero.charms.sheet.content;

import net.sf.anathema.character.framework.library.IStats;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.lib.util.Identifier;

public interface IMagicStats extends IStats, Comparable<IMagicStats> {

  @Override
  Identifier getName();

  String getCostString(Resources resources);

  String getGroupName(Resources resources);

  String getType(Resources resources);

  String getDurationString(Resources resources);

  String getSourceString(Resources resources);

  String[] getDetailStrings(Resources resources);

  String getNameString(Resources resources);
}