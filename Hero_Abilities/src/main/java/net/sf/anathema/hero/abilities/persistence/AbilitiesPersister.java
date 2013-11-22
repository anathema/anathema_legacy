package net.sf.anathema.hero.abilities.persistence;

import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.traits.types.AbilityType;
import net.sf.anathema.hero.abilities.model.AbilitiesModel;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.persistence.AbstractModelJsonPersister;
import net.sf.anathema.hero.traits.persistence.TraitListPto;
import net.sf.anathema.hero.traits.persistence.TraitMapPersister;
import net.sf.anathema.hero.traits.persistence.TraitTypeMap;
import net.sf.anathema.lib.util.Identifier;

@SuppressWarnings("UnusedDeclaration")
public class AbilitiesPersister extends AbstractModelJsonPersister<TraitListPto, AbilitiesModel> {

  private final TraitMapPersister traitMapPersister = new TraitMapPersister(new TraitTypeMap() {
    @Override
    public TraitType get(String id) {
      return AbilityType.valueOf(id);
    }
  });

  public AbilitiesPersister() {
    super("abilities", TraitListPto.class);
  }

  @Override
  public Identifier getModelId() {
    return AbilitiesModel.ID;
  }

  @Override
  protected void loadModelFromPto(Hero hero, AbilitiesModel model, TraitListPto pto) {
    traitMapPersister.loadTraitMap(model, pto);
  }

  @Override
  protected TraitListPto saveModelToPto(AbilitiesModel model) {
    return traitMapPersister.saveTraitMap(model);
  }
}
