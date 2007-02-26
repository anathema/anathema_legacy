package net.sf.anathema.character.equipment.impl.character.model.stats;

import java.util.List;

import net.disy.commons.core.util.ArrayUtilities;
import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.character.equipment.impl.creation.model.WeaponTag;
import net.sf.anathema.character.equipment.item.model.ICollectionFactory;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.lib.util.IIdentificate;

public abstract class AbstractWeaponStats extends AbstractStats implements IWeaponStats {

  private int accuracy;
  private int damage;
  private HealthType damageType;
  private String damageTypeString;
  private Integer defence;
  private Integer range;
  private Integer rate;
  private int speed;
  private boolean inflictsNoDamage;
  private final List<String> tags;

  public AbstractWeaponStats(ICollectionFactory collectionFactory) {
    this.tags = collectionFactory.createList();
  }

  /** Used for conversion of old-type stats with WeaponStats to new-type String storage. */
  protected AbstractWeaponStats(ICollectionFactory collectionFactory, AbstractWeaponStats stats) {
    this.tags = collectionFactory.createList();
    setName(stats.getName());
    setAccuracy(stats.getAccuracy());
    setDamage(stats.getDamage());
    setDamageType(stats.getDamageType());
    setDefence(stats.getDefence());
    setRange(stats.getRange());
    setRate(stats.getRate());
    setSpeed(stats.getSpeed());
    setInflictsNoDamage(stats.inflictsNoDamage());
    for (IIdentificate tag : stats.getTags()) {
      addTag(tag);
    }
  }

  public int getAccuracy() {
    return accuracy;
  }

  public int getDamage() {
    return damage;
  }

  public ITraitType getDamageTraitType() {
    return AttributeType.Strength;
  }

  // TODO: During transition, both type must be supported.
  public HealthType getDamageType() {
    if (damageType != null) {
      System.err.println("Old-type weapon stats encountered. If you see this message after starting this version of Anathema for the second time, please notify the development team."); //$NON-NLS-1$
      return damageType;
    }
    return HealthType.valueOf(damageTypeString);
  }

  public Integer getDefence() {
    return defence;
  }

  public Integer getRange() {
    return range;
  }

  public Integer getRate() {
    return rate;
  }

  public int getSpeed() {
    return speed;
  }

  // TODO: During transition, both types must be supported.
  public IIdentificate[] getTags() {
    try {
      String[] tagIds = tags.toArray(new String[tags.size()]);
      return ArrayUtilities.transform(tagIds, WeaponTag.class, new ITransformer<String, WeaponTag>() {
        public WeaponTag transform(String input) {
          return WeaponTag.valueOf(input);
        }
      });
    }
    catch (ArrayStoreException e) {
      System.err.println("Old-type weapon stats encountered. If you see this message after starting this version of Anathema for the second time, please notify the development team."); //$NON-NLS-1$
      return tags.toArray(new WeaponTag[tags.size()]);
    }
  }

  public ITraitType getTraitType() {
    return getCombatTrait();
  }

  protected abstract ITraitType getCombatTrait();

  protected final boolean hasTag(WeaponTag tag) {
    return tags.contains(tag.getId()) || tags.contains(tag);
  }

  public boolean inflictsNoDamage() {
    return inflictsNoDamage;
  }

  public void setAccuracy(int accuracy) {
    this.accuracy = accuracy;
  }

  public void setDamage(int damage) {
    this.damage = damage;
  }

  public void setDamageType(HealthType damageType) {
    this.damageType=null;
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

  public boolean isRangedCombat() {
    return hasTag(WeaponTag.BowType) || hasTag(WeaponTag.FlameType) || hasTag(WeaponTag.Thrown);
  }
}