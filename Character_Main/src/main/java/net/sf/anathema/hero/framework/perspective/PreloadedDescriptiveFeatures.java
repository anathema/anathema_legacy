package net.sf.anathema.hero.framework.perspective;

import net.sf.anathema.character.framework.item.CharacterReferenceScanner;
import net.sf.anathema.hero.template.TemplateType;
import net.sf.anathema.hero.framework.perspective.model.CharacterIdentifier;
import net.sf.anathema.hero.framework.perspective.model.CharacterReference;
import net.sf.anathema.lib.util.Identifier;

public class PreloadedDescriptiveFeatures implements DescriptiveFeatures {

  private final CharacterReferenceScanner fileScanner;
  private final CharacterReference reference;

  public PreloadedDescriptiveFeatures(CharacterReferenceScanner fileScanner, CharacterReference reference) {
    this.fileScanner = fileScanner;
    this.reference = reference;
  }

  @Override
  public String getPrintName() {
    return reference.printName;
  }

  @Override
  public CharacterIdentifier getIdentifier() {
    String repositoryId = reference.repositoryId.getStringRepresentation();
    return new CharacterIdentifier(repositoryId);
  }

  @Override
  public TemplateType getTemplateType() {
    return fileScanner.getTemplateType(reference);
  }

  @Override
  public Identifier getCasteType() {
    return fileScanner.getCasteType(reference);
  }

  @Override
  public boolean isDirty() {
    return false;
  }
}
