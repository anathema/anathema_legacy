package net.sf.anathema.hero.intimacies.sheet.content;

import net.sf.anathema.character.reporting.pdf.content.general.NamedValue;
import net.sf.anathema.hero.intimacies.model.Intimacy;

public class PrintIntimacy implements NamedValue {
  private final Intimacy intimacy;

  public PrintIntimacy(Intimacy intimacy) {
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
