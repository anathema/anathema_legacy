package net.sf.anathema.character.db.template.cult;

import net.sf.anathema.character.db.DbCharacterModule;
import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.impl.traits.ExaltTraitTemplateFactory;
import net.sf.anathema.character.generic.impl.traits.SimpleTraitTemplate;
import net.sf.anathema.character.generic.traits.LowerableState;
import net.sf.anathema.character.generic.traits.ITraitTemplate;

public abstract class AbstractCultDbTraitTemplateFactory extends ExaltTraitTemplateFactory {
  @Override
  public ITraitTemplate createBackgroundTemplate(IBackgroundTemplate template) {
    if (template.getId().equals(DbCharacterModule.BACKGROUND_ID_ILLUMINATION)) {
      return SimpleTraitTemplate.createStaticLimitedTemplate(0, 3, LowerableState.LowerableRegain);
    }
    return super.createBackgroundTemplate(template);
  }
}