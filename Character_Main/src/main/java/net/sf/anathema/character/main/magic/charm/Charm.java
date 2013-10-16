package net.sf.anathema.character.main.magic.charm;

import java.util.List;
import java.util.Set;

import net.sf.anathema.character.main.magic.basic.Magic;
import net.sf.anathema.character.main.magic.charm.combos.IComboRestrictions;
import net.sf.anathema.character.main.magic.charm.duration.Duration;
import net.sf.anathema.character.main.magic.charm.prerequisite.CharmLearnPrerequisite;
import net.sf.anathema.character.main.magic.charm.type.ICharmTypeModel;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.traits.ValuedTraitType;
import net.sf.anathema.character.main.type.CharacterType;

public interface Charm extends Magic {

  String FAVORED_CASTE_PREFIX = "FavoredCaste.";

  CharacterType getCharacterType();

  Duration getDuration();

  ValuedTraitType getEssence();

  ValuedTraitType[] getPrerequisites();

  TraitType getPrimaryTraitType();

  String getGroupId();

  IComboRestrictions getComboRules();
  
  List<CharmLearnPrerequisite> getLearnPrerequisites();

  Set<Charm> getParentCharms();

  ICharmTypeModel getCharmTypeModel();

  boolean isInstanceOfGenericCharm();

  Set<Charm> getLearnFollowUpCharms(ICharmLearnArbitrator learnArbitrator);

  Set<Charm> getLearnPrerequisitesCharms(ICharmLearnArbitrator learnArbitrator);
  
  <T extends CharmLearnPrerequisite> List<T> getPrerequisitesOfType(Class<T> clazz);

  boolean isBlockedByAlternative(ICharmLearnArbitrator  learnArbitrator);

  Set<Charm> getLearnChildCharms();

  Set<Charm> getMergedCharms();
  
  boolean isTreeRoot();

  Set<Charm> getRenderingPrerequisiteCharms();
}