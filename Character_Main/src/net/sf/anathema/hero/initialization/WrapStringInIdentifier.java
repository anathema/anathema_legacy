package net.sf.anathema.hero.initialization;

import com.google.common.base.Function;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public class WrapStringInIdentifier implements Function<String, Identifier> {
  @Override
  public Identifier apply(String input) {
    return new SimpleIdentifier(input);
  }
}