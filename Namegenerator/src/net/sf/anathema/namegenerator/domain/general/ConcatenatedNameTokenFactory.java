package net.sf.anathema.namegenerator.domain.general;

import net.sf.anathema.lib.lang.AnathemaStringUtilities;

public class ConcatenatedNameTokenFactory implements INameTokenFactory {

  public INameTokenFactory[] tokenFactories;

  public ConcatenatedNameTokenFactory(INameTokenFactory[] tokenFactories) {
    this.tokenFactories = tokenFactories;
  }

  public String createToken() {
    StringBuilder token = new StringBuilder();
    boolean isFirstToken = true;
    for (INameTokenFactory factory : tokenFactories) {
      String tokenPart = factory.createToken();
      if (isFirstToken) {
        tokenPart = AnathemaStringUtilities.capitalizeFirstCharacter(tokenPart);
        isFirstToken = false;
      }
      token.append(tokenPart);
    }
    return token.toString();
  }
}