package net.sf.anathema.hero.spells.display;

import net.sf.anathema.character.main.presenter.initializers.HeroModelInitializer;
import net.sf.anathema.character.main.presenter.initializers.RegisteredInitializer;
import net.sf.anathema.character.main.view.SectionView;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.spells.model.CircleModel;
import net.sf.anathema.hero.spells.model.NecromancyModel;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.Resources;

import static net.sf.anathema.hero.display.HeroModelGroup.Magic;

@RegisteredInitializer(Magic)
@Weight(weight = 300)
public class NecromancyInitializer implements HeroModelInitializer {

  private IApplicationModel applicationModel;

  public NecromancyInitializer(IApplicationModel applicationModel) {
    this.applicationModel = applicationModel;
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero, Resources resources) {
    boolean canLeanNecromancy = hero.getTemplate().getMagicTemplate().getSpellMagic().canLearnNecromancy();
    if (canLeanNecromancy) {
      String titleKey = "CardView.CharmConfiguration.Necromancy.Title";
      CircleModel circleModel = new NecromancyModel(hero);
      new SpellInitializer(applicationModel, titleKey, circleModel).initialize(sectionView, hero, resources);
    }
  }
}
