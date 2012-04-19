package net.sf.anathema.character.generic.impl.magic.persistence;

import net.sf.anathema.character.generic.impl.magic.SpellException;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.initialization.ExtensibleDataSetCompiler;
import net.sf.anathema.initialization.IExtensibleDataSetCompiler;
import net.sf.anathema.lib.resources.IAnathemaResourceFile;
import net.sf.anathema.lib.resources.IExtensibleDataSet;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.util.ArrayList;
import java.util.List;

@ExtensibleDataSetCompiler
public class SpellCompiler implements IExtensibleDataSetCompiler {
	private static final String Spell_File_Recognition_Pattern = "Spells.*\\.xml";
	private final List<Document> spellFileList = new ArrayList<Document>();
	private final SpellBuilder builder = new SpellBuilder();
	private final SAXReader reader = new SAXReader();
	private final SpellCache cache = new SpellCache();
	
	@Override
	public String getName() {
		return "Spells";
	}
	
	@Override
	public String getRecognitionPattern() {
		return Spell_File_Recognition_Pattern;
	}

	@Override
	public IExtensibleDataSet build() {
		for (Document document : spellFileList) {
			ISpell[] spells = builder.buildSpells(document);
			for (ISpell spell : spells) {
				cache.addSpell(spell);
			}
		}
		return cache;
	}
	
	@Override
	public void registerFile(IAnathemaResourceFile resource) throws Exception {
	    try {
	      spellFileList.add(reader.read(resource.getURL()));
	    } catch (DocumentException e) {
	      throw new SpellException(resource.getURL().toExternalForm(), e);
	    }
	}	
}
