package net.sf.anathema.character.generic.framework.magic.stringbuilder;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.lib.resources.IResources;

public class MagicInfoStringConcatenator {

  private final IResources resources;

  public MagicInfoStringConcatenator(IResources resources) {
    this.resources = resources;
  }

  public String buildCostString(String essenceCost, String willpowerCost, String healthCost, String xpCost) {
    StringBuilder builder = new StringBuilder();
    appendToString(builder, essenceCost);
    appendToString(builder, willpowerCost);
    appendToString(builder, healthCost);
    appendToString(builder, xpCost);
    if (builder.length() == 0) {
      return resources.getString("Charms.Cost.None"); //$NON-NLS-1$
    }
    return builder.toString();
  }

  private void appendToString(StringBuilder builder, String appendix) {
    if (StringUtilities.isNullOrEmpty(appendix)) {
      return;
    }
    if (builder.length() == 0) {
      builder.append(appendix);
      return;
    }
    builder.append(", "); //$NON-NLS-1$
    builder.append(appendix);
  }
}
