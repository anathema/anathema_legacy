package net.sf.anathema.character.reporting.pdf.content;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.generic.magic.IMagicStats;

import java.util.ArrayList;
import java.util.List;

public class ReportSession {

  private final IGenericCharacter character;
  private final IGenericDescription description;
  private final ReportContentRegistry registry;
  private final List<IMagicStats> printMagic = new ArrayList<IMagicStats>();

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

  public void addPrintMagic(List<IMagicStats> printMagic) {
    this.printMagic.addAll(printMagic);
  }

  public void removePrintMagic(IMagicStats printMagic) {
    this.printMagic.remove(printMagic);
  }
  
  public List<IMagicStats> getPrintMagic() {
    return new ArrayList<IMagicStats>(printMagic);
  }

  public <C extends SubContent> C createContent(Class<C> contentClass) {
    ReportContentFactory<C> factory = registry.getFactory(contentClass);
    return factory.create(character, description);
  }
}
