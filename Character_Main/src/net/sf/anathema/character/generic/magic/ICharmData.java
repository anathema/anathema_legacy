package net.sf.anathema.character.generic.magic;

import net.sf.anathema.character.generic.magic.charms.ICharmAttribute;
import net.sf.anathema.character.generic.magic.charms.IComboRestrictions;
import net.sf.anathema.character.generic.magic.charms.IndirectCharmRequirement;
import net.sf.anathema.character.generic.magic.charms.duration.IDuration;
import net.sf.anathema.character.generic.magic.charms.type.ICharmTypeModel;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.util.Identified;
import net.sf.anathema.lib.util.Identifier;

import java.util.List;
import java.util.Set;

public interface ICharmData extends IMagicData {
  Identified FORM_ATTRIBUTE = new Identifier("Form");
  Identified MERGED_ATTRIBUTE = new Identifier("Merged");
  Identified ALLOWS_CELESTIAL_ATTRIBUTE = new Identifier("AllowsCelestial");
  Identified UNRESTRICTED_ATTRIBUTE = new Identifier("Unrestricted");
  Identified NO_STYLE_ATTRIBUTE = new Identifier("NoStyle");
  Identified NATIVE = new Identifier("Native");
  String FAVORED_CASTE_PREFIX = "FavoredCaste.";

  ICharacterType getCharacterType();

  IDuration getDuration();

  IGenericTrait getEssence();

  IGenericTrait[] getPrerequisites();

  ITraitType getPrimaryTraitType();

  String getGroupId();

  IComboRestrictions getComboRules();

  ICharmAttribute[] getAttributes();

  IndirectCharmRequirement[] getAttributeRequirements();

  Set<ICharm> getParentCharms();
  
  List<String> getParentSubeffects();

  ICharmTypeModel getCharmTypeModel();
  
  boolean isInstanceOfGenericCharm();
}