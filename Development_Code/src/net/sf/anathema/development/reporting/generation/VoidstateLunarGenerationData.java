package net.sf.anathema.development.reporting.generation;

import java.io.File;

import net.sf.anathema.character.generic.framework.reporting.template.ICharacterReportTemplate;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.impl.model.ExaltedCharacter;
import net.sf.anathema.character.impl.reporting.CharacterReport;
import net.sf.anathema.character.lunar.reporting.LunarVoidstateReportTemplate;
import net.sf.anathema.framework.reporting.jasper.IJasperReport;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.resources.IResources;

public class VoidstateLunarGenerationData extends AbstractGenerationData {
  private final IResources resources;

  public VoidstateLunarGenerationData(IResources resources) {
    this.resources = resources;
  }

  public IItem createFilledCharacter() throws Exception {
    ExaltedCharacter emptyCharacter = createEmptyCharacter();
    ICharacterTemplate defaultTemplate = container.getCharacterGenerics().getTemplateRegistry().getDefaultTemplate(
        CharacterType.LUNAR,
        ExaltedEdition.FirstEdition);
    createStatistics(emptyCharacter, defaultTemplate);
    return createItem(emptyCharacter);
  }

  public IJasperReport createReport() {
    ICharacterReportTemplate template = new LunarVoidstateReportTemplate(resources);
    return new CharacterReport("Voidstate Lunar Character Sheet", template);
  }

  public File createFile() {
    return new File("Voidstate Lunar.pdf");
  }
}