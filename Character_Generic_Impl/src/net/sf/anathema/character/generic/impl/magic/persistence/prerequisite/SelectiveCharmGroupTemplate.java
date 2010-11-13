package net.sf.anathema.character.generic.impl.magic.persistence.prerequisite;

import net.disy.commons.core.util.Ensure;

public class SelectiveCharmGroupTemplate {

  private final int threshold;
  private final String[] groupCharmIds;

  public SelectiveCharmGroupTemplate(String[] groupCharmIds, int threshold) {
    Ensure.ensureTrue("Number of charms must be greater than threshold.", threshold < groupCharmIds.length); //$NON-NLS-1$
    Ensure.ensureTrue("No selective charm groups without charms.", groupCharmIds.length > 0); //$NON-NLS-1$
    this.groupCharmIds = groupCharmIds;
    this.threshold = threshold;
  }

  public String[] getGroupCharmIds() {
    return groupCharmIds;
  }

  public int getThreshold() {
    return threshold;
  }
}