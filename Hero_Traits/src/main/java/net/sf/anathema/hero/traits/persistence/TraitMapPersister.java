package net.sf.anathema.hero.traits.persistence;

import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitMap;

public class TraitMapPersister {

  private final TraitPersister traitPersister = new TraitPersister();
  private TraitTypeMap traitTypeMap;

  public TraitMapPersister(TraitTypeMap traitTypeMap) {
    this.traitTypeMap = traitTypeMap;
  }

  public void loadTraitMap(TraitMap model, TraitListPto pto) {
    for (TraitPto traitPto : pto.traits) {
      Trait trait = model.getTrait(traitTypeMap.get(traitPto.name));
      traitPersister.load(trait, traitPto);
    }
  }

  public TraitListPto saveTraitMap(TraitMap model) {
    TraitListPto pto = new TraitListPto();
    for(Trait trait : model.getAll()) {
      TraitPto traitPto = new TraitPto();
      traitPersister.save(trait, traitPto);
      pto.traits.add(traitPto);
    }
    return pto;
  }
}
