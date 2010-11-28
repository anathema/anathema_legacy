package net.sf.anathema.character.generic.framework.magic.stringbuilder;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.generic.magic.general.ICost;
import net.sf.anathema.lib.lang.AnathemaStringUtilities;
import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractCostStringBuilder<T extends ICost> implements ICostStringBuilder<T> {

  private final IResources resources;
  private final String singularKey;
  private final String pluralKey;

  public AbstractCostStringBuilder(IResources resources, String singularKey, String pluralKey) {
    this.resources = resources;
    this.singularKey = singularKey;
    this.pluralKey = pluralKey;
  }

  public String getCostString(T cost) {
    String value = cost.getCost();
    String text = cost.getText();
    String costString = AnathemaStringUtilities.EMPTY_STRING;
    try {
      int intValue = Integer.parseInt(value);
      if (intValue != 0) {
        costString = getQualifiedValueString(cost);
      }
    }
    catch (NumberFormatException e) {
      if (!StringUtilities.isNullOrEmpty(value)) {
        costString = value;
      }
    }
    if (!StringUtilities.isNullOrEmpty(text)) {
      costString = costString.concat(IMagicStringBuilderConstants.Space + text);
    }
    return costString;
  }

  protected final IResources getResources() {
    return resources;
  }

  protected String getSingularKey() {
    return singularKey;
  }

  protected String getPluralKey() {
    return pluralKey;
  }

  protected abstract String getQualifiedValueString(T cost);
}