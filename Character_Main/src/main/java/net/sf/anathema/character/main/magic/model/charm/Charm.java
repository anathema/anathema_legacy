package net.sf.anathema.character.main.magic.model.charm;

import net.sf.anathema.hero.charmtree.duration.IDuration;
import net.sf.anathema.hero.charmtree.type.ICharmTypeModel;
import net.sf.anathema.character.main.magic.model.charmtree.ICharmLearnArbitrator;
import net.sf.anathema.character.main.magic.model.combos.IComboRestrictions;
import net.sf.anathema.character.main.magic.model.magic.Magic;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.traits.ValuedTraitType;
import net.sf.anathema.character.main.type.CharacterType;

import java.util.List;
import java.util.Set;

public interface Charm extends Magic {

  String FAVORED_CASTE_PREFIX = "FavoredCaste.";

  CharacterType getCharacterType();

  IDuration getDuration();

  ValuedTraitType getEssence();

  ValuedTraitType[] getPrerequisites();

  TraitType getPrimaryTraitType();

  String getGroupId();

  IComboRestrictions getComboRules();

  IndirectCharmRequirement[] getAttributeRequirements();

  Set<Charm> getParentCharms();

  List<String> getParentSubEffects();

  ICharmTypeModel getCharmTypeModel();

  boolean isInstanceOfGenericCharm();

  Set<Charm> getLearnFollowUpCharms(ICharmLearnArbitrator learnArbitrator);

  Set<Charm> getLearnPrerequisitesCharms(ICharmLearnArbitrator learnArbitrator);

  boolean isBlockedByAlternative(ICharmLearnArbitrator  learnArbitrator);

  Set<Charm> getLearnChildCharms();

  Set<Charm> getMergedCharms();
  
  boolean isTreeRoot();

  Set<Charm> getRenderingPrerequisiteCharms();
  
  Set<IndirectCharmRequirement> getIndirectRequirements();
}