package net.sf.anathema.character.presenter.initializers;

import net.sf.anathema.character.generic.framework.magic.view.CharmDescriptionProviderExtractor;
import net.sf.anathema.character.generic.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.generic.template.magic.ISpellMagicTemplate;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.presenter.magic.spells.SorceryModel;
import net.sf.anathema.character.presenter.magic.spells.SpellModel;
import net.sf.anathema.character.presenter.magic.spells.SpellPresenter;
import net.sf.anathema.character.view.SectionView;
import net.sf.anathema.character.view.magic.ISpellView;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.lib.resources.Resources;

public class SorceryInitializer implements CoreModelInitializer {
  private IApplicationModel model;

  public SorceryInitializer(IApplicationModel model) {
    this.model = model;
  }

  @Override
  public void initialize(SectionView sectionView, ICharacter character, Resources resources) {
    ISpellMagicTemplate spellMagic = character.getCharacterTemplate().getMagicTemplate().getSpellMagic();
    if (spellMagic.canLearnSorcery()) {
      String header = resources.getString("CardView.CharmConfiguration.Spells.Title");
      ISpellView view = sectionView.addView(header, ISpellView.class, character.getCharacterType());
      MagicDescriptionProvider magicDescriptionProvider = CharmDescriptionProviderExtractor.CreateFor(model, resources);
      SpellModel spellModel = new SorceryModel(character);
      new SpellPresenter(spellModel, character, resources, view, magicDescriptionProvider).initPresentation();
    }
  }
}
