package net.sf.anathema.cards.types;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Joiner;
import com.itextpdf.text.Phrase;

import net.sf.anathema.cards.layout.ICardReportResourceProvider;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.magic.description.MagicDescription;
import net.sf.anathema.character.reporting.pdf.content.stats.magic.SpellStats;
import net.sf.anathema.lib.resources.IResources;

public class SpellCard extends AbstractMagicCard {
	private ISpell spell;
	private SpellStats stats;
	
	public SpellCard(ISpell spell, SpellStats stats, MagicDescription description,
			ICardReportResourceProvider properties, IResources resources) {
		super(spell, description, properties, resources);
		this.spell = spell;
		this.stats = stats;
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
	public Phrase[] getStats() {
		String target = Joiner.on(", ").join(stats.getDetailStrings(getResources()));
		String targetLabel = getResources().getString("MagicReport.Target.Label") + ": ";
		return new Phrase[] {
				getCostPhrase(false),
				new Phrase("\n"),
				new Phrase(targetLabel, getResourceProvider().getBoldFont()),
				new Phrase(target, getResourceProvider().getNormalFont()) };
	}
	
	@Override
	public Phrase[] getBody() {
		List<Phrase> phrases = new ArrayList<Phrase>();
	    if (hasDescription()) {
	    	addDescriptionPhrases(phrases);
	    }
	    return phrases.toArray(new Phrase[0]);
	}

	@Override
	public String getKeywords() {
		return "";
	}
}
