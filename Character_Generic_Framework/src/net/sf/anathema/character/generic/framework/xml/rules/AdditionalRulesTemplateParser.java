package net.sf.anathema.character.generic.framework.xml.rules;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.predicate.IPredicate;
import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.character.generic.additionalrules.IAdditionalEssencePool;
import net.sf.anathema.character.generic.framework.xml.core.AbstractXmlTemplateParser;
import net.sf.anathema.character.generic.framework.xml.registry.IXmlTemplateRegistry;
import net.sf.anathema.character.generic.impl.additional.MultiLearnablePool;
import net.sf.anathema.character.generic.magic.charms.special.IMultiLearnableCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.lib.exception.PersistenceException;
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
  private final ISpecialCharm[] charms;

  public AdditionalRulesTemplateParser(IXmlTemplateRegistry<GenericAdditionalRules> registry, ISpecialCharm[] charms) {
    super(registry);
    this.charms = charms;
  }

  @Override
  protected GenericAdditionalRules createNewBasicTemplate() {
    return new GenericAdditionalRules();
  }

  public GenericAdditionalRules parseTemplate(Element element) throws PersistenceException {
    GenericAdditionalRules basicTemplate = getBasicTemplate(element);
    setCompulsiveCharms(element, basicTemplate);
    setAdditionalEssencePools(element, basicTemplate);
    return basicTemplate;
  }

  private void setAdditionalEssencePools(Element element, GenericAdditionalRules basicTemplate)
      throws PersistenceException {
    Element additionalPoolsElement = element.element(TAG_ADDITIONAL_POOLS);
    if (additionalPoolsElement == null) {
      return;
    }
    List<IAdditionalEssencePool> pools = new ArrayList<IAdditionalEssencePool>();
    for (Element multiPool : ElementUtilities.elements(additionalPoolsElement, TAG_MULTI_LEARNABLE_POOL)) {
      final String charmId = ElementUtilities.getRequiredAttrib(multiPool.element(TAG_CHARM_REFERENCE), ATTRIB_ID);
      int personalMultiplier = parseMultiplier(multiPool, TAG_PERSONAL_POOL);
      int peripheralMultiplier = parseMultiplier(multiPool, TAG_PERIPHERAL_POOL);
      ISpecialCharm charm = ArrayUtilities.getFirst(charms, new IPredicate<ISpecialCharm>() {
        public boolean evaluate(ISpecialCharm value) {
          return value.getCharmId().equals(charmId);
        }
      });
      pools.add(new MultiLearnablePool((IMultiLearnableCharm) charm, personalMultiplier, peripheralMultiplier));
    }
    basicTemplate.setAdditionalEssencePools(pools.toArray(new IAdditionalEssencePool[pools.size()]));
  }

  private int parseMultiplier(Element element, String elementName) throws PersistenceException {
    Element poolElement = element.element(elementName);
    if (poolElement == null) {
      return 0;
    }
    return ElementUtilities.getRequiredIntAttrib(poolElement, ATTRIB_MULTIPLIER);
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