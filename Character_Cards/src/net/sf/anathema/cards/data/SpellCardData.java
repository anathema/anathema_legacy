package net.sf.anathema.cards.data;

import com.google.common.base.Joiner;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;

import net.sf.anathema.cards.layout.ICardReportResourceProvider;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.magic.description.MagicDescription;
import net.sf.anathema.character.reporting.pdf.content.stats.magic.SpellStats;
import net.sf.anathema.lib.resources.IResources;

public class SpellCardData extends AbstractMagicCardData {
	private ISpell spell;
	private SpellStats spellStats;
	
	public SpellCardData(ISpell spell, SpellStats stats, MagicDescription description,
			ICardReportResourceProvider properties, IResources resources) {
		super(spell, description, properties, resources);
		this.spell = spell;
		this.spellStats = stats;
	}

	@Override
	public Image getPrimaryIcon() {
		return getResourceProvider().getSpellIcon(spell.getCircleType());
	}

	@Override
	public Image getSecondaryIcon() {
		return null;
	}

	@Override
	public Paragraph getStats() {
		Paragraph stats = new Paragraph();
		stats.add(getCostPhrase(false));
		stats.add(new Phrase("\n" + getResources().getString("MagicReport.Target.Label") + ": ",
				getResourceProvider().getBoldFont()));
		stats.add(new Phrase(Joiner.on(", ").join(spellStats.getDetailStrings(getResources())),
				getResourceProvider().getNormalFont()));
		return stats;
	}
	
	@Override
	public Element[] getBody(int getHeight) {
		Paragraph paragraph = new Paragraph();
	    if (hasDescription()) {
	    	addDescriptionPhrases(paragraph);
	    }
	    return new Element[] { paragraph };
	}

	@Override
	public String getKeywords() {
		return "";
	}
}
