package net.sf.anathema.hero.experience.persistence;

import net.sf.anathema.character.main.advance.ExperiencePointEntry;
import net.sf.anathema.hero.experience.ExperienceModel;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.persistence.AbstractModelJsonPersister;
import net.sf.anathema.hero.persistence.RegisteredHeroModelPersister;
import net.sf.anathema.lib.util.Identifier;

@RegisteredHeroModelPersister
public class ExperiencePersister extends AbstractModelJsonPersister<ExperiencePto, ExperienceModel> {

  public ExperiencePersister() {
    super("experience", ExperiencePto.class);
  }

  @Override
  public Identifier getModelId() {
    return ExperienceModel.ID;
  }

  @Override
  protected void loadModelFromPto(Hero hero, ExperienceModel model, ExperiencePto pto) {
    model.setExperienced(pto.isExperienced);
    for (ExperiencePointsPto pointsPto : pto.experiencePoints) {
      ExperiencePointEntry entry = model.getExperiencePoints().addEntry();
      loadPointsEntry(pointsPto, entry);
    }
  }

  private void loadPointsEntry(ExperiencePointsPto pointsPto, ExperiencePointEntry entry) {
    entry.setExperiencePoints(pointsPto.points);
    entry.getTextualDescription().setText(pointsPto.description);
  }

  @Override
  protected ExperiencePto saveModelToPto(ExperienceModel model) {
    ExperiencePto pto = new ExperiencePto();
    pto.isExperienced = model.isExperienced();
    for (ExperiencePointEntry entry : model.getExperiencePoints().getAllEntries()) {
      ExperiencePointsPto pointsPto = createExperiencePointsPto(entry);
      pto.experiencePoints.add(pointsPto);
    }
    return pto;
  }

  private ExperiencePointsPto createExperiencePointsPto(ExperiencePointEntry entry) {
    ExperiencePointsPto pointsPto = new ExperiencePointsPto();
    pointsPto.description = entry.getTextualDescription().getText();
    pointsPto.points = entry.getExperiencePoints();
    return pointsPto;
  }
}
