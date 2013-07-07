package net.sf.anathema.character.main.magic;

import net.sf.anathema.character.main.util.IStats;
import net.sf.anathema.lib.resources.Resources;
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