package net.sf.anathema.character.lunar.reporting.content.stats.knacks;

import net.sf.anathema.character.generic.util.IStats;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.Identified;

public interface IKnackStats extends IStats {

  @Override
  Identified getName();

  String getGroupName(IResources resources);

  String getSourceString(IResources resources);
  
  String[] getDetailString(IResources resources);

  String getNameString(IResources resources);
}
