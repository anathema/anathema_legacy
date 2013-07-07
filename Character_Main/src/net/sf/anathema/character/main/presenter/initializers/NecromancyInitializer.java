package net.sf.anathema.character.main.presenter.initializers;

import net.sf.anathema.character.main.magic.display.spells.NecromancyModel;
import net.sf.anathema.character.main.magic.display.spells.SpellModel;
import net.sf.anathema.character.main.view.SectionView;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.hero.model.Hero;
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
      SpellModel spellModel = new NecromancyModel(hero);
      new SpellInitializer(applicationModel, titleKey, spellModel).initialize(sectionView, hero, resources);
    }
  }
}
