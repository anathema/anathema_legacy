package net.sf.anathema.character.equipment.impl.character.model.stats;

import net.disy.commons.core.util.ArrayUtilities;
import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.character.equipment.impl.creation.model.WeaponTag;
import net.sf.anathema.character.equipment.item.model.ICollectionFactory;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.lib.util.IIdentificate;

import java.util.List;

public abstract class AbstractWeaponStats extends AbstractCombatStats implements IWeaponStats {

  private int accuracy;
  private int damage;
  private String damageTypeString;
  private Integer defence;
  private Integer range;
  private Integer rate;
  private int speed;
  private boolean inflictsNoDamage;
  private final List<String> tags;
  private int minimumDamage;

  public AbstractWeaponStats(ICollectionFactory collectionFactory) {
    this.tags = collectionFactory.createList();
  }

  @Override
  public int getAccuracy() {
    return accuracy;
  }

  @Override
  public int getDamage() {
    return damage;
  }

  @Override
  public int getMinimumDamage() {
    return minimumDamage;
  }

  @Override
  public ITraitType getDamageTraitType() {
    return AttributeType.Strength;
  }

  @Override
  public HealthType getDamageType() {
    return HealthType.valueOf(damageTypeString);
  }

  @Override
  public Integer getDefence() {
    return defence;
  }

  @Override
  public Integer getRange() {
    return range;
  }

  @Override
  public Integer getRate() {
    return rate;
  }

  @Override
  public int getSpeed() {
    return speed;
  }

  @Override
  public IIdentificate[] getTags() {
    String[] tagIds = tags.toArray(new String[tags.size()]);
    return ArrayUtilities.transform(tagIds, WeaponTag.class, new ITransformer<String, WeaponTag>() {
      @Override
      public WeaponTag transform(String input) {
        return WeaponTag.valueOf(input);
      }
    });
  }

  protected final boolean hasTag(WeaponTag tag) {
    return tags.contains(tag.getId());
  }

  @Override
  public boolean inflictsNoDamage() {
    return inflictsNoDamage;
  }

  public void setAccuracy(int accuracy) {
    this.accuracy = accuracy;
  }

  public void setDamage(int damage) {
    this.damage = damage;
  }

  public void setMinimumDamage(int damage) {
    this.minimumDamage = damage;
  }

  public void setDamageType(HealthType damageType) {
    this.damageTypeString = damageType.name();
  }

  public void setDefence(Integer defence) {
    this.defence = defence;
  }

  public void setInflictsNoDamage(boolean inflictsNoDamage) {
    this.inflictsNoDamage = inflictsNoDamage;
  }

  public void setRange(Integer range) {
    this.range = range;
  }

  public void setRate(Integer rate) {
    this.rate = rate;
  }

  public void setSpeed(int speed) {
    this.speed = speed;
  }

  public void addTag(IIdentificate tag) {
    tags.add(tag.getId());
  }

  @Override
  public boolean isRangedCombat() {
    return hasTag(WeaponTag.BowType) || hasTag(WeaponTag.FlameType) || hasTag(WeaponTag.Thrown);
  }

  @Override
  public String getId() {
    return getName().getId();
  }
}