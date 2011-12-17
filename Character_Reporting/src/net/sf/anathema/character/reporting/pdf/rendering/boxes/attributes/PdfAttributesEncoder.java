package net.sf.anathema.character.reporting.pdf.rendering.boxes.attributes;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import net.disy.commons.core.predicate.IPredicate;
import net.disy.commons.core.util.CollectionUtilities;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.generic.template.abilities.IGroupedTraitType;
import net.sf.anathema.character.generic.template.magic.FavoringTraitType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.traits.PdfTraitEncoder;
import net.sf.anathema.lib.resources.IResources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PdfAttributesEncoder implements IPdfContentBoxEncoder {

  private final IResources resources;
  private PdfTraitEncoder smallTraitEncoder;
  private final int essenceMax;
  private final boolean encodeFavored;

  public PdfAttributesEncoder(BaseFont baseFont, IResources resources, int essenceMax, boolean encodeFavored) {
    this.resources = resources;
    this.essenceMax = essenceMax;
    this.encodeFavored = encodeFavored;
    this.smallTraitEncoder = PdfTraitEncoder.createSmallTraitEncoder(baseFont);
  }

  public String getHeaderKey(IGenericCharacter character, IGenericDescription description) {
    return "Attributes"; //$NON-NLS-1$
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description,
                     Bounds bounds) throws DocumentException {
    IGroupedTraitType[] attributeGroups = character.getTemplate().getAttributeGroups();
    IGenericTraitCollection traitCollection = character.getTraitCollection();
    IMagicStats[] excellencies = getExcellencies(character);
    encodeAttributes(directContent, character, bounds, attributeGroups, traitCollection, excellencies);
  }

  protected IMagicStats[] getExcellencies(IGenericCharacter character) {
    List<IMagicStats> excellencies = new ArrayList<IMagicStats>();
    FavoringTraitType type = character.getTemplate().getMagicTemplate().getFavoringTraitType();
    if (type == FavoringTraitType.AttributeType) {
      for (IMagicStats stats : character.getGenericCharmStats()) {
        String genericId = stats.getName().getId();
        if (genericId.endsWith("Excellency")) {
          excellencies.add(stats);
        }
      }
      Collections.sort(excellencies, new Comparator<IMagicStats>() {
        public int compare(IMagicStats a, IMagicStats b) {
          String aId = a.getName().getId();
          String bId = b.getName().getId();
          return aId.compareTo(bId);
        }
      });
    }
    return excellencies.toArray(new IMagicStats[0]);
  }

  public final void encodeAttributes(PdfContentByte directContent, IGenericCharacter character, Bounds contentBounds,
                                     IGroupedTraitType[] attributeGroups, IGenericTraitCollection traitCollection, IMagicStats[] excellencies) {
    float groupSpacing = smallTraitEncoder.getTraitHeight() / 2;
    float y = contentBounds.getMaxY() - groupSpacing;
    String groupId = null;
    List<IMagic> allLearnedMagic = character.getAllLearnedMagic();
    // TODO: (2011-06-19) Add column headers [particularly for Excellencies]
    for (IGroupedTraitType groupedTraitType : attributeGroups) {
      if (!groupedTraitType.getGroupId().equals(groupId)) {
        groupId = groupedTraitType.getGroupId();
        y -= groupSpacing;
      }
      ITraitType traitType = groupedTraitType.getTraitType();
      String traitLabel = resources.getString("AttributeType.Name." + traitType.getId()); //$NON-NLS-1$
      int value = traitCollection.getTrait(traitType).getCurrentValue();
      Position position = new Position(contentBounds.x, y);
      if (!encodeFavored) {
        y -= smallTraitEncoder.encodeWithText(directContent, traitLabel, position, contentBounds.width, value, essenceMax);
      }
      else {
        boolean favored = traitCollection.getFavorableTrait(traitType).isCasteOrFavored();
        boolean[] excellencyLearned = new boolean[excellencies.length];
        for (int i = 0; i < excellencies.length; i++) {
          final String charmId = excellencies[i].getName().getId() + "." + traitType.getId(); //$NON-NLS-1$
          excellencyLearned[i] = CollectionUtilities.find(allLearnedMagic, new IPredicate<IMagic>() {
            public boolean evaluate(IMagic value) {
              return charmId.equals(value.getId());
            }
          }) != null;
        }
        y -= smallTraitEncoder.encodeWithExcellencies(directContent, traitLabel, position, contentBounds.width, value, favored, excellencyLearned,
                                                      essenceMax);
      }
    }
  }

  public boolean hasContent(IGenericCharacter character) {
    return true;
  }
}
