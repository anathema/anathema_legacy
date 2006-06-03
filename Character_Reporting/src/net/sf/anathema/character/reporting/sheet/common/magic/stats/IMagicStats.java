package net.sf.anathema.character.reporting.sheet.common.magic.stats;

import net.sf.anathema.character.generic.util.IStats;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;

public interface IMagicStats extends IStats {

  public IIdentificate getName();

  public String getCostString(IResources resources);

  public String getGroupName(IResources resources);

  public String getType(IResources resources);

  public String getDurationString(IResources resources);

  public String getSourceString(IResources resources);

  public String[] getDetailKeys();
}