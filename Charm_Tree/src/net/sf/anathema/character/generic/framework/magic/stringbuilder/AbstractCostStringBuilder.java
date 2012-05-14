package net.sf.anathema.character.generic.framework.magic.stringbuilder;

import net.sf.anathema.character.generic.magic.general.ICost;
import net.sf.anathema.lib.resources.IResources;

import static net.sf.anathema.lib.lang.StringUtilities.EMPTY_STRING;
import static net.sf.anathema.lib.lang.StringUtilities.isNullOrEmpty;

public abstract class AbstractCostStringBuilder<T extends ICost> implements ICostStringBuilder<T> {

  private final IResources resources;
  private final String singularKey;
  private final String pluralKey;

  public AbstractCostStringBuilder(IResources resources, String singularKey, String pluralKey) {
    this.resources = resources;
    this.singularKey = singularKey;
    this.pluralKey = pluralKey;
  }

  @Override
  public String getCostString(T cost) {
    String value = cost.getCost();
    String text = cost.getText();
    String costString = EMPTY_STRING;
    try {
      int intValue = Integer.parseInt(value);
      if (intValue != 0) {
        costString = getQualifiedValueString(cost);
      }
    }
    catch (NumberFormatException e) {
      if (!isNullOrEmpty(value)) {
        costString = value;
      }
    }
    if (!isNullOrEmpty(text)) {
      costString = costString.concat(IMagicTooltipStringBuilder.Space + text);
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