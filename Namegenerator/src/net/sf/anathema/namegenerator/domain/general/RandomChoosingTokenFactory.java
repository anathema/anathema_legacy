package net.sf.anathema.namegenerator.domain.general;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.lib.random.RandomUtilities;

public class RandomChoosingTokenFactory implements INameTokenFactory {

  private String[] tokens;

  public RandomChoosingTokenFactory(String[] tokens) {
    Ensure.ensureArgumentTrue("At least one token must be given.", tokens.length > 0); //$NON-NLS-1$
    this.tokens = tokens;
  }

  public final String createToken() {
    return RandomUtilities.choose(tokens);
  }

  public String[] getAvailableTokens() {
    return tokens;
  }
}