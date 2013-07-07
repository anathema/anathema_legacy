package net.sf.anathema.character.main.magic.model.charm;

import net.sf.anathema.character.main.magic.model.magic.IMagicData;
import net.sf.anathema.character.main.magic.model.combos.IComboRestrictions;
import net.sf.anathema.character.main.magic.model.charm.duration.IDuration;
import net.sf.anathema.character.main.magic.model.charm.type.ICharmTypeModel;
import net.sf.anathema.character.main.traits.ValuedTraitType;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.type.ICharacterType;
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