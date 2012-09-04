package net.sf.anathema.character.generic.magic.charms.special;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.traits.ITraitType;

public interface IPrerequisiteModifyingCharm extends ISpecialCharm {
  int modifyRequiredValue(ICharm charm, int currentlyRequiredValue);

  /**Returns the modified required value, if the candidate trait matches the expected trait.
   * For Charms, the candidate usually is the primary trait, since all prerequisites should be modified if the modifying rule
   * applies to the Charms primary.
   */
  int modifyRequiredValueIfIsApplicableToCandidate(ITraitType candidateTrait, int currentlyRequiredValue);
}