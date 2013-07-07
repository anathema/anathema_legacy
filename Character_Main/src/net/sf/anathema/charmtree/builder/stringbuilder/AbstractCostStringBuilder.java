package net.sf.anathema.charmtree.builder.stringbuilder;

import com.google.common.base.Strings;
import net.sf.anathema.character.main.magic.general.ICost;
import net.sf.anathema.lib.gui.TooltipBuilder;
import net.sf.anathema.lib.resources.Resources;

import static net.sf.anathema.lib.lang.StringUtilities.EMPTY_STRING;

public abstract class AbstractCostStringBuilder<T extends ICost> implements ICostStringBuilder<T> {

  private final Resources resources;
  private final String singularKey;
  private final String pluralKey;

  public AbstractCostStringBuilder(Resources resources, String singularKey, String pluralKey) {
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
    } catch (NumberFormatException e) {
      if (!Strings.isNullOrEmpty(value)) {
        costString = value;
      }
    }
    if (!Strings.isNullOrEmpty(text)) {
      costString = costString.concat(TooltipBuilder.Space + text);
    }
    return costString;
  }

  protected final Resources getResources() {
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