package net.sf.anathema.character.magic.basic.cost;

public interface ICostList {

  Cost getEssenceCost();

  IHealthCost getHealthCost();

  Cost getWillpowerCost();

  Cost getXPCost();
}