package net.sf.anathema.hero.abilities.persistence;

import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.traits.types.AbilityType;
import net.sf.anathema.hero.abilities.model.AbilitiesModel;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.persistence.AbstractModelJsonPersister;
import net.sf.anathema.hero.persistence.RegisteredHeroModelPersister;
import net.sf.anathema.hero.traits.persistence.TraitPersister;
import net.sf.anathema.hero.traits.persistence.TraitPto;
import net.sf.anathema.lib.util.Identifier;

@RegisteredHeroModelPersister
public class AbilitiesPersister extends AbstractModelJsonPersister<AbilitiesPto, AbilitiesModel> {

  private final TraitPersister traitPersister = new TraitPersister();

  public AbilitiesPersister() {
    super("abilities", AbilitiesPto.class);
  }

  @Override
  public Identifier getModelId() {
    return AbilitiesModel.ID;
  }

  @Override
  protected void loadModelFromPto(Hero hero, AbilitiesModel model, AbilitiesPto pto) {
    for (TraitPto traitPto : pto.traits) {
      Trait trait = model.getTrait(AbilityType.valueOf(traitPto.name));
      traitPersister.load(trait, traitPto);
    }
  }

  @Override
  protected AbilitiesPto saveModelToPto(AbilitiesModel model) {
    AbilitiesPto pto = new AbilitiesPto();
    for(Trait trait : model.getAll()) {
      TraitPto traitPto = new TraitPto();
      traitPersister.save(trait, traitPto);
      pto.traits.add(traitPto);
    }
    return pto;
  }
}
