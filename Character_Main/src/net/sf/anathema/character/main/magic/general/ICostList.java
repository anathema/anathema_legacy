package net.sf.anathema.character.main.magic.general;

public interface ICostList {

  ICost getEssenceCost();

  IHealthCost getHealthCost();

  ICost getWillpowerCost();

  ICost getXPCost();
}