package net.sf.anathema.hero.initialization;

import com.google.common.base.Function;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

import javax.annotation.Nullable;

public class WrapStringInIdentifier implements Function<String, Identifier> {
  @Override
  public Identifier apply(@Nullable String input) {
    return new SimpleIdentifier(input);
  }
}