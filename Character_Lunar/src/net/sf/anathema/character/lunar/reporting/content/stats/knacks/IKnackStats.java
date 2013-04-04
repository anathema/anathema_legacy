package net.sf.anathema.character.lunar.reporting.content.stats.knacks;

import net.sf.anathema.character.generic.util.IStats;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identified;

public interface IKnackStats extends IStats {

  @Override
  Identified getName();

  String getGroupName(Resources resources);

  String getSourceString(Resources resources);
  
  String[] getDetailString(Resources resources);

  String getNameString(Resources resources);
}
