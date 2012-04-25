package net.sf.anathema.namegenerator.domain.general;

import org.apache.commons.lang3.StringUtils;

public class ConcatenatedNameTokenFactory implements INameTokenFactory {

  public INameTokenFactory[] tokenFactories;

  public ConcatenatedNameTokenFactory(INameTokenFactory[] tokenFactories) {
    this.tokenFactories = tokenFactories;
  }

  @Override
  public String createToken() {
    StringBuilder token = new StringBuilder();
    boolean isFirstToken = true;
    for (INameTokenFactory factory : tokenFactories) {
      String tokenPart = factory.createToken();
      if (isFirstToken) {
        tokenPart = StringUtils.capitalize(tokenPart);
        isFirstToken = false;
      }
      token.append(tokenPart);
    }
    return token.toString();
  }
}