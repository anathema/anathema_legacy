package net.sf.anathema.character.solar.model;

import net.sf.anathema.character.library.virtueflaw.model.DescriptiveVirtueFlawModelImpl;
import net.sf.anathema.character.main.model.othertraits.OtherTraitModel;
import net.sf.anathema.hero.initialization.SimpleModelTreeEntry;
import net.sf.anathema.hero.model.HeroModelAutoCollector;
import net.sf.anathema.hero.model.HeroModelFactory;
import net.sf.anathema.hero.template.TemplateFactory;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

@HeroModelAutoCollector
public class SolarVirtueFlawModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  private static final Identifier FACTORY_ID = new SimpleIdentifier("SolarVirtueFlaw");

  public SolarVirtueFlawModelFactory() {
    super(FACTORY_ID, OtherTraitModel.ID);
  }

  @Override
  public DescriptiveVirtueFlawModelImpl create(TemplateFactory templateFactory, String templateId) {
    return new DescriptiveVirtueFlawModelImpl();
  }
}