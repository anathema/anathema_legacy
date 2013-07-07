package net.sf.anathema.character.main.magic.persistence.builder.special;

import net.sf.anathema.character.main.magic.model.charm.special.EssenceFixedMultiLearnableCharm;
import net.sf.anathema.character.main.traits.EssenceTemplate;
import net.sf.anathema.character.main.magic.model.charm.special.ISpecialCharm;
import org.dom4j.Element;

import static net.sf.anathema.character.main.traits.types.OtherTraitType.Essence;

@SpecialCharmParser
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