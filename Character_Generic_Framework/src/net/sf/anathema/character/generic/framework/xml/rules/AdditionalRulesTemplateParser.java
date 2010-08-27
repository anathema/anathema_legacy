package net.sf.anathema.character.generic.framework.xml.rules;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.predicate.IPredicate;
import net.disy.commons.core.util.ArrayUtilities;
import net.disy.commons.core.util.ContractFailedException;
import net.sf.anathema.character.generic.additionalrules.IAdditionalEssencePool;
import net.sf.anathema.character.generic.additionalrules.IAdditionalMagicLearnPool;
import net.sf.anathema.character.generic.additionalrules.ITraitCostModifier;
import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.framework.xml.core.AbstractXmlTemplateParser;
import net.sf.anathema.character.generic.framework.xml.registry.IXmlTemplateRegistry;
import net.sf.anathema.character.generic.impl.additional.AdditionalEssencePool;
import net.sf.anathema.character.generic.impl.additional.BackgroundPool;
import net.sf.anathema.character.generic.impl.additional.GenericMagicLearnPool;
import net.sf.anathema.character.generic.impl.additional.MultiLearnableCharmPool;
import net.sf.anathema.character.generic.impl.util.DefaultPointModification;
import net.sf.anathema.character.generic.magic.charms.special.IMultiLearnableCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.util.IPointModification;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.registry.IIdentificateRegistry;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class AdditionalRulesTemplateParser extends AbstractXmlTemplateParser<GenericAdditionalRules> {

  private static final String TAG_REQUIRED_MAGIC = "requiredMagic"; //$NON-NLS-1$
  private static final String TAG_MAGIC = "magic"; //$NON-NLS-1$
  private static final String ATTRIB_TYPE = "type"; //$NON-NLS-1$
  private static final Object VALUE_CHARM = "charm"; //$NON-NLS-1$
  private static final String ATTRIB_ID = "id"; //$NON-NLS-1$
  private static final String TAG_ADDITIONAL_POOLS = "additionalPools"; //$NON-NLS-1$
  private static final String TAG_MULTI_LEARNABLE_POOL = "multilearnablePool"; //$NON-NLS-1$
  private static final String TAG_CHARM_REFERENCE = "charmReference"; //$NON-NLS-1$
  private static final String TAG_PERSONAL_POOL = "personalPool"; //$NON-NLS-1$
  private static final String TAG_PERIPHERAL_POOL = "peripheralPool"; //$NON-NLS-1$
  private static final String ATTRIB_MULTIPLIER = "multiplier"; //$NON-NLS-1$
  private static final String TAG_BACKGROUND_REFERENCE = "backgroundReference"; //$NON-NLS-1$
  private static final String TAG_FORBIDDEN_BACKGROUNDS = "forbiddenBackgrounds"; //$NON-NLS-1$
  private static final String TAG_FIXED_VALUE = "fixedValue"; //$NON-NLS-1$
  private static final String ATTRIB_VALUE = "value"; //$NON-NLS-1$
  private static final String ATTRIB_POOL = "pool"; //$NON-NLS-1$
  private static final String TAG_ADDITIONAL_MAGIC = "additionalMagic"; //$NON-NLS-1$
  private static final String TAG_MAGIC_POOL = "magicPool"; //$NON-NLS-1$
  private static final String TAG_ADDITIONAL_COST = "additionalCost"; //$NON-NLS-1$
  private static final String TAG_COST_MODIFIER = "costModifier"; //$NON-NLS-1$
  private static final String TAG_BONUS_MODIFICATION = "bonusModification"; //$NON-NLS-1$
  private static final String ATTRIB_MINIMUM_VALUE = "thresholdLevel"; //$NON-NLS-1$
  private static final String ATTRIB_DEFAULT_RESPONSE = "defaultResponse"; //$NON-NLS-1$
  private static final String TAG_SPELL_REFERENCE = "spellReference"; //$NON-NLS-1$
  private static final String TAG_DOT_COST_MODIFICATION = "dotCostModification"; //$NON-NLS-1$
  private static final String ATTRIB_FIXED_COST = "fixedCost";
  private final ISpecialCharm[] charms;
  private final IIdentificateRegistry<IBackgroundTemplate> backgroundRegistry;

  public AdditionalRulesTemplateParser(
      IXmlTemplateRegistry<GenericAdditionalRules> registry,
      ISpecialCharm[] charms,
      IIdentificateRegistry<IBackgroundTemplate> backgroundRegistry) {
    super(registry);
    this.charms = charms;
    this.backgroundRegistry = backgroundRegistry;
  }

  @Override
  protected GenericAdditionalRules createNewBasicTemplate() {
    return new GenericAdditionalRules();
  }

  public GenericAdditionalRules parseTemplate(Element element) throws PersistenceException {
    GenericAdditionalRules basicTemplate = getBasicTemplate(element);
    setCompulsiveCharms(element, basicTemplate);
    setAdditionalEssencePools(element, basicTemplate);
    setAdditionalMagicPools(element, basicTemplate);
    setAdditionalTraitCosts(element, basicTemplate);
    setForbiddenBackgrounds(element, basicTemplate);
    return basicTemplate;
  }

  private void setAdditionalTraitCosts(Element element, GenericAdditionalRules basicTemplate)
      throws PersistenceException {
    Element additionalCostElement = element.element(TAG_ADDITIONAL_COST);
    if (additionalCostElement == null) {
      return;
    }
    for (Element costModifierElement : ElementUtilities.elements(additionalCostElement, TAG_COST_MODIFIER)) {
      IBackgroundTemplate backgroundTemplate = getBackgroundTemplate(costModifierElement);
      final List<IPointModification> bonusModifications = new ArrayList<IPointModification>();
      for (Element modificationElement : ElementUtilities.elements(costModifierElement, TAG_BONUS_MODIFICATION)) {
        bonusModifications.add(createPointCostModification(modificationElement));
      }
      final List<IPointModification> dotCostModifications = new ArrayList<IPointModification>();
      for (Element modificationElement : ElementUtilities.elements(costModifierElement, TAG_DOT_COST_MODIFICATION)) {
        dotCostModifications.add(createPointCostModification(modificationElement));
      }
      basicTemplate.addBackgroundCostModifier(backgroundTemplate.getId(), new ITraitCostModifier() {
        public int getAdditionalBonusPointsToSpend(int traitValue) {
          int cost = 0;
          for (IPointModification modification : bonusModifications) {
            cost += modification.getAdditionalPoints(traitValue);
          }
          return cost;
        }

        @Override
        public int getAdditionalDotsToSpend(int traitValue) {
          int cost = 0;
          for (IPointModification modification : dotCostModifications) {
            cost += modification.getAdditionalPoints(traitValue);
          }
          return cost;
        }
      });
    }
  }

  private IPointModification createPointCostModification(Element bonusModification) throws PersistenceException {
    if (bonusModification == null) {
      return new DefaultPointModification();
    }
    final int minimumValue = ElementUtilities.getRequiredIntAttrib(bonusModification, ATTRIB_MINIMUM_VALUE);
    final int multiplier = ElementUtilities.getIntAttrib(bonusModification, ATTRIB_MULTIPLIER, 0);
    final int fixedCost = ElementUtilities.getIntAttrib(bonusModification, ATTRIB_FIXED_COST, 0);
    return new IPointModification() {
      public int getAdditionalPoints(int traitValue) {
        int variable = Math.max(0, (traitValue - minimumValue) * multiplier);
        int fixed = traitValue > minimumValue ? fixedCost : 0;
        return variable + fixed;
      }
    };
  }

  private void setForbiddenBackgrounds(Element element, GenericAdditionalRules basicTemplate) {
    Element backgroundsElement = element.element(TAG_FORBIDDEN_BACKGROUNDS);
    if (backgroundsElement == null) {
      return;
    }
    List<String> ids = new ArrayList<String>();
    for (Element background : ElementUtilities.elements(backgroundsElement, TAG_BACKGROUND_REFERENCE)) {
      ids.add(background.attributeValue(ATTRIB_ID));
    }
    basicTemplate.setRejectedBackgrounds(ids.toArray(new String[ids.size()]));
  }

  private void setAdditionalMagicPools(Element element, GenericAdditionalRules basicTemplate)
      throws PersistenceException {
    Element additionalMagicElement = element.element(TAG_ADDITIONAL_MAGIC);
    if (additionalMagicElement == null) {
      return;
    }
    List<IAdditionalMagicLearnPool> pools = new ArrayList<IAdditionalMagicLearnPool>();
    for (Element magicPoolElement : ElementUtilities.elements(additionalMagicElement, TAG_MAGIC_POOL)) {
      IBackgroundTemplate backgroundTemplate = getBackgroundTemplate(magicPoolElement);
      boolean defaultAnswer = ElementUtilities.getBooleanAttribute(magicPoolElement, ATTRIB_DEFAULT_RESPONSE, true);
      GenericMagicLearnPool pool = new GenericMagicLearnPool(backgroundTemplate, defaultAnswer);
      for (Element spellReference : ElementUtilities.elements(magicPoolElement, TAG_SPELL_REFERENCE)) {
        pool.addIdException(spellReference.attributeValue(ATTRIB_ID));
      }
      pools.add(pool);
    }
    basicTemplate.setMagicPools(pools.toArray(new IAdditionalMagicLearnPool[pools.size()]));
  }

  private void setAdditionalEssencePools(Element element, GenericAdditionalRules basicTemplate)
      throws PersistenceException {
    Element additionalPoolsElement = element.element(TAG_ADDITIONAL_POOLS);
    if (additionalPoolsElement == null) {
      return;
    }
    List<IAdditionalEssencePool> pools = new ArrayList<IAdditionalEssencePool>();
    for (Element multiPool : ElementUtilities.elements(additionalPoolsElement, TAG_MULTI_LEARNABLE_POOL)) {
      AdditionalEssencePool personalPool = parsePool(multiPool, TAG_PERSONAL_POOL);
      AdditionalEssencePool peripheralPool = parsePool(multiPool, TAG_PERIPHERAL_POOL);
      if (multiPool.element(TAG_CHARM_REFERENCE) != null) {
        final String charmId = ElementUtilities.getRequiredAttrib(multiPool.element(TAG_CHARM_REFERENCE), ATTRIB_ID);
        ISpecialCharm charm = ArrayUtilities.getFirst(charms, new IPredicate<ISpecialCharm>() {
          public boolean evaluate(ISpecialCharm value) {
            return value.getCharmId().equals(charmId);
          }
        });
        pools.add(new MultiLearnableCharmPool((IMultiLearnableCharm) charm, personalPool, peripheralPool));
      }
      else if (multiPool.element(TAG_BACKGROUND_REFERENCE) != null) {
        pools.add(new BackgroundPool(getBackgroundTemplate(multiPool), personalPool, peripheralPool));
      }
      else {
        throw new ContractFailedException("Either CharmReference or BackgroundReference required."); //$NON-NLS-1$
      }
    }
    basicTemplate.setAdditionalEssencePools(pools.toArray(new IAdditionalEssencePool[pools.size()]));
  }

  private IBackgroundTemplate getBackgroundTemplate(Element parent) throws PersistenceException {
    final String backgroundId = ElementUtilities.getRequiredAttrib(parent.element(TAG_BACKGROUND_REFERENCE), ATTRIB_ID);
    IBackgroundTemplate background = backgroundRegistry.getById(backgroundId);
    return background;
  }

  private AdditionalEssencePool parsePool(Element parent, String elementName) throws PersistenceException {
    Element poolElement = parent.element(elementName);
    if (poolElement == null) {
      return new AdditionalEssencePool(0);
    }
    int multiplier = ElementUtilities.getIntAttrib(poolElement, ATTRIB_MULTIPLIER, 0);
    AdditionalEssencePool pool = new AdditionalEssencePool(multiplier);
    for (Element overrideElement : ElementUtilities.elements(poolElement, TAG_FIXED_VALUE)) {
      int traitValue = ElementUtilities.getRequiredIntAttrib(overrideElement, ATTRIB_VALUE);
      int poolValue = ElementUtilities.getRequiredIntAttrib(overrideElement, ATTRIB_POOL);
      pool.setFixedValue(traitValue, poolValue);
    }
    return pool;
  }

  private void setCompulsiveCharms(Element element, GenericAdditionalRules basicTemplate) throws PersistenceException {
    Element requiredMagicElement = element.element(TAG_REQUIRED_MAGIC);
    if (requiredMagicElement == null) {
      return;
    }
    List<Element> magicElements = ElementUtilities.elements(requiredMagicElement, TAG_MAGIC);
    List<String> compulsiveCharms = new ArrayList<String>();
    for (Element magic : magicElements) {
      String type = ElementUtilities.getRequiredAttrib(magic, ATTRIB_TYPE);
      if (type.equals(VALUE_CHARM)) {
        compulsiveCharms.add(ElementUtilities.getRequiredAttrib(magic, ATTRIB_ID));
      }
    }
    basicTemplate.setCompulsiveCharmIds(compulsiveCharms.toArray(new String[compulsiveCharms.size()]));
  }
}