package net.sf.anathema.hero.attributes.persistence;

import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.types.AttributeType;
import net.sf.anathema.hero.attributes.model.AttributeModel;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.persistence.AbstractModelJsonPersister;
import net.sf.anathema.hero.traits.persistence.TraitListPto;
import net.sf.anathema.hero.traits.persistence.TraitMapPersister;
import net.sf.anathema.hero.traits.persistence.TraitTypeMap;
import net.sf.anathema.lib.util.Identifier;

@SuppressWarnings("UnusedDeclaration")
public class AttributesPersister extends AbstractModelJsonPersister<TraitListPto, AttributeModel> {

  private final TraitMapPersister traitMapPersister = new TraitMapPersister(new TraitTypeMap() {
    @Override
    public TraitType get(String id) {
      return AttributeType.valueOf(id);
    }
  });

  public AttributesPersister() {
    super("attributes", TraitListPto.class);
  }

  @Override
  public Identifier getModelId() {
    return AttributeModel.ID;
  }

  @Override
  protected void loadModelFromPto(Hero hero, AttributeModel model, TraitListPto pto) {
    traitMapPersister.loadTraitMap(model, pto);
  }

  @Override
  protected TraitListPto saveModelToPto(AttributeModel model) {
    return traitMapPersister.saveTraitMap(model);
  }

}
