package net.sf.anathema.character.generic.framework.xml.trait.types;

import net.sf.anathema.character.generic.framework.xml.registry.IXmlTemplateRegistry;
import net.sf.anathema.character.generic.framework.xml.trait.GenericTraitTemplateFactory;
import net.sf.anathema.character.generic.framework.xml.trait.pool.GenericTraitTemplatePool;
import net.sf.anathema.character.generic.framework.xml.trait.pool.GenericTraitTemplatePoolParser;
import net.sf.anathema.character.generic.traits.groups.TraitTypeGroup;
import net.sf.anathema.lib.exception.PersistenceException;

import org.dom4j.Element;

public abstract class AbstractPoolTemplateParser {

  protected final IXmlTemplateRegistry<GenericTraitTemplatePool> poolTemplateRegistry;

  protected final GenericTraitTemplateFactory templateFactory;

  public AbstractPoolTemplateParser(
      IXmlTemplateRegistry<GenericTraitTemplatePool> poolTemplateRegistry,
      GenericTraitTemplateFactory templateFactory) {
    this.poolTemplateRegistry = poolTemplateRegistry;
    this.templateFactory = templateFactory;
  }

  private GenericTraitTemplatePoolParser createPoolParser() {
    return new GenericTraitTemplatePoolParser(poolTemplateRegistry, getTraitTypeGroup());
  }

  public void parseTraits(Element element) throws PersistenceException {
    Element traitElement = element.element(getTagName());
    if (traitElement == null) {
      return;
    }
    final GenericTraitTemplatePool traitPool = createPoolParser().parseTemplate(traitElement);
    executeResult(traitPool);
  }

  protected abstract void executeResult(final GenericTraitTemplatePool traitPool);

  protected abstract String getTagName();

  protected abstract TraitTypeGroup getTraitTypeGroup();
}