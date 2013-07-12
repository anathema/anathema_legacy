package net.sf.anathema.hero.spiritual.sheet.essence.content.pools;

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
