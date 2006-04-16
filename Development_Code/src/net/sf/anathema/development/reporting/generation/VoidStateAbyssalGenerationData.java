package net.sf.anathema.development.reporting.generation;

import java.io.File;

import net.sf.anathema.character.abyssal.caste.AbyssalCaste;
import net.sf.anathema.character.generic.framework.reporting.template.ICharacterReportTemplate;
import net.sf.anathema.character.generic.framework.reporting.template.voidstate.DefaultVoidstateSubreports;
import net.sf.anathema.character.generic.framework.reporting.template.voidstate.ExaltVoidstateReportTemplate;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.impl.model.ExaltedCharacter;
import net.sf.anathema.character.impl.reporting.CharacterReport;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.character.model.charm.ILearningCharmGroup;
import net.sf.anathema.framework.reporting.jasper.IJasperReport;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.resources.IResources;

public class VoidStateAbyssalGenerationData extends AbstractGenerationData {

  private final IResources resources;

  public VoidStateAbyssalGenerationData(IResources resources) {
    this.resources = resources;
  }

  public IItem createFilledCharacter() throws Exception {
    ExaltedCharacter character = createEmptyAbyssal();
    createTestDescription(character.getDescription());
    ICharacterStatistics statistics = character.getStatistics();
    fillBasicStatistics(statistics);
    statistics.getCharacterConcept().getCaste().setType(AbyssalCaste.Daybreak);
    statistics.getTraitConfiguration().getTrait(AbilityType.MartialArts).setCurrentValue(5);
    statistics.getTraitConfiguration().getTrait(OtherTraitType.Essence).setCurrentValue(4);
    ICharmConfiguration charmConfiguration = statistics.getCharms();
    ICharm soulMasteryCharm = charmConfiguration.getCharmById("Dragon-Blooded.SoulMastery");
    ILearningCharmGroup woodDragonGroup = charmConfiguration.getGroup(soulMasteryCharm);
    woodDragonGroup.toggleLearned(soulMasteryCharm);
    return createItem(character);
  }

  private ExaltedCharacter createEmptyAbyssal() throws Exception {
    ExaltedCharacter emptyCharacter = createEmptyCharacter();
    ICharacterTemplate defaultTemplate = container.getCharacterGenerics().getTemplateRegistry().getDefaultTemplate(
        CharacterType.ABYSSAL,
        ExaltedEdition.FirstEdition);
    createStatistics(emptyCharacter, defaultTemplate);
    return emptyCharacter;
  }

  public IJasperReport createReport() {
    ICharacterReportTemplate template = new ExaltVoidstateReportTemplate(
        CharacterType.ABYSSAL,
        resources,
        new DefaultVoidstateSubreports(CharacterType.ABYSSAL));
    return new CharacterReport("Voidstate Abyssal Character Sheet", template);
  }

  public File createFile() {
    return new File("Voidstate Abyssal.pdf");
  }
}