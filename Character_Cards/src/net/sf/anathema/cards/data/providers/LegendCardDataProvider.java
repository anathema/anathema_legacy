package net.sf.anathema.cards.data.providers;

import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.Image;

import net.sf.anathema.cards.data.ICardData;
import net.sf.anathema.cards.data.LegendCardData;
import net.sf.anathema.cards.data.LegendEntry;
import net.sf.anathema.cards.layout.ICardReportResourceProvider;
import net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.lib.resources.IResources;

import static java.text.MessageFormat.format;

public class LegendCardDataProvider implements ICardDataProvider {

	private final IResources resources;
	
	public List<LegendEntry> traits = new ArrayList<LegendEntry>();
	public List<LegendEntry> characterTypes = new ArrayList<LegendEntry>();
	public List<LegendEntry> spellCircles = new ArrayList<LegendEntry>();
	public List<LegendEntry> martialArtLevels = new ArrayList<LegendEntry>();
	public List<LegendEntry> martialArtStyles = new ArrayList<LegendEntry>();
	public List<LegendEntry> misc = new ArrayList<LegendEntry>();
	
	public LegendCardDataProvider(IResources resources) {
		this.resources = resources;
	}
	
	@Override
	public ICardData[] getCards(ICharacter character,
			ICardReportResourceProvider resourceProvider) {
		traits.clear();
		characterTypes.clear();
		spellCircles.clear();
		martialArtLevels.clear();
		martialArtStyles.clear();
		misc.clear();
		
		buildCharmEntries(resourceProvider, getCurrentCharms(character));
		buildSpellEntries(resourceProvider, getCurrentSpells(character));
		
		cleanEntries();
		
		return createCards(resourceProvider);
	}
	
	private ICardData[] createCards(ICardReportResourceProvider resourceProvider) {
		List<LegendEntry> entries = new ArrayList<LegendEntry>();
		List<ICardData> cards = new ArrayList<ICardData>();
		
		entries.addAll(traits);
		entries.addAll(spellCircles);
		entries.addAll(characterTypes);
		entries.addAll(martialArtLevels);
		entries.addAll(martialArtStyles);
		
		String legend = resources.getString("CardsReport.Legend");
		
		boolean newPage = true;
		
		while (!entries.isEmpty()) {
			List<LegendEntry> cardEntries = new ArrayList<LegendEntry>();
			for (int i = 0; i != LegendCardData.ICONS_PER_CARD &&
					!entries.isEmpty(); i++) {
				if (entries.get(0).getIcon() != resourceProvider.getNullIcon()) {
					cardEntries.add(entries.get(0));
				}
				entries.remove(0);
			}
			
			// add low priority entries, if there is room
			while (entries.isEmpty() && !misc.isEmpty() && cardEntries.size() < LegendCardData.ICONS_PER_CARD) {
				cardEntries.add(misc.get(0));
				misc.remove(0);
			}
			
			if (cardEntries.size() > 0)
				cards.add(new LegendCardData(resourceProvider, legend,
						cardEntries.toArray(new LegendEntry[0]), newPage));
			newPage = false;
		}
		
		return cards.toArray(new ICardData[0]);
	}
	
	private void buildCharmEntries(ICardReportResourceProvider resourceProvider, ICharm[] charms) {
		for (ICharm charm : charms) {
			if (!MartialArtsUtilities.isMartialArtsCharm(charm) || charm.isInstanceOfGenericCharm())
			{
				LegendEntry trait = new LegendEntry(resourceProvider.getTraitIcon(charm.getPrimaryTraitType()),
						resources.getString(charm.getPrimaryTraitType().getId()));
				LegendEntry character = new LegendEntry(resourceProvider.getCharacterIcon(charm.getCharacterType()),
						resources.getString(charm.getCharacterType().getId()));
				if (!traits.contains(trait)) {
					traits.add(trait);
				}
				if (!characterTypes.contains(character)) {
					characterTypes.add(character);
				}	
			} else {
				String levelString = format(resources.getString("CardsReport.Legend.MartialArt"),
						resources.getString(MartialArtsUtilities.getLevel(charm).getId()));
				
				LegendEntry level = new LegendEntry(resourceProvider.getMartialArtLevelIcon(
						MartialArtsUtilities.getLevel(charm)), levelString);
				
				Image styleIcon = resourceProvider.getMartialArtIcon(charm.getGroupId());
				if (styleIcon != null) {
					String styleString = resources.getString(charm.getGroupId());
					int parenIndex = styleString.indexOf(')');
					if (parenIndex > 0) {
						styleString = styleString.substring(parenIndex + 1).trim();
					}
					LegendEntry style = new LegendEntry(styleIcon, styleString);
					
					if (!martialArtStyles.contains(style)) {
						martialArtStyles.add(style);
					}
				}
				
				if (!martialArtLevels.contains(level)) {
					martialArtLevels.add(level);
				}
			}
		}
	}
	
	private void buildSpellEntries(ICardReportResourceProvider resourceProvider, ISpell[] spells) {
		for (ISpell spell : spells) {
			
			String circleString;
			String circleFullString = spell.getCircleType().isSorceryCircle() ?
					resources.getString("CardsReport.Legend.Sorcery") :
					resources.getString("CardsReport.Legend.Necromancy");
			circleString = format(circleFullString, resources.getString(spell.getCircleType().getId()));
			LegendEntry circle = new LegendEntry(resourceProvider.getSpellIcon(spell.getCircleType()),
					circleString);
			if (!spellCircles.contains(circle)) {
				spellCircles.add(circle);
			}
		}
	}
	
	private void cleanEntries() {
		// reshuffle some entries around to minimize card count
		
		// if we only have one character type, deprioritize it
		if (martialArtLevels.size() <= 1) {
			misc.addAll(martialArtLevels);
			martialArtLevels.clear();
		}
				
		// if we only have one character type, don't bother to print it
		if (characterTypes.size() == 1) {
			misc.addAll(characterTypes);
			characterTypes.clear();
		}
	}
	
	private ICharm[] getCurrentCharms(ICharacter character) {
		return character.getStatistics().getCharms().getLearnedCharms(character.getStatistics().isExperienced());
	}
	
	private ISpell[] getCurrentSpells(ICharacter character) {
		return character.getStatistics().getSpells().getLearnedSpells(character.getStatistics().isExperienced());
	}

}
