package net.sf.anathema.character.main.magic.model.charm.special;

import net.sf.anathema.character.main.magic.model.charm.ICharm;
import net.sf.anathema.character.main.traits.TraitType;

public interface IPrerequisiteModifyingCharm extends ISpecialCharm {
  int modifyRequiredValue(ICharm charm, int currentlyRequiredValue);

  /**Returns the modified required value, if the candidate trait matches the expected trait.
   * For Charms, the candidate usually is the primary trait, since all prerequisites should be modified if the modifying rule
   * applies to the Charms primary.
   */
  int modifyRequiredValueIfIsApplicableToCandidate(TraitType candidateTrait, int currentlyRequiredValue);
}