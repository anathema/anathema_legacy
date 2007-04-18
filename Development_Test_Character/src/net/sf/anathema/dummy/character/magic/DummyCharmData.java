package net.sf.anathema.dummy.character.magic;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.ICharmData;
import net.sf.anathema.character.generic.magic.charms.ICharmAttribute;
import net.sf.anathema.character.generic.magic.charms.ICharmAttributeRequirement;
import net.sf.anathema.character.generic.magic.charms.IComboRestrictions;
import net.sf.anathema.character.generic.magic.charms.duration.SimpleDuration;
import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.character.generic.magic.charms.type.ICharmTypeModel;
import net.sf.anathema.character.generic.magic.general.ICostList;
import net.sf.anathema.character.generic.rules.IExaltedSourceBook;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.types.ValuedTraitType;
import net.sf.anathema.character.generic.type.CharacterType;

public class DummyCharmData implements ICharmData {
  private final Set<ICharm> parents = new HashSet<ICharm>();

  public CharacterType getCharacterType() {
    return null;
  }

  public CharmType getCharmType() {
    return null;
  }

  public SimpleDuration getDuration() {
    return null;
  }

  public IGenericTrait getEssence() {
    return new ValuedTraitType(null, 1);
  }

  public IGenericTrait[] getPrerequisites() {
    return new IGenericTrait[0];
  }

  public String getGroupId() {
    return null;
  }

  public IComboRestrictions getComboRules() {
    return null;
  }

  public ICharmAttributeRequirement[] getAttributeRequirements() {
    return null;
  }

  public void setParentCharms(ICharm[] parentcharms) {
    Collections.addAll(this.parents, parentcharms);
  }

  public Set<ICharm> getParentCharms() {
    return parents;
  }

  public IExaltedSourceBook getSource() {
    return null;
  }

  public ICostList getTemporaryCost() {
    return null;
  }

  public String getId() {
    return null;
  }

  public ICharmTypeModel getCharmTypeModel() {
    return null;
  }

  public ICharmAttribute[] getAttributes() {
    return new ICharmAttribute[0];
  }
}