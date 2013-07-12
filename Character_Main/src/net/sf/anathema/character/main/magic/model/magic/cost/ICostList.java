package net.sf.anathema.character.main.magic.model.magic.cost;

public interface ICostList {

  ICost getEssenceCost();

  IHealthCost getHealthCost();

  ICost getWillpowerCost();

  ICost getXPCost();
}