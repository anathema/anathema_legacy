package net.sf.anathema.cards.types;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Joiner;
import com.itextpdf.text.Phrase;

import net.sf.anathema.cards.layout.ICardReportResourceProvider;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.type.VerboseCharmTypeStringBuilder;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.description.MagicDescription;
import net.sf.anathema.character.reporting.pdf.content.stats.magic.CharmStats;
import net.sf.anathema.lib.resources.IResources;

public class CharmCard extends AbstractMagicCard {
	private ICharm charm;
	private CharmStats stats;
	
	public CharmCard(ICharm charm, CharmStats stats, MagicDescription description,
			ICardReportResourceProvider fontProvider, IResources resources) {
		super(charm, description, fontProvider, resources);
		this.charm = charm;
		this.stats = stats;
	}
	
	@Override
	public Image getPrimaryIcon() {
		return getResourceProvider().getTraitIcon(charm.getPrimaryTraitType());
	}

	@Override
	public Image getSecondaryIcon() {
		return getResourceProvider().getCharacterIcon(charm.getCharacterType());
	}
	
	@Override
	public Phrase[] getStats() {
		return new Phrase[] {
				getCharmType(charm),
				new Phrase("\n"),
				getCharmDuration(charm)
		};
	}
	
	@Override
	public Phrase[] getBody() {
		List<Phrase> phrases = new ArrayList<Phrase>();
		if (hasCost(charm)) {
			phrases.add(getCostPhrase(hasDescription()));
		}
	    if (hasDescription()) {
	    	addDescriptionPhrases(phrases);
	    }
	    return phrases.toArray(new Phrase[0]);
	}
	
	private Phrase getCharmType(ICharm charm) {
		String type = new VerboseCharmTypeStringBuilder(getResources()).createTypeString(charm.getCharmTypeModel());
		return new Phrase(8, type, getResourceProvider().getBoldFont());
	}
	
	private Phrase getCharmDuration(ICharm charm) {
		String duration = charm.getDuration().getText(getResources());
		return new Phrase(duration, getResourceProvider().getNormalFont());
	}

	@Override
	public String getKeywords() {
		return Joiner.on(", ").join(stats.getDetailStrings(getResources()));
	}
}
