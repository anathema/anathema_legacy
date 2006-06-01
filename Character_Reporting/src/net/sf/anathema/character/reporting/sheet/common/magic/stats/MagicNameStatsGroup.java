package net.sf.anathema.character.reporting.sheet.common.magic.stats;

import net.sf.anathema.character.reporting.sheet.util.statstable.AbstractNameStatsGroup;
import net.sf.anathema.lib.resources.IResources;

public class MagicNameStatsGroup extends AbstractNameStatsGroup<IMagicStats> {

  public MagicNameStatsGroup(IResources resources) {
    super(resources);
  }

  @Override
  public Float[] getColumnWeights() {
    return new Float[] { 6.0f };
  }

  @Override
  protected String getHeaderResourceKey() {
    return "Sheet.Magic.Name"; //$NON-NLS-1$
  }

  @Override
  protected String getResourceBase() {
    return ""; //$NON-NLS-1$
  }
}