package net.sf.anathema.character.lunar.reporting.knacks;

import net.sf.anathema.character.generic.util.IStats;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;

public interface IKnackStats extends IStats {

  public IIdentificate getName();

  public String getGroupName(IResources resources);

  public String getSourceString(IResources resources);
  
  public String[] getDetailString(IResources resources);

  public String getNameString(IResources resources);
}