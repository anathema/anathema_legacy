package net.sf.anathema.character.generic.magic.general;

public interface ICostList {

  ICost getEssenceCost();

  IHealthCost getHealthCost();

  ICost getWillpowerCost();

  ICost getXPCost();
}