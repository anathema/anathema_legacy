package net.sf.anathema.hero.languages.persistence;

import net.sf.anathema.hero.languages.model.LanguagesModel;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.persistence.AbstractModelJsonPersister;
import net.sf.anathema.hero.persistence.HeroModelPersisterCollected;
import net.sf.anathema.lib.util.Identifier;

@HeroModelPersisterCollected
public class LanguagesPersister extends AbstractModelJsonPersister<LanguagesPto, LanguagesModel> {

  public LanguagesPersister() {
    super("languages", LanguagesPto.class);
  }

  @Override
  public Identifier getModelId() {
    return LanguagesModel.ID;
  }

  protected void loadModelFromPto(Hero hero, LanguagesModel model, LanguagesPto pto) {
     for (String name : pto.languages) {
      Identifier language = model.getPredefinedLanguageById(name);
      if (language != null) {
        model.selectLanguage(language);
      } else {
        model.selectBarbarianLanguage(name);
      }
      model.commitSelection();
    }
  }

  @Override
  protected LanguagesPto saveModelToPto(LanguagesModel languages) {
    LanguagesPto pto = new LanguagesPto();
    for (Identifier language : languages.getEntries()) {
      pto.languages.add(language.getId());
    }
    return pto;
  }
}