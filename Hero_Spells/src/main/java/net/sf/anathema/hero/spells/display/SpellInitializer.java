package net.sf.anathema.hero.spells.display;

import net.sf.anathema.character.main.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.main.magic.display.view.charms.CharmDescriptionProviderExtractor;
import net.sf.anathema.character.main.presenter.initializers.HeroModelInitializer;
import net.sf.anathema.character.main.view.SectionView;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.spells.model.CircleModel;
import net.sf.anathema.lib.resources.Resources;

public class SpellInitializer implements HeroModelInitializer {
  private IApplicationModel applicationModel;
  private final String titleKey;
  private final CircleModel circleModel;

  public SpellInitializer(IApplicationModel applicationModel, String titleKey, CircleModel circleModel) {
    this.applicationModel = applicationModel;
    this.titleKey = titleKey;
    this.circleModel = circleModel;
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero, Resources resources) {
    String header = resources.getString(titleKey);
    ISpellView view = sectionView.addView(header, ISpellView.class, hero.getTemplate().getTemplateType().getCharacterType());
    MagicDescriptionProvider magicDescriptionProvider = CharmDescriptionProviderExtractor.CreateFor(applicationModel, resources);
    new SpellPresenter(circleModel, hero, resources, view, magicDescriptionProvider).initPresentation();
  }
}