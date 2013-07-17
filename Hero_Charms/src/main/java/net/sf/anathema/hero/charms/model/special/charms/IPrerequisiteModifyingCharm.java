package net.sf.anathema.hero.charms.model.special.charms;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.traits.TraitType;

public interface IPrerequisiteModifyingCharm extends ISpecialCharm {
  int modifyRequiredValue(Charm charm, int currentlyRequiredValue);

  /**Returns the modified required value, if the candidate trait matches the expected trait.
   * For Charms, the candidate usually is the primary trait, since all prerequisites should be modified if the modifying rule
   * applies to the Charms primary.
   */
  int modifyRequiredValueIfIsApplicableToCandidate(TraitType candidateTrait, int currentlyRequiredValue);
}