package net.sf.anathema.character.ghost.reporting.content;

import net.sf.anathema.character.ghost.fetters.model.Fetter;
import net.sf.anathema.character.reporting.pdf.content.general.NamedValue;

public class PrintFetter implements NamedValue {
  private final Fetter input;

  public PrintFetter(Fetter input) {
    this.input = input;
  }

  @Override
  public String getLabel() {
    return input.getName();
  }

  @Override
  public int getValue() {
    return input.getCurrentValue();
  }
}
