package net.sf.anathema.character.generic.framework.xml.trait.types;

import net.sf.anathema.character.generic.framework.xml.registry.IXmlTemplateRegistry;
import net.sf.anathema.character.generic.framework.xml.trait.GenericTraitTemplateFactory;
import net.sf.anathema.character.generic.framework.xml.trait.pool.GenericTraitTemplatePool;
import net.sf.anathema.character.generic.traits.groups.AllAbilityTraitTypeGroup;
import net.sf.anathema.character.generic.traits.groups.TraitTypeGroup;

public class AbilityPoolParser extends AbstractPoolTemplateParser {

  private static final String TAG_ABILITIES = "abilities"; //$NON-NLS-1$

  public AbilityPoolParser(
      IXmlTemplateRegistry<GenericTraitTemplatePool> poolTemplateRegistry,
      GenericTraitTemplateFactory templateFactory) {
    super(poolTemplateRegistry, templateFactory);
  }

  @Override
  protected String getTagName() {
    return TAG_ABILITIES;
  }

  @Override
  protected TraitTypeGroup getTraitTypeGroup() {
    return AllAbilityTraitTypeGroup.getInstance();
  }

  @Override
  protected void executeResult(final GenericTraitTemplatePool traitPool) {
    templateFactory.setAbilitiesPool(traitPool);
  }
}