package net.sf.anathema.character.generic.magic;

import net.sf.anathema.character.generic.util.IStats;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;

public interface IMagicStats extends IStats, Comparable<IMagicStats> {

  @Override
  IIdentificate getName();

  String getCostString(IResources resources);

  String getGroupName(IResources resources);

  String getType(IResources resources);

  String getDurationString(IResources resources);

  String getSourceString(IResources resources);

  String[] getDetailStrings(IResources resources);

  String getNameString(IResources resources);
}