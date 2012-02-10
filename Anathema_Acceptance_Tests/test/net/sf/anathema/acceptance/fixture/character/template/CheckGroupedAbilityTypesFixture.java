package net.sf.anathema.acceptance.fixture.character.template;

import com.google.common.base.Predicate;
import net.sf.anathema.character.generic.template.abilities.IGroupedTraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.lib.lang.ArrayUtilities;

public class CheckGroupedAbilityTypesFixture extends AbstractTemplateColumnFixture {

  public String abilityTypeId;

  public String getGroupId() {
    final AbilityType type = AbilityType.valueOf(abilityTypeId);
    IGroupedTraitType[] types = getTemplate().getAbilityGroups();
    IGroupedTraitType foundType = ArrayUtilities.find(new Predicate<IGroupedTraitType>() {
      public boolean apply(IGroupedTraitType input) {
        return input.getTraitType() == type;
      }
    }, types);
    return foundType.getGroupId();
  }
}