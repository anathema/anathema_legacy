package net.sf.anathema.character.spirit.template;

import net.sf.anathema.character.generic.health.HealthLevelType;
import net.sf.anathema.character.generic.impl.magic.charm.special.OxBodyTechniqueCharm;
import net.sf.anathema.character.generic.magic.charms.special.IOxBodyTechniqueCharm;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;

import java.util.LinkedHashMap;

public interface ISpiritSpecialCharms
{
	  @SuppressWarnings("serial")
      IOxBodyTechniqueCharm OX_BODY_TECHNIQUE = new OxBodyTechniqueCharm(
		      "Spirit.OxBodyTechnique", OtherTraitType.Essence,//$NON-NLS-1$
		      new LinkedHashMap<String, HealthLevelType[]>() {
		        {
		          put("Category.-1,-2", new HealthLevelType[] { HealthLevelType.ONE, HealthLevelType.TWO }); //$NON-NLS-1$
		        }
		      });

}