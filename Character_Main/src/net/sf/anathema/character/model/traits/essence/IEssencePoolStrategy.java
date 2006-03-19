package net.sf.anathema.character.model.traits.essence;


public interface IEssencePoolStrategy {

  public int getExtendedPersonalPool();

  public int getExtendedPeripheralPool();

  public int getStandardPersonalPool();

  public int getStandardPeripheralPool();
  
  public void addPoolListener(IPoolValueListener listener);
}