package net.sf.anathema.development.reporting.generation;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.impl.model.ExaltedCharacter;
import net.sf.anathema.character.impl.model.charm.special.OxBodyTechniqueConfiguration;
import net.sf.anathema.character.impl.util.GenericCharacterUtilities;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.ISpellConfiguration;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.character.model.charm.ILearningCharmGroup;
import net.sf.anathema.character.sidereal.colleges.SiderealCollegeTemplate;
import net.sf.anathema.character.sidereal.colleges.model.CollegeType;
import net.sf.anathema.character.sidereal.colleges.model.SiderealCollegeModel;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.initialization.InitializationException;

public abstract class AbstractSiderealGenerationData extends AbstractGenerationData {

  public AbstractSiderealGenerationData() throws InitializationException {
    super();
  }

  private IItem createEmptySidereal() throws Exception {
    ExaltedCharacter emptyCharacter = createEmptyCharacter();
    ICharacterTemplate defaultTemplate = container.getCharacterGenerics().getTemplateRegistry().getDefaultTemplate(
        CharacterType.SIDEREAL,
        ExaltedEdition.FirstEdition);
    createStatistics(emptyCharacter, defaultTemplate);
    return createItem(emptyCharacter);
  }

  public final IItem createFilledCharacter() throws Exception {
    IItem item = createEmptySidereal();
    ExaltedCharacter exaltedCharacter = (ExaltedCharacter) item.getItemData();
    createTestDescription(exaltedCharacter.getDescription());
    ICharacterStatistics statistics = exaltedCharacter.getStatistics();
    IGenericCharacter character = GenericCharacterUtilities.createGenericCharacter(statistics);
    fillBasicStatistics(statistics);
    statistics.getCharacterConcept().getCaste().setType(net.sf.anathema.character.sidereal.caste.SiderealCaste.Secrets);
    ICharmConfiguration charms = statistics.getCharms();
    ILearningCharmGroup dodgeGroup = charms.getGroup(charms.getCharmById("Sidereal.Absence"));
    dodgeGroup.learnCharm(charms.getCharmById("Sidereal.Absence"), false); //$NON-NLS-1$
    dodgeGroup.learnCharm(charms.getCharmById("Sidereal.DuckFate"), false); //$NON-NLS-1$
    ILearningCharmGroup occultGroup = charms.getGroup(charms.getCharmById("Sidereal.TerrestrialCircleSorcery"));
    occultGroup.learnCharm(charms.getCharmById("Sidereal.TerrestrialCircleSorcery"), false); //$NON-NLS-1$
    ((OxBodyTechniqueConfiguration) charms.getSpecialCharmConfiguration("Sidereal.Ox-BodyTechnique")).getCategoryById( //$NON-NLS-1$
        "Category.-0").setCurrentValue(2); //$NON-NLS-1$
    ISpellConfiguration spells = statistics.getSpells();
    spells.addSpells(new ISpell[] { spells.getSpellById("Terrestrial.InvulnerableSkinBronze") }); //$NON-NLS-1$
    SiderealCollegeModel collegeModel = (SiderealCollegeModel) character.getAdditionalModel(SiderealCollegeTemplate.ID);
    collegeModel.getCollege(CollegeType.Banner).setCurrentValue(3);
    return item;
  }
}