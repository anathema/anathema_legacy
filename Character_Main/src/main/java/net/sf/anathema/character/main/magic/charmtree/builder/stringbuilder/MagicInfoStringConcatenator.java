package net.sf.anathema.character.main.magic.charmtree.builder.stringbuilder;

import com.google.common.base.Strings;
import net.sf.anathema.lib.resources.Resources;

public class MagicInfoStringConcatenator {

  private final Resources resources;

  public MagicInfoStringConcatenator(Resources resources) {
    this.resources = resources;
  }

  public String buildCostString(String essenceCost, String willpowerCost, String healthCost, String xpCost) {
    StringBuilder builder = new StringBuilder();
    appendToString(builder, essenceCost);
    appendToString(builder, willpowerCost);
    appendToString(builder, healthCost);
    appendToString(builder, xpCost);
    if (builder.length() == 0) {
      return resources.getString("Charms.Cost.None");
    }
    return builder.toString();
  }

  private void appendToString(StringBuilder builder, String appendix) {
    if (Strings.isNullOrEmpty(appendix)) {
      return;
    }
    if (builder.length() == 0) {
      builder.append(appendix);
      return;
    }
    builder.append(", ");
    builder.append(appendix);
  }
}
