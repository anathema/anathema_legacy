package net.sf.anathema.character.presenter.initializers;

import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.presenter.magic.spells.NecromancyModel;
import net.sf.anathema.character.presenter.magic.spells.SpellModel;
import net.sf.anathema.character.view.SectionView;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.lib.resources.Resources;

import static net.sf.anathema.character.model.CharacterModelGroup.Magic;

@RegisteredInitializer(Magic)
public class NecromancyInitializer implements CoreModelInitializer {

  private IApplicationModel applicationModel;

  public NecromancyInitializer(IApplicationModel applicationModel) {
    this.applicationModel = applicationModel;
  }

  @Override
  public void initialize(SectionView sectionView, ICharacter character, Resources resources) {
    boolean canLeanNecromancy = character.getCharacterTemplate().getMagicTemplate().getSpellMagic().canLearnNecromancy();
    if (canLeanNecromancy) {
      String titleKey = "CardView.CharmConfiguration.Necromancy.Title";
      SpellModel spellModel = new NecromancyModel(character);
      new SpellInitializer(applicationModel, titleKey, spellModel).initialize(sectionView, character, resources);
    }
  }
}
