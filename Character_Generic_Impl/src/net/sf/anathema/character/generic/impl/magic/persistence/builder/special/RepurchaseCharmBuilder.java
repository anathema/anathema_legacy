package net.sf.anathema.character.generic.impl.magic.persistence.builder.special;

import net.sf.anathema.character.generic.impl.magic.charm.special.*;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.SpecialCharmBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.TraitTypeFinder;
import net.sf.anathema.character.generic.impl.traits.EssenceTemplate;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.ValuedTraitType;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

import static net.sf.anathema.character.generic.traits.types.OtherTraitType.Essence;

public class RepurchaseCharmBuilder {

  private static final String TAG_REPURCHASES = "repurchases";
  private static final String TAG_REPURCHASE = "repurchase";
  private static final String ATTRIB_ABSOLUTE_MAX = "absoluteMax";
  private static final String ATTRIB_LIMITING_TRAIT = "limitingTrait";
  private static final String ATTRIB_LIMIT = "limit";

  private final TraitTypeFinder traitTypeFinder = new TraitTypeFinder();

  public ISpecialCharm readRepurchaseCharm(Element charmElement, String id) {
    Element repurchaseElement = charmElement.element(TAG_REPURCHASES);
    if (repurchaseElement == null) {
      return null;
    }

    String limitingTraitString = repurchaseElement.attributeValue(ATTRIB_LIMITING_TRAIT);
    if (limitingTraitString != null) {
      ITraitType trait = traitTypeFinder.getTrait(limitingTraitString);

      int modifier = 0;
      String modifierString = repurchaseElement.attributeValue(SpecialCharmBuilder.ATTRIB_MODIFIER);
      try {
        modifier = Integer.parseInt(modifierString);
      } catch (Exception ignored) {
      }

      int absoluteMax = EssenceTemplate.SYSTEM_ESSENCE_MAX + modifier;
      String maxString = repurchaseElement.attributeValue(ATTRIB_ABSOLUTE_MAX);
      try {
        absoluteMax = Integer.parseInt(maxString);
      } catch (Exception ignored) {
      }

      return new TraitDependentMultiLearnableCharm(id, absoluteMax, trait, modifier);
    }

    String limitString = repurchaseElement.attributeValue(ATTRIB_LIMIT);
    if (limitString != null) {
      int limit = Integer.parseInt(limitString);

      return new StaticMultiLearnableCharm(id, limit);
    }

    String traitString = repurchaseElement.attributeValue(SpecialCharmBuilder.ATTRIB_TRAIT);
    ITraitType trait = traitTypeFinder.getTrait(traitString);
    List<CharmTier> tiers = new ArrayList<>();

    for (Object repurchaseObj : repurchaseElement.elements(TAG_REPURCHASE)) {
      Element repurchase = (Element) repurchaseObj;
      int essence = ElementUtilities.getRequiredIntAttrib(repurchase, SpecialCharmBuilder.ATTRIB_ESSENCE);
      CharmTier tier = createTier(trait, repurchase, essence);
      tiers.add(tier);
    }

    CharmTier[] tierArray = tiers.toArray(new CharmTier[tiers.size()]);
    return new TieredMultiLearnableCharm(id, tierArray);
  }

  private CharmTier createTier(ITraitType trait, Element repurchase, int essence) {
    TraitCharmTier traitCharmTier = new TraitCharmTier();
    traitCharmTier.addRequirement(new ValuedTraitType(Essence, essence));
    if (trait != null) {
      int traitValue = ElementUtilities.getRequiredIntAttrib(repurchase, SpecialCharmBuilder.ATTRIB_TRAIT);
      traitCharmTier.addRequirement(new ValuedTraitType(trait, traitValue));
    }
    return traitCharmTier;
  }
}