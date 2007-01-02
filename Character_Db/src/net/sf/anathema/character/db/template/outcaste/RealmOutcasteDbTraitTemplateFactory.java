package net.sf.anathema.character.db.template.outcaste;

import net.sf.anathema.character.db.DbCharacterModule;
import net.sf.anathema.character.db.template.dynastic.DynasticDbTraitTemplateFactory;
import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.impl.traits.SimpleTraitTemplate;
import net.sf.anathema.character.generic.traits.ITraitTemplate;

public class RealmOutcasteDbTraitTemplateFactory extends DynasticDbTraitTemplateFactory {

  @Override
  public ITraitTemplate createBackgroundTemplate(IBackgroundTemplate template) {
    if (template.getId().equals(DbCharacterModule.BACKGROUND_ID_BREEDING)) {
      return SimpleTraitTemplate.createStaticLimitedTemplate(0, 3, template.getExperiencedState());
    }
    return super.createBackgroundTemplate(template);
  }
}