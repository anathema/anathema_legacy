package net.sf.anathema.character.generic.impl.magic.persistence.prerequisite;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.character.generic.magic.charms.ICharmAttributeRequirement;
import net.sf.anathema.character.generic.traits.IGenericTrait;

public class CharmPrerequisiteList {

  private final String[] parentCharmIDs;
  private final IGenericTrait[] prerequisites;
  private final IGenericTrait essence;
  private final SelectiveCharmGroupTemplate[] selectiveCharmGroups;
  private final ICharmAttributeRequirement[] attributeRequirements;

  public CharmPrerequisiteList(
      IGenericTrait[] prerequisites,
      IGenericTrait essence,
      String[] prerequisiteCharmID,
      SelectiveCharmGroupTemplate[] selectiveCharmGroups,
      ICharmAttributeRequirement[] attributeRequirements) {
    Ensure.ensureNotNull("Argument must not be null.", prerequisites); //$NON-NLS-1$
    Ensure.ensureNotNull("Argument must not be null.", essence); //$NON-NLS-1$
    Ensure.ensureNotNull("Argument must not be null.", prerequisiteCharmID); //$NON-NLS-1$
    Ensure.ensureNotNull("SelectiveCharmGroups must not be null.", selectiveCharmGroups); //$NON-NLS-1$
    this.prerequisites = prerequisites;
    this.essence = essence;
    this.parentCharmIDs = prerequisiteCharmID;
    this.selectiveCharmGroups = selectiveCharmGroups;
    this.attributeRequirements = attributeRequirements;
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

  public ICharmAttributeRequirement[] getAttributeRequirements() {
    return attributeRequirements;
  }
}