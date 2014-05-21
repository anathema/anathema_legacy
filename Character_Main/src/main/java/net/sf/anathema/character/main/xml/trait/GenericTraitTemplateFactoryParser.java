package net.sf.anathema.character.main.xml.trait;

import net.sf.anathema.character.main.xml.core.AbstractXmlTemplateParser;
import net.sf.anathema.character.main.xml.registry.IXmlTemplateRegistry;
import net.sf.anathema.character.main.xml.trait.pool.GenericTraitTemplatePool;
import net.sf.anathema.character.main.xml.trait.types.VirtuePoolParser;
import net.sf.anathema.lib.exception.PersistenceException;
import org.dom4j.Element;

public class GenericTraitTemplateFactoryParser extends AbstractXmlTemplateParser<GenericTraitTemplateFactory> {

  private static final String TAG_ESSENCE = "essence";
  private static final String TAG_WILLPOWER = "willpower";
  private final IXmlTemplateRegistry<GenericTraitTemplatePool> poolTemplateRegistry;

  public GenericTraitTemplateFactoryParser(IXmlTemplateRegistry<GenericTraitTemplateFactory> templateRegistry,
                                           IXmlTemplateRegistry<GenericTraitTemplatePool> poolTemplateRegistry) {
    super(templateRegistry);
    this.poolTemplateRegistry = poolTemplateRegistry;
  }

  @Override
  protected GenericTraitTemplateFactory createNewBasicTemplate() {
    return new GenericTraitTemplateFactory();
  }

  @Override
  public GenericTraitTemplateFactory parseTemplate(Element element) throws PersistenceException {
    GenericTraitTemplateFactory templateFactory = getBasicTemplate(element);
    new VirtuePoolParser(poolTemplateRegistry, templateFactory).parseTraits(element);
    parseEssence(element, templateFactory);
    parseWillpower(element, templateFactory);
    return templateFactory;
  }

  private void parseWillpower(Element element, GenericTraitTemplateFactory templateFactory) throws PersistenceException {
    Element willpowerElement = element.element(TAG_WILLPOWER);
    if (willpowerElement == null) {
      return;
    }
    IClonableTraitTemplate willpowerTemplate = GenericTraitTemplateParser.parseTraitTemplate(willpowerElement);
    templateFactory.setWillpowerTemplate((GenericTraitTemplate) willpowerTemplate);
  }

  private void parseEssence(Element element, GenericTraitTemplateFactory templateFactory) throws PersistenceException {
    Element essenceElement = element.element(TAG_ESSENCE);
    if (essenceElement == null) {
      return;
    }
    IClonableTraitTemplate essenceTemplate = GenericTraitTemplateParser.parseTraitTemplate(essenceElement);
    templateFactory.setEssenceTemplate((GenericTraitTemplate) essenceTemplate);
  }
}