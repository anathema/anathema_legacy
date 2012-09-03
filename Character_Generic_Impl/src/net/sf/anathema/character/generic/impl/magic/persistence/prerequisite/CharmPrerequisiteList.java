package net.sf.anathema.character.generic.impl.magic.persistence.prerequisite;

import com.google.common.base.Preconditions;
import net.sf.anathema.character.generic.magic.charms.IndirectCharmRequirement;
import net.sf.anathema.character.generic.traits.IGenericTrait;

public class CharmPrerequisiteList {

  private final String[] parentCharmIDs;
  private final IGenericTrait[] prerequisites;
  private final IGenericTrait essence;
  private final SelectiveCharmGroupTemplate[] selectiveCharmGroups;
  private final IndirectCharmRequirement[] indirectRequirements;

  public CharmPrerequisiteList(
      IGenericTrait[] prerequisites,
      IGenericTrait essence,
      String[] prerequisiteCharmID,
      SelectiveCharmGroupTemplate[] selectiveCharmGroups,
      IndirectCharmRequirement[] indirectRequirements) {
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

  public IGenericTrait getEssence() {
    return essence;
  }

  public IGenericTrait[] getPrerequisites() {
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