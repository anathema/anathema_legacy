package net.sf.anathema.character.reporting.pdf.content.essence.pools;

public interface PoolRow {

  String getLabel();

  String getCapacity();

  String getCommitted();

  String getAvailable();

  boolean isCommitmentEnabled();

  boolean isDisplayable();
}
