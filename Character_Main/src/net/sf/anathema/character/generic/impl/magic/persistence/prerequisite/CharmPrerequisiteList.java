package net.sf.anathema.character.generic.impl.magic.persistence.prerequisite;

import com.google.common.base.Preconditions;
import net.sf.anathema.character.generic.magic.charms.IndirectCharmRequirement;
import net.sf.anathema.character.generic.traits.GenericTrait;

public class CharmPrerequisiteList {

  private final String[] parentCharmIDs;
  private final GenericTrait[] prerequisites;
  private final GenericTrait essence;
  private final SelectiveCharmGroupTemplate[] selectiveCharmGroups;
  private final IndirectCharmRequirement[] indirectRequirements;

  public CharmPrerequisiteList(GenericTrait[] prerequisites, GenericTrait essence, String[] prerequisiteCharmID,
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

  public GenericTrait getEssence() {
    return essence;
  }

  public GenericTrait[] getPrerequisites() {
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