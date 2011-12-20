package net.sf.anathema.character.reporting.pdf.content.combat;

import net.sf.anathema.character.reporting.pdf.content.AbstractSubContent;
import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractCombatStatsContent extends AbstractSubContent {

  protected AbstractCombatStatsContent(IResources resources) {
    super(resources);
  }

  @Override
  public String getHeaderKey() {
    return "Combat"; //$NON-NLS-1$
  }

  @Override
  public boolean hasContent() {
    return true;
  }
}
