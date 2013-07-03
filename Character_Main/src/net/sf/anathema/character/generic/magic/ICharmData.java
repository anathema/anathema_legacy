package net.sf.anathema.character.generic.magic;

import net.sf.anathema.character.generic.magic.charms.ICharmAttribute;
import net.sf.anathema.character.generic.magic.charms.IComboRestrictions;
import net.sf.anathema.character.generic.magic.charms.IndirectCharmRequirement;
import net.sf.anathema.character.generic.magic.charms.duration.IDuration;
import net.sf.anathema.character.generic.magic.charms.type.ICharmTypeModel;
import net.sf.anathema.character.generic.traits.ValuedTraitType;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

import java.util.List;
import java.util.Set;

public interface ICharmData extends IMagicData {
  Identifier FORM_ATTRIBUTE = new SimpleIdentifier("Form");
  Identifier MERGED_ATTRIBUTE = new SimpleIdentifier("Merged");
  Identifier ALLOWS_CELESTIAL_ATTRIBUTE = new SimpleIdentifier("AllowsCelestial");
  Identifier UNRESTRICTED_ATTRIBUTE = new SimpleIdentifier("Unrestricted");
  Identifier NO_STYLE_ATTRIBUTE = new SimpleIdentifier("NoStyle");
  Identifier NATIVE = new SimpleIdentifier("Native");
  String FAVORED_CASTE_PREFIX = "FavoredCaste.";

  ICharacterType getCharacterType();

  IDuration getDuration();

  ValuedTraitType getEssence();

  ValuedTraitType[] getPrerequisites();

  TraitType getPrimaryTraitType();

  String getGroupId();

  IComboRestrictions getComboRules();

  ICharmAttribute[] getAttributes();

  IndirectCharmRequirement[] getAttributeRequirements();

  Set<ICharm> getParentCharms();
  
  List<String> getParentSubeffects();

  ICharmTypeModel getCharmTypeModel();
  
  boolean isInstanceOfGenericCharm();
}