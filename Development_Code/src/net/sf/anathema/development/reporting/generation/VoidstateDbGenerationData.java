package net.sf.anathema.development.reporting.generation;

import java.io.File;

import net.sf.anathema.character.db.aspect.DBAspect;
import net.sf.anathema.character.generic.framework.reporting.template.ICharacterReportTemplate;
import net.sf.anathema.character.generic.framework.reporting.template.voidstate.ExaltVoidstateReportTemplate;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.impl.model.ExaltedCharacter;
import net.sf.anathema.character.impl.reporting.CharacterReport;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.framework.reporting.jasper.IJasperReport;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.lib.resources.IResources;

public class VoidstateDbGenerationData extends AbstractGenerationData implements IGenerationData {

  private final IResources resources;

  public VoidstateDbGenerationData(IResources resources) throws InitializationException {
    this.resources = resources;
  }

  public IItem createFilledCharacter() throws Exception {
    ExaltedCharacter character = createEmptyDb();
    createTestDescription(character.getDescription());
    ICharacterStatistics statistics = character.getStatistics();
    fillBasicStatistics(statistics);
    statistics.getCharacterConcept().getCaste().setType(DBAspect.Earth);
    return createItem(character);
  }

  private ExaltedCharacter createEmptyDb() throws Exception {
    ExaltedCharacter emptyCharacter = createEmptyCharacter();
    ICharacterTemplate defaultTemplate = container.getCharacterGenerics().getTemplateRegistry().getDefaultTemplate(
        CharacterType.DB,
        ExaltedEdition.FirstEdition);
    createStatistics(emptyCharacter, defaultTemplate);
    return emptyCharacter;
  }

  public IJasperReport createReport() {
    ICharacterReportTemplate template = new ExaltVoidstateReportTemplate(CharacterType.DB, resources);
    return new CharacterReport("Voidstate Db Character Sheet", template);
  }

  public File createFile() {
    return new File("Voidstate Dragon-Blooded.pdf");
  }
}