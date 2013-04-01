package net.sf.anathema.campaign.load.selection;

public interface ILegalityProvider<V> {

  boolean isLegal(V value);
}