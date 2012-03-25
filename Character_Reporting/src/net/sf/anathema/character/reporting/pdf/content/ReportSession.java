package net.sf.anathema.character.reporting.pdf.content;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;

import java.util.ArrayList;
import java.util.List;

public class ReportSession {

  private final IGenericCharacter character;
  private final IGenericDescription description;
  private final ReportContentRegistry registry;
  private final List<Object> mnemonics = new ArrayList<Object>();

  public ReportSession(ReportContentRegistry registry, IGenericCharacter character, IGenericDescription description) {
    this.registry = registry;
    this.character = character;
    this.description = description;
  }

  public IGenericCharacter getCharacter() {
    return character;
  }

  public IGenericDescription getDescription() {
    return description;
  }

  public void storeMnemonic(Object mnemonic) {
    mnemonics.add(mnemonic);
  }
  
  public <T> T retrieveMnemonic(Class<T> mnemonicClass) {
    for (Object mnemonic : mnemonics) {
      if (mnemonic.getClass() == mnemonicClass) {
        return (T) mnemonic;
      }
    }
    return null;
  }
  
  public <T> boolean knowsMnemonic(Class<T> mnemonicClass) {
    return retrieveMnemonic(mnemonicClass) != null;
  }

  public <C extends SubContent> C createContent(Class<C> contentClass) {
    ReportContentFactory<C> factory = registry.getFactory(contentClass);
    return factory.create(this, character, description);
  }
}
