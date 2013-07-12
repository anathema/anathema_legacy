package net.sf.anathema.hero.spiritual.sheet.essence.content.pools;

public interface PoolRow {

  String getLabel();

  String getCapacity();

  String getCommitted();

  String getAvailable();

  boolean isCommitmentEnabled();

  boolean isDisplayable();
}
