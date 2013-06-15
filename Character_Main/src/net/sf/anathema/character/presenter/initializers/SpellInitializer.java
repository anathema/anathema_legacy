package net.sf.anathema.character.presenter.initializers;

import net.sf.anathema.character.generic.framework.magic.view.CharmDescriptionProviderExtractor;
import net.sf.anathema.character.generic.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.presenter.magic.spells.SpellModel;
import net.sf.anathema.character.presenter.magic.spells.SpellPresenter;
import net.sf.anathema.character.view.SectionView;
import net.sf.anathema.character.view.magic.ISpellView;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.lib.resources.Resources;

public class SpellInitializer implements CoreModelInitializer {
  private IApplicationModel applicationModel;
  private final String titleKey;
  private final SpellModel spellModel;

  public SpellInitializer(IApplicationModel applicationModel, String titleKey, SpellModel spellModel) {
    this.applicationModel = applicationModel;
    this.titleKey = titleKey;
    this.spellModel = spellModel;
  }

  @Override
  public void initialize(SectionView sectionView, ICharacter character, Resources resources) {
    String header = resources.getString(titleKey);
    ISpellView view = sectionView.addView(header, ISpellView.class, character.getCharacterType());
    MagicDescriptionProvider magicDescriptionProvider = CharmDescriptionProviderExtractor.CreateFor(applicationModel, resources);
    new SpellPresenter(spellModel, character, resources, view, magicDescriptionProvider).initPresentation();
  }
}