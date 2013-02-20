package net.sf.anathema.character.generic.impl.magic.persistence.builder.special;

import net.sf.anathema.character.generic.impl.magic.charm.special.EssenceFixedMultiLearnableCharm;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.SpecialCharmBuilder;
import net.sf.anathema.character.generic.impl.traits.EssenceTemplate;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import org.dom4j.Element;

import static net.sf.anathema.character.generic.traits.types.OtherTraitType.Essence;

public class EssenceFixedRepurchaseCharmBuilder implements SpecialCharmBuilder {
  private static final String TAG_ESSENCE_FIXED_REPURCHASES = "essenceFixedRepurchases";

  @Override
  public ISpecialCharm readCharm(Element charmElement, String id) {
    Element repurchasesElement = charmElement.element(TAG_ESSENCE_FIXED_REPURCHASES);
    return new EssenceFixedMultiLearnableCharm(id, EssenceTemplate.SYSTEM_ESSENCE_MAX, Essence);
  }

  @Override
  public boolean willReadCharm(Element charmElement) {
    Element repurchasesElement = charmElement.element(TAG_ESSENCE_FIXED_REPURCHASES);
    return repurchasesElement != null;
  }
}