package net.sf.anathema.character.generic.framework.xml.trait.types;

import net.sf.anathema.character.generic.framework.xml.registry.IXmlTemplateRegistry;
import net.sf.anathema.character.generic.framework.xml.trait.GenericTraitTemplateFactory;
import net.sf.anathema.character.generic.framework.xml.trait.pool.GenericTraitTemplatePool;
import net.sf.anathema.character.generic.traits.groups.AllAttributeTraitTypeGroup;
import net.sf.anathema.character.generic.traits.groups.TraitTypeGroup;

public class AttributePoolParser extends AbstractPoolTemplateParser {

  private static final String TAG_ATTRIBUTES = "attributes"; //$NON-NLS-1$

  public AttributePoolParser(
      IXmlTemplateRegistry<GenericTraitTemplatePool> poolTemplateRegistry,
      GenericTraitTemplateFactory templateFactory) {
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