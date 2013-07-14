package net.sf.anathema.character.main.xml.trait.types;

import net.sf.anathema.character.main.traits.groups.AllAbilityTraitTypeGroup;
import net.sf.anathema.character.main.traits.groups.TraitTypeGroup;
import net.sf.anathema.character.main.xml.registry.IXmlTemplateRegistry;
import net.sf.anathema.character.main.xml.trait.GenericTraitTemplateFactory;
import net.sf.anathema.character.main.xml.trait.pool.GenericTraitTemplatePool;

public class AbilityPoolParser extends AbstractPoolTemplateParser {

  private static final String TAG_ABILITIES = "abilities";

  public AbilityPoolParser(IXmlTemplateRegistry<GenericTraitTemplatePool> poolTemplateRegistry, GenericTraitTemplateFactory templateFactory) {
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
  protected void executeResult(GenericTraitTemplatePool traitPool) {
    templateFactory.setAbilitiesPool(traitPool);
  }
}