package net.sf.anathema.character.main.magic.parser;

import com.google.common.base.Preconditions;

public class SelectiveCharmGroupTemplate {

  private final int threshold;
  private final String[] groupCharmIds;
  private final String label;

  public SelectiveCharmGroupTemplate(String[] groupCharmIds, int threshold, String label) {
    Preconditions.checkArgument(groupCharmIds.length > 0, "No selective charm groups without charms.");
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