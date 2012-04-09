package net.sf.anathema.character.generic.impl.magic.persistence;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.impl.magic.SpellException;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.magic.charms.CharmException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;


public class SpellCompiler {
	private final List<Document> spellFileList = new ArrayList<Document>();
	private final SpellBuilder builder = new SpellBuilder();
	private final SAXReader reader = new SAXReader();
	private final SpellCache cache;
	
	public SpellCompiler(SpellCache cache) {
		this.cache = cache;
	}
	
	public void buildSpells() {
		for (Document document : spellFileList) {
			ISpell[] spells = builder.buildSpells(document);
			for (ISpell spell : spells) {
				cache.addSpell(spell);
			}
		}
	}
	
	public void registerSpellFile(URL resource) throws CharmException {
	    try {
	      spellFileList.add(reader.read(resource));
	    } catch (DocumentException e) {
	      throw new SpellException(resource.toExternalForm(), e);
	    }
	}
	
	
}
