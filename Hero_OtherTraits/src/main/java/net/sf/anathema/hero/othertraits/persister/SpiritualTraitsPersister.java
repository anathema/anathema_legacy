package net.sf.anathema.hero.othertraits.persister;

import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.othertraits.OtherTraitModel;
import net.sf.anathema.hero.persistence.AbstractModelJsonPersister;
import net.sf.anathema.hero.persistence.RegisteredHeroModelPersister;
import net.sf.anathema.hero.traits.persistence.TraitListPto;
import net.sf.anathema.hero.traits.persistence.TraitMapPersister;
import net.sf.anathema.lib.util.Identifier;

@RegisteredHeroModelPersister
public class SpiritualTraitsPersister extends AbstractModelJsonPersister<TraitListPto, OtherTraitModel> {

  private final TraitMapPersister traitMapPersister = new TraitMapPersister(new SpiritualTraitTypeMap());

  public SpiritualTraitsPersister() {
    super("spiritualTraits", TraitListPto.class);
  }

  @Override
  public Identifier getModelId() {
    return OtherTraitModel.ID;
  }

  @Override
  protected void loadModelFromPto(Hero hero, OtherTraitModel model, TraitListPto pto) {
    traitMapPersister.loadTraitMap(model, pto);
  }

  @Override
  protected TraitListPto saveModelToPto(OtherTraitModel model) {
    return traitMapPersister.saveTraitMap(model);
  }

}
