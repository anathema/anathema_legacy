package net.sf.anathema.character.perspective;

import net.sf.anathema.character.CharacterPrintNameFileScanner;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.perspective.model.model.CharacterIdentifier;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.util.Identified;

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
  public Identified getCasteType() {
    return fileScanner.getCasteType(printNameFile);
  }

  @Override
  public boolean isDirty() {
    return false;
  }
}
