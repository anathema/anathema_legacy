package net.sf.anathema.character.main.magic.model.cost;

public interface ICostList {

  Cost getEssenceCost();

  IHealthCost getHealthCost();

  Cost getWillpowerCost();

  Cost getXPCost();
}