package net.sf.anathema.campaign.load.selection;

public class LenientLegalityProvider<V> implements ILegalityProvider<V> {

  @Override
  public boolean isLegal(Object value) {
    return true;
  }
}