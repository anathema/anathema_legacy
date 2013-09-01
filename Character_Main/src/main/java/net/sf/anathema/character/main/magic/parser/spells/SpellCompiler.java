package net.sf.anathema.character.main.magic.parser.spells;

import net.sf.anathema.character.main.framework.data.ExtensibleDataSet;
import net.sf.anathema.character.main.framework.data.IExtensibleDataSetCompiler;
import net.sf.anathema.character.main.magic.spells.Spell;
import net.sf.anathema.character.main.magic.spells.SpellException;
import net.sf.anathema.initialization.ExtensibleDataSetCompiler;
import net.sf.anathema.initialization.ObjectFactory;
import net.sf.anathema.framework.environment.resources.ResourceFile;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.util.ArrayList;
import java.util.List;

@ExtensibleDataSetCompiler
public class SpellCompiler implements IExtensibleDataSetCompiler {
  private static final String Spell_File_Recognition_Pattern = "Spells.*\\.xml";
  private final List<Document> spellFileList = new ArrayList<>();
  private final SpellBuilder builder = new SpellBuilder();
  private final SAXReader reader = new SAXReader();
  private final SpellCache cache = new SpellCache();

  @SuppressWarnings("UnusedParameters")
  public SpellCompiler(ObjectFactory objectFactory) {
    //nothing to do
  }

  @Override
  public String getName() {
    return "Spells";
  }

  @Override
  public String getRecognitionPattern() {
    return Spell_File_Recognition_Pattern;
  }

  @Override
  public ExtensibleDataSet build() {
    for (Document document : spellFileList) {
      Spell[] spells = builder.buildSpells(document);
      for (Spell spell : spells) {
        cache.addSpell(spell);
      }
    }
    return cache;
  }

  @Override
  public void registerFile(ResourceFile resource) throws Exception {
    try {
      spellFileList.add(reader.read(resource.getURL()));
    } catch (DocumentException e) {
      throw new SpellException(resource.getURL().toExternalForm(), e);
    }
  }
}
