package net.sf.anathema.exaltedengine.numericquality;

import net.sf.anathema.characterengine.command.Command;
import net.sf.anathema.characterengine.persona.Qualities;
import net.sf.anathema.characterengine.quality.Type;

public class SetMinimumValue implements Command {
  private final Type type;
  private final int value;

  public SetMinimumValue(Type type, int value) {
    this.type = type;
    this.value = value;
  }

  @Override
  public void execute(Qualities qualities) {
    qualities.defineRule(type, new MinimumQualityValue(value));
  }
}