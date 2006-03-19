package net.sf.anathema.character.model.traits.essence;

public interface IEssencePoolConfiguration {

  public String getPersonalPool();

  public String getPeripheralPool();

  public boolean isEssenceUser();

  public boolean hasPeripheralPool();

  public void addPoolListener(IPoolValueListener listener);
}