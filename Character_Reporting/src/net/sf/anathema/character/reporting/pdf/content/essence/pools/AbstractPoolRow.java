package net.sf.anathema.character.reporting.pdf.content.essence.pools;

public abstract class AbstractPoolRow implements PoolRow {

  private int getAvailableValue() {
    int committed = getCommittedValue() != null ? getCommittedValue() : 0;
    return getCapacityValue() - committed;
  }

  @Override
  public String getCapacity() {
    return Integer.toString(getCapacityValue()) + " m";
  }

  @Override
  public String getCommitted() {
    return isCommitmentEnabled() ? String.valueOf(getCommittedValue()) + " m" : " ";
  }

  @Override
  public String getAvailable() {
    int availableValue = getAvailableValue();
    return Integer.toString(availableValue) + " m";
  }

  @Override
  public boolean isCommitmentEnabled() {
    return getCommittedValue() != null;
  }

  @Override
  public boolean isDisplayable() {
    return getCapacityValue() > 0;
  }

  protected abstract int getCapacityValue();

  protected abstract Integer getCommittedValue();
}
