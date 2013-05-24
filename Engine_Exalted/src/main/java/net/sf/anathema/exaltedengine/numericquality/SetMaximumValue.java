package net.sf.anathema.exaltedengine.numericquality;

import net.sf.anathema.characterengine.command.Command;
import net.sf.anathema.characterengine.persona.Qualities;
import net.sf.anathema.characterengine.quality.Type;

public class SetMaximumValue implements Command {
  private final Type type;
  private final int maximum;

  public SetMaximumValue(Type type, int maximum) {
    this.type = type;
    this.maximum = maximum;
  }

  @Override
  public void execute(Qualities qualities) {
    qualities.defineRule(type, new MaximumQualityValue(maximum));
  }
}
