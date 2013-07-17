package net.sf.anathema.character.main.magic.parser.charms;

import com.google.common.base.Preconditions;
import net.sf.anathema.character.main.magic.charm.requirements.IndirectCharmRequirement;
import net.sf.anathema.character.main.traits.ValuedTraitType;

public class CharmPrerequisiteList {

  private final String[] parentCharmIDs;
  private final ValuedTraitType[] prerequisites;
  private final ValuedTraitType essence;
  private final SelectiveCharmGroupTemplate[] selectiveCharmGroups;
  private final IndirectCharmRequirement[] indirectRequirements;

  public CharmPrerequisiteList(ValuedTraitType[] prerequisites, ValuedTraitType essence, String[] prerequisiteCharmID,
                               SelectiveCharmGroupTemplate[] selectiveCharmGroups, IndirectCharmRequirement[] indirectRequirements) {
    Preconditions.checkNotNull(prerequisites);
    Preconditions.checkNotNull(essence);
    Preconditions.checkNotNull(prerequisiteCharmID);
    Preconditions.checkNotNull(selectiveCharmGroups);
    this.prerequisites = prerequisites;
    this.essence = essence;
    this.parentCharmIDs = prerequisiteCharmID;
    this.selectiveCharmGroups = selectiveCharmGroups;
    this.indirectRequirements = indirectRequirements;
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

  public IndirectCharmRequirement[] getAttributeRequirements() {
    return indirectRequirements;
  }
}