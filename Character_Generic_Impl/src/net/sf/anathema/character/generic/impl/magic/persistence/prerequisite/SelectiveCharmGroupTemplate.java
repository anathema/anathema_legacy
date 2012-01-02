package net.sf.anathema.character.generic.impl.magic.persistence.prerequisite;

import net.disy.commons.core.util.Ensure;

public class SelectiveCharmGroupTemplate {

  private final int threshold;
  private final String[] groupCharmIds;
  private final String label;

  public SelectiveCharmGroupTemplate(String[] groupCharmIds, int threshold, String label) {
    Ensure.ensureTrue("No selective charm groups without charms.", groupCharmIds.length > 0); //$NON-NLS-1$
    this.groupCharmIds = groupCharmIds;
    this.threshold = threshold;
    this.label = label;
  }

  public String[] getGroupCharmIds() {
    return groupCharmIds;
  }

  public int getThreshold() {
    return threshold;
  }

  public String getLabel() {
    return label;
  }
}