package net.sf.anathema.character.main.xml.trait.types;

import net.sf.anathema.character.main.xml.registry.IXmlTemplateRegistry;
import net.sf.anathema.character.main.xml.trait.GenericTraitTemplateFactory;
import net.sf.anathema.character.main.xml.trait.pool.GenericTraitTemplatePool;
import net.sf.anathema.character.main.traits.groups.AllVirtueTraitTypeGroup;
import net.sf.anathema.character.main.traits.groups.TraitTypeGroup;

public class VirtuePoolParser extends AbstractPoolTemplateParser {

  private static final String TAG_VIRTUES = "virtues";

  public VirtuePoolParser(IXmlTemplateRegistry<GenericTraitTemplatePool> poolTemplateRegistry, GenericTraitTemplateFactory templateFactory) {
    super(poolTemplateRegistry, templateFactory);
  }

  @Override
  protected void executeResult(GenericTraitTemplatePool traitPool) {
    templateFactory.setVirtuesPool(traitPool);
  }

  @Override
  protected String getTagName() {
    return TAG_VIRTUES;
  }

  @Override
  protected TraitTypeGroup getTraitTypeGroup() {
    return AllVirtueTraitTypeGroup.getInstance();
  }
}