package net.sf.anathema.hero.framework.perspective;

import net.sf.anathema.character.main.framework.item.CharacterPrintNameFileScanner;
import net.sf.anathema.character.main.template.ITemplateType;
import net.sf.anathema.hero.framework.perspective.model.CharacterIdentifier;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.util.Identifier;

public class PreloadedDescriptiveFeatures implements DescriptiveFeatures {

  private CharacterPrintNameFileScanner fileScanner;
  private PrintNameFile printNameFile;

  public PreloadedDescriptiveFeatures(CharacterPrintNameFileScanner fileScanner, PrintNameFile printNameFile) {
    this.fileScanner = fileScanner;
    this.printNameFile = printNameFile;
  }

  @Override
  public String getPrintName() {
    return printNameFile.getPrintName();
  }

  @Override
  public CharacterIdentifier getIdentifier() {
    String repositoryId = printNameFile.getRepositoryId();
    return new CharacterIdentifier(repositoryId);
  }

  @Override
  public ITemplateType getTemplateType() {
    return fileScanner.getTemplateType(printNameFile);
  }

  @Override
  public Identifier getCasteType() {
    return fileScanner.getCasteType(printNameFile);
  }

  @Override
  public boolean isDirty() {
    return false;
  }
}
