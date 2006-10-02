package net.sf.anathema.character.generic.equipment.weapon;

public interface IEquipmentStatsVisitor {

  public void visitArmourStats(IArmourStats armourStats);

  public void visitWeaponStats(IWeaponStats weaponStats);

  public void visitShieldStats(IShieldStats shieldStats);
}