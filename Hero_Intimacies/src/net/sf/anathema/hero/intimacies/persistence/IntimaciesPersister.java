package net.sf.anathema.hero.intimacies.persistence;

import net.sf.anathema.hero.intimacies.model.IntimaciesModel;
import net.sf.anathema.hero.intimacies.model.Intimacy;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.persistence.AbstractModelJsonPersister;
import net.sf.anathema.hero.persistence.HeroModelPersisterCollected;
import net.sf.anathema.lib.util.Identifier;

@HeroModelPersisterCollected
public class IntimaciesPersister extends AbstractModelJsonPersister<IntimaciesPto, IntimaciesModel> {

  public IntimaciesPersister() {
    super("intimacies", IntimaciesPto.class);
  }

  @Override
  protected void loadModelFromPto(Hero hero, IntimaciesModel model, IntimaciesPto pto) {
    for (IntimacyPto intimacyPto : pto.intimacies) {
      model.setCurrentName(intimacyPto.name);
      Intimacy intimacy = model.commitSelection();
      intimacy.getTrait().setUncheckedCreationValue(intimacyPto.creationValue);
      if (intimacyPto.experiencedValue != null) {
        intimacy.getTrait().setUncheckedExperiencedValue(intimacyPto.experiencedValue);
      }
      intimacy.setComplete(intimacyPto.complete);
    }
  }

  @Override
  protected IntimaciesPto saveModelToPto(IntimaciesModel heroModel) {
    IntimaciesPto intimaciesList = new IntimaciesPto();
    for (Intimacy intimacy : heroModel.getEntries()) {
      intimaciesList.intimacies.add(createIntimacyPto(intimacy));
    }
    return intimaciesList;
  }

  private IntimacyPto createIntimacyPto(Intimacy intimacy) {
    IntimacyPto pto = new IntimacyPto();
    pto.name = intimacy.getName();
    pto.complete = intimacy.isComplete();
    pto.creationValue = intimacy.getTrait().getCreationValue();
    if (intimacy.getTrait().getExperiencedValue() >= 0) {
      pto.experiencedValue = intimacy.getTrait().getExperiencedValue();
    }
    return pto;
  }

  @Override
  public Identifier getModelId() {
    return IntimaciesModel.ID;
  }
}
