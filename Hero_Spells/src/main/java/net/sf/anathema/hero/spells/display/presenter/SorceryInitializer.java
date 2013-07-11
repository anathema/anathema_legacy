package net.sf.anathema.hero.spells.display.presenter;

import net.sf.anathema.character.main.presenter.initializers.HeroModelInitializer;
import net.sf.anathema.character.main.presenter.initializers.RegisteredInitializer;
import net.sf.anathema.character.main.view.SectionView;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.spells.model.CircleModel;
import net.sf.anathema.hero.spells.model.SorceryModel;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.Resources;

import static net.sf.anathema.hero.display.HeroModelGroup.Magic;

@RegisteredInitializer(Magic)
@Weight(weight = 200)
public class SorceryInitializer implements HeroModelInitializer {
  private IApplicationModel model;

  public SorceryInitializer(IApplicationModel model) {
    this.model = model;
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero, Resources resources) {
    boolean canLeanSorcery = hero.getTemplate().getMagicTemplate().getSpellMagic().canLearnSorcery();
    if (canLeanSorcery) {
      String titleKey = "CardView.CharmConfiguration.Spells.Title";
      CircleModel circleModel = new SorceryModel(hero);
      new SpellInitializer(model, titleKey, circleModel).initialize(sectionView, hero, resources);
    }
  }
}
