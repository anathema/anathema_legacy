package net.sf.anathema.character.main.magic.parser.charms;

import net.sf.anathema.character.main.magic.charm.prerequisite.IndirectCharmLearnPrerequisite;
import net.sf.anathema.character.main.traits.ValuedTraitType;

import com.google.common.base.Preconditions;

public class CharmPrerequisiteList {

  private final String[] parentCharmIDs;
  private final ValuedTraitType[] prerequisites;
  private final ValuedTraitType essence;
  private final SelectiveCharmGroupTemplate[] selectiveCharmGroups;
  private final IndirectCharmLearnPrerequisite[] indirectPrerequisites;

  public CharmPrerequisiteList(ValuedTraitType[] prerequisites, ValuedTraitType essence, String[] prerequisiteCharmID,
                               SelectiveCharmGroupTemplate[] selectiveCharmGroups, IndirectCharmLearnPrerequisite[] indirectPrerequisites) {
    Preconditions.checkNotNull(prerequisites);
    Preconditions.checkNotNull(essence);
    Preconditions.checkNotNull(prerequisiteCharmID);
    Preconditions.checkNotNull(selectiveCharmGroups);
    this.prerequisites = prerequisites;
    this.essence = essence;
    this.parentCharmIDs = prerequisiteCharmID;
    this.selectiveCharmGroups = selectiveCharmGroups;
    this.indirectPrerequisites = indirectPrerequisites;
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

  public SelectiveCharmGroupTemplate[] getSelectiveCharmGroups() {
    return selectiveCharmGroups;
  }

  public IndirectCharmLearnPrerequisite[] getIndirectPrerequisites() {
    return indirectPrerequisites;
  }
}