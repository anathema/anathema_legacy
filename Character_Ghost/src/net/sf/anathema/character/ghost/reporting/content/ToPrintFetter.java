package net.sf.anathema.character.ghost.reporting.content;

import com.google.common.base.Function;
import net.sf.anathema.character.ghost.fetters.model.Fetter;
import net.sf.anathema.character.reporting.pdf.content.general.NamedValue;

public class ToPrintFetter implements Function<Fetter, NamedValue> {
  @Override
  public NamedValue apply(Fetter input) {
    return new PrintFetter(input);
  }
}
