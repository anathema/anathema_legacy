package net.sf.anathema.character.main.presenter.initializers;

import net.sf.anathema.character.main.magic.display.view.charms.CharmDescriptionProviderExtractor;
import net.sf.anathema.character.main.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.main.magic.display.spells.SpellModel;
import net.sf.anathema.character.main.magic.display.spells.SpellPresenter;
import net.sf.anathema.character.main.view.SectionView;
import net.sf.anathema.character.main.magic.display.view.spells.ISpellView;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.resources.Resources;

public class SpellInitializer implements HeroModelInitializer {
  private IApplicationModel applicationModel;
  private final String titleKey;
  private final SpellModel spellModel;

  public SpellInitializer(IApplicationModel applicationModel, String titleKey, SpellModel spellModel) {
    this.applicationModel = applicationModel;
    this.titleKey = titleKey;
    this.spellModel = spellModel;
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero, Resources resources) {
    String header = resources.getString(titleKey);
    ISpellView view = sectionView.addView(header, ISpellView.class, hero.getTemplate().getTemplateType().getCharacterType());
    MagicDescriptionProvider magicDescriptionProvider = CharmDescriptionProviderExtractor.CreateFor(applicationModel, resources);
    new SpellPresenter(spellModel, hero, resources, view, magicDescriptionProvider).initPresentation();
  }
}