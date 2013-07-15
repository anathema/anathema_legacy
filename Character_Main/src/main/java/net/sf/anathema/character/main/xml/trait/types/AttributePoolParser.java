package net.sf.anathema.character.main.xml.trait.types;

import net.sf.anathema.character.main.traits.groups.AllAttributeTraitTypeGroup;
import net.sf.anathema.character.main.traits.groups.TraitTypeGroup;
import net.sf.anathema.character.main.xml.registry.IXmlTemplateRegistry;
import net.sf.anathema.character.main.xml.trait.GenericTraitTemplateFactory;
import net.sf.anathema.character.main.xml.trait.pool.GenericTraitTemplatePool;

public class AttributePoolParser extends AbstractPoolTemplateParser {

  private static final String TAG_ATTRIBUTES = "attributes";

  public AttributePoolParser(IXmlTemplateRegistry<GenericTraitTemplatePool> poolTemplateRegistry, GenericTraitTemplateFactory templateFactory) {
    super(poolTemplateRegistry, templateFactory);
  }

  @Override
  protected void executeResult(GenericTraitTemplatePool traitPool) {
    templateFactory.setAttributesPool(traitPool);
  }

  @Override
  protected String getTagName() {
    return TAG_ATTRIBUTES;
  }

  @Override
  protected TraitTypeGroup getTraitTypeGroup() {
    return AllAttributeTraitTypeGroup.getInstance();
  }
}