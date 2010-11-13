package net.sf.anathema.character.generic.magic.general;

public interface ICostList {

  public ICost getEssenceCost();

  public IHealthCost getHealthCost();

  public ICost getWillpowerCost();

  public ICost getXPCost();
}