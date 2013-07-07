package net.sf.anathema.character.main.presenter.initializers;

import net.sf.anathema.character.main.presenter.magic.spells.SorceryModel;
import net.sf.anathema.character.main.presenter.magic.spells.SpellModel;
import net.sf.anathema.character.model.view.SectionView;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.hero.model.Hero;
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
      SpellModel spellModel = new SorceryModel(hero);
      new SpellInitializer(model, titleKey, spellModel).initialize(sectionView, hero, resources);
    }
  }
}
