package net.sf.anathema.character.presenter.initializers;

import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.presenter.magic.spells.SorceryModel;
import net.sf.anathema.character.presenter.magic.spells.SpellModel;
import net.sf.anathema.character.view.SectionView;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.lib.resources.Resources;

import static net.sf.anathema.character.model.CharacterModelGroup.Magic;

@RegisteredInitializer(Magic)
public class SorceryInitializer implements CoreModelInitializer {
  private IApplicationModel model;

  public SorceryInitializer(IApplicationModel model) {
    this.model = model;
  }

  @Override
  public void initialize(SectionView sectionView, ICharacter character, Resources resources) {
    boolean canLeanSorcery = character.getCharacterTemplate().getMagicTemplate().getSpellMagic().canLearnSorcery();
    if (canLeanSorcery) {
      String titleKey = "CardView.CharmConfiguration.Spells.Title";
      SpellModel spellModel = new SorceryModel(character);
      new SpellInitializer(model, titleKey, spellModel).initialize(sectionView, character, resources);
    }
  }
}
