package net.sf.anathema.character.main.magic.parser.charms;

import net.sf.anathema.character.main.magic.charm.prerequisite.CharmLearnPrerequisite;
import net.sf.anathema.character.main.traits.ValuedTraitType;

import com.google.common.base.Preconditions;

public class CharmPrerequisiteList {

  private final String[] parentCharmIDs;
  private final ValuedTraitType[] prerequisites;
  private final ValuedTraitType essence;
  private final CharmLearnPrerequisite[] learnPrerequisites;

  public CharmPrerequisiteList(ValuedTraitType[] prerequisites, ValuedTraitType essence, String[] prerequisiteCharmID,
                               CharmLearnPrerequisite[] learnPrerequisites) {
    Preconditions.checkNotNull(prerequisites);
    Preconditions.checkNotNull(essence);
    Preconditions.checkNotNull(prerequisiteCharmID);
    Preconditions.checkNotNull(learnPrerequisites);
    this.prerequisites = prerequisites;
    this.essence = essence;
    this.parentCharmIDs = prerequisiteCharmID;
    this.learnPrerequisites = learnPrerequisites;
  }

  public ValuedTraitType getEssence() {
    return essence;
  }

  public ValuedTraitType[] getPrerequisites() {
    return prerequisites;
  }

  public String[] getParentIDs() {
    return parentCharmIDs;
  }

  public CharmLearnPrerequisite[] getLearnPrerequisites() {
    return learnPrerequisites;
  }
}