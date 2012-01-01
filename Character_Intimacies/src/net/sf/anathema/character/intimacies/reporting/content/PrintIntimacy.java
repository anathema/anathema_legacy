package net.sf.anathema.character.intimacies.reporting.content;

import net.sf.anathema.character.intimacies.model.IIntimacy;
import net.sf.anathema.character.reporting.pdf.content.general.NamedValue;

public class PrintIntimacy implements NamedValue {
  private final IIntimacy intimacy;

  public PrintIntimacy(IIntimacy intimacy) {
    this.intimacy = intimacy;
  }

  @Override
  public String getLabel() {
    return intimacy.getName();
  }

  @Override
  public int getValue() {
    return intimacy.getTrait().getCurrentValue();
  }
}
