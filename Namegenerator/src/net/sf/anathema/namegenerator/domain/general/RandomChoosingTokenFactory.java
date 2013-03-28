package net.sf.anathema.namegenerator.domain.general;

import com.google.common.base.Preconditions;
import net.sf.anathema.lib.util.RandomUtilities;

public class RandomChoosingTokenFactory implements INameTokenFactory {

  private String[] tokens;

  public RandomChoosingTokenFactory(String[] tokens) {
    Preconditions.checkArgument(tokens.length > 0, "At least one token must be given."); //$NON-NLS-1$
    this.tokens = tokens;
  }

  @Override
  public final String createToken() {
    return RandomUtilities.choose(tokens);
  }
}