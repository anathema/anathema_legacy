package net.sf.anathema.development.reporting.generation;

import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.impl.model.ExaltedCharacter;
import net.sf.anathema.character.impl.model.charm.special.OxBodyTechniqueConfiguration;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.ISpellConfiguration;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.character.model.charm.ILearningCharmGroup;
import net.sf.anathema.character.solar.caste.SolarCaste;
import net.sf.anathema.character.solar.virtueflaw.model.ISolarVirtueFlaw;
import net.sf.anathema.character.solar.virtueflaw.presenter.ISolarVirtueFlawModel;
import net.sf.anathema.framework.repository.IItem;

public abstract class AbstractSolarGenerationData extends AbstractGenerationData {

  private IItem createEmptySolar() throws Exception {
    ExaltedCharacter emptyCharacter = createEmptyCharacter();
    ICharacterTemplate defaultTemplate = container.getCharacterGenerics().getTemplateRegistry().getDefaultTemplate(
        CharacterType.SOLAR,ExaltedEdition.FirstEdition);
    createStatistics(emptyCharacter, defaultTemplate);
    return createItem(emptyCharacter);
  }

  public final IItem createFilledCharacter() throws Exception {
    IItem item = createEmptySolar();
    ICharacter character = (ICharacter) item.getItemData();
    createTestDescription(character.getDescription());
    ICharacterStatistics statistics = character.getStatistics();
    fillBasicStatistics(statistics);
    ISolarVirtueFlaw virtueFlaw = ((ISolarVirtueFlawModel) statistics.getExtendedConfiguration().getAdditionalModel(
        IAdditionalTemplate.SOLAR_VIRTUE_FLAW_ID)).getVirtueFlaw();
    virtueFlaw.setRoot(VirtueType.Compassion);
    virtueFlaw.getName().setText("Red Rage of Compassion"); //$NON-NLS-1$
    virtueFlaw.getLimitBreak().setText("The character witnesses humans in despair or suffering."); //$NON-NLS-1$
    statistics.getCharacterConcept().getCaste().setType(SolarCaste.Dawn);
    ICharmConfiguration charms = statistics.getCharms();
    ILearningCharmGroup occultGroup = charms.getGroup(charms.getCharmById("Solar.All-EncompassingSorcerer'sSight")); //$NON-NLS-1$
    occultGroup.learnCharm(charms.getCharmById("Solar.All-EncompassingSorcerer'sSight"), false); //$NON-NLS-1$
    occultGroup.learnCharm(charms.getCharmById("Solar.TerrestrialCircleSorcery"), false); //$NON-NLS-1$
    ILearningCharmGroup sailGroup = charms.getGroup(charms.getCharmById("Solar.HardenHullPractice")); //$NON-NLS-1$
    sailGroup.learnCharm(charms.getCharmById("Solar.HardenHullPractice"), false); //$NON-NLS-1$//$NON-NLS-2$
    ILearningCharmGroup meleeGroup = charms.getGroup(charms.getCharmById("Solar.PeonyBlossomAttack")); //$NON-NLS-1$
    meleeGroup.learnCharm(charms.getCharmById("Solar.PeonyBlossomAttack"), false); //$NON-NLS-1$//$NON-NLS-2$
    ((OxBodyTechniqueConfiguration) charms.getSpecialCharmConfiguration("Solar.Ox-BodyTechnique")).getCategoryById( //$NON-NLS-1$
        "Category.-1x2").setCurrentValue(2); //$NON-NLS-1$
    ISpellConfiguration spells = statistics.getSpells();
    spells.addSpells(new ISpell[] { spells.getSpellById("Terrestrial.InvulnerableSkinBronze") }); //$NON-NLS-1$
    return item;
  }
}