package net.sf.anathema.character.magic.charm;

import java.util.List;
import java.util.Set;

import net.sf.anathema.character.magic.basic.Magic;
import net.sf.anathema.character.magic.charm.combos.IComboRestrictions;
import net.sf.anathema.character.magic.charm.duration.Duration;
import net.sf.anathema.character.magic.charm.prerequisite.CharmLearnPrerequisite;
import net.sf.anathema.character.magic.charm.type.ICharmTypeModel;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.ValuedTraitType;
import net.sf.anathema.character.framework.type.CharacterType;

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