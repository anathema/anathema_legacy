package net.sf.anathema.character.main.magic.model.magic;

public interface ICostList {

  ICost getEssenceCost();

  IHealthCost getHealthCost();

  ICost getWillpowerCost();

  ICost getXPCost();
}