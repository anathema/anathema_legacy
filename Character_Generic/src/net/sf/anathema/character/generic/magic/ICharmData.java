package net.sf.anathema.character.generic.magic;

import java.util.Set;

import net.sf.anathema.character.generic.magic.charms.CharmType;
import net.sf.anathema.character.generic.magic.charms.Duration;
import net.sf.anathema.character.generic.magic.charms.ICharmAttributeRequirement;
import net.sf.anathema.character.generic.magic.charms.IComboRestrictions;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.type.CharacterType;

public interface ICharmData extends IMagicData {
  public CharacterType getCharacterType();

  public CharmType getCharmType();

  public Duration getDuration();

  public IGenericTrait getEssence();

  public IGenericTrait[] getPrerequisites();

  public String getGroupId();

  public IComboRestrictions getComboRules();

  public ICharmAttributeRequirement[] getAttributeRequirements();

  public Set<ICharm> getParentCharms();
}