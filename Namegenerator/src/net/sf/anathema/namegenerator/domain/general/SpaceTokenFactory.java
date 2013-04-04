package net.sf.anathema.namegenerator.domain.general;

public class SpaceTokenFactory implements INameTokenFactory {

  @Override
  public String createToken() {
    return " ";
  }
}