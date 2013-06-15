package net.sf.anathema.character.generic.magic;

import net.sf.anathema.character.generic.util.IStats;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identified;

public interface IMagicStats extends IStats, Comparable<IMagicStats> {

  @Override
  Identified getName();

  String getCostString(Resources resources);

  String getGroupName(Resources resources);

  String getType(Resources resources);

  String getDurationString(Resources resources);

  String getSourceString(Resources resources);

  String[] getDetailStrings(Resources resources);

  String getNameString(Resources resources);
}