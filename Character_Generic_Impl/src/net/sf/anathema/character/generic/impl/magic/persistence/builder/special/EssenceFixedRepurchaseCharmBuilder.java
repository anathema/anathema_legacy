package net.sf.anathema.character.generic.impl.magic.persistence.builder.special;

import net.sf.anathema.character.generic.impl.magic.charm.special.EssenceFixedMultiLearnableCharm;
import net.sf.anathema.character.generic.impl.traits.EssenceTemplate;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import org.dom4j.Element;

import static net.sf.anathema.character.generic.traits.types.OtherTraitType.Essence;

public class EssenceFixedRepurchaseCharmBuilder {
  private static final String TAG_ESSENCE_FIXED_REPURCHASES = "essenceFixedRepurchases";

  public ISpecialCharm readEssenceFixedRepurchasesCharm(Element charmElement, String id) {
    Element repurchasesElement = charmElement.element(TAG_ESSENCE_FIXED_REPURCHASES);
    if (repurchasesElement == null) {
      return null;
    }
    return new EssenceFixedMultiLearnableCharm(id, EssenceTemplate.SYSTEM_ESSENCE_MAX, Essence);
  }
}