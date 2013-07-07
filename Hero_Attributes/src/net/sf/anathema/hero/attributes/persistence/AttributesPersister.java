package net.sf.anathema.hero.attributes.persistence;

import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.hero.attributes.model.AttributeModel;
import net.sf.anathema.hero.persistence.AbstractModelJsonPersister;
import net.sf.anathema.hero.persistence.HeroModelPersisterCollected;
import net.sf.anathema.hero.traits.persistence.TraitPersister;
import net.sf.anathema.hero.traits.persistence.TraitPto;
import net.sf.anathema.lib.util.Identifier;

@HeroModelPersisterCollected
public class AttributesPersister extends AbstractModelJsonPersister<AttributesPto, AttributeModel> {

  private final TraitPersister traitPersister = new TraitPersister();

  public AttributesPersister() {
    super("attributes", AttributesPto.class);
  }

  @Override
  public Identifier getModelId() {
    return AttributeModel.ID;
  }

  @Override
  protected void fillModel(AttributeModel model, AttributesPto pto) {
    for (TraitPto traitPto : pto.traits) {
      Trait trait = model.getTrait(AttributeType.valueOf(traitPto.name));
      traitPersister.load(trait, traitPto);
    }
  }

  @Override
  protected AttributesPto createPto(AttributeModel model) {
    AttributesPto pto = new AttributesPto();
    for(Trait trait : model.getAll()) {
      TraitPto traitPto = new TraitPto();
      traitPersister.save(trait, traitPto);
      pto.traits.add(traitPto);
    }
    return pto;
  }
}
