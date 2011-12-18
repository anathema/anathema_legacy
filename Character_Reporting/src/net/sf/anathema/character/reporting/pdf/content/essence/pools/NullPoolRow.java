package net.sf.anathema.character.reporting.pdf.content.essence.pools;

public class NullPoolRow implements PoolRow {
  @Override
  public String getLabel() {
    return " ";
  }

  @Override
  public String getCapacity() {
    return " ";
  }

  @Override
  public String getCommitted() {
    return " ";
  }

  @Override
  public String getAvailable() {
    return " ";
  }

  @Override
  public boolean isCommitmentEnabled() {
    return true;
  }

  @Override
  public boolean isDisplayable() {
    return true;
  }
}
