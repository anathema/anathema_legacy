package net.sf.anathema.character.presenter.magic;

import net.sf.anathema.character.generic.caste.CasteType;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;
import net.sf.anathema.character.main.model.concept.HeroConceptFetcher;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.model.ITypedDescription;
import net.sf.anathema.character.main.model.charms.CharmsModel;
import net.sf.anathema.character.model.charm.ILearningCharmGroup;
import net.sf.anathema.lib.control.IChangeListener;

public class CharacterCharmModel {
  private ICharacter character;
  private MagicDescriptionProvider magicDescriptionProvider;

  public CharacterCharmModel(ICharacter character, MagicDescriptionProvider magicDescriptionProvider) {
    this.character = character;
    this.magicDescriptionProvider = magicDescriptionProvider;
  }

  public boolean isAllowedAlienCharms() {
    ICharmTemplate charmTemplate = character.getTemplate().getMagicTemplate().getCharmTemplate();
    return charmTemplate.isAllowedAlienCharms(getCaste().getType());
  }

  public void addCasteChangeListener(IChangeListener listener) {
    ITypedDescription<CasteType> caste = getCaste();
    caste.addChangeListener(listener);
  }

  public CharmsModel getCharmConfiguration() {
    return character.getCharms();
  }

  private ITypedDescription<CasteType> getCaste() {
    return HeroConceptFetcher.fetch(character).getCaste();
  }

  public void toggleLearned(String charmId) {
    CharmsModel charms = getCharmConfiguration();
    ILearningCharmGroup charmGroup = getCharmGroupByCharmId(charmId);
    charmGroup.toggleLearned(charms.getCharmById(charmId));
  }

  private ILearningCharmGroup getCharmGroupByCharmId(String charmId) {
    CharmsModel charms = getCharmConfiguration();
    ICharm charm = charms.getCharmById(charmId);
    return charms.getGroup(charm);
  }

  public MagicDescriptionProvider getMagicDescriptionProvider() {
    return magicDescriptionProvider;
  }
}
