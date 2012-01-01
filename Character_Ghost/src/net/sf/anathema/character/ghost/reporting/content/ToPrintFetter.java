package net.sf.anathema.character.ghost.reporting.content;

import com.google.common.base.Function;
import net.sf.anathema.character.ghost.fetters.model.Fetter;
import net.sf.anathema.character.reporting.pdf.content.general.NamedValue;

import javax.annotation.Nullable;

public class ToPrintFetter implements Function<Fetter, NamedValue> {
  @Override
  public NamedValue apply(@Nullable final Fetter input) {
    return new PrintFetter(input);
  }
}
