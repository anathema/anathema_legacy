package net.sf.anathema.character.reporting.pdf.content.traits;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.reporting.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.character.reporting.pdf.content.magic.GenericCharmUtilities;
import net.sf.anathema.character.reporting.pdf.util.MagicLearnUtilities;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identified;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class FavorableTraitContent extends AbstractSubBoxContent {

  private IGenericCharacter character;

  public FavorableTraitContent(IGenericCharacter character, Resources resources) {
    super(resources);
    this.character = character;
  }

  public abstract List<? extends ITraitType> getMarkedTraitTypes();

  protected IGenericCharacter getCharacter() {
    return character;
  }

  public IMagicStats[] getExcellencies() {
    List<IMagicStats> excellencies = new ArrayList<>();
    if (shouldShowExcellencies()) {
      for (IMagicStats stats : GenericCharmUtilities.getGenericCharmStats(character)) {
        String genericId = stats.getName().getId();
        if (genericId.endsWith("Excellency")) {
          excellencies.add(stats);
        }
      }
      Collections.sort(excellencies, new Comparator<IMagicStats>() {
        @Override
        public int compare(IMagicStats a, IMagicStats b) {
          String aId = a.getName().getId();
          String bId = b.getName().getId();

          Integer aIndex = new Integer(aId.substring(aId.lastIndexOf('.') + 1, aId.indexOf("Excellency") - 2));
          Integer bIndex = new Integer(bId.substring(bId.lastIndexOf('.') + 1, bId.indexOf("Excellency") - 2));
          return aIndex.compareTo(bIndex);
        }
      });
    }
    return excellencies.toArray(new IMagicStats[excellencies.size()]);
  }

  public abstract boolean shouldShowExcellencies();

  public abstract IIdentifiedTraitTypeGroup[] getIdentifiedTraitTypeGroups();

  public abstract IGenericTraitCollection getTraitCollection();

  public boolean[] hasExcellenciesLearned(ITraitType traitType) {
    IMagicStats[] excellencies = getExcellencies();
    List<IMagic> allLearnedMagic = character.getAllLearnedMagic();
    boolean[] excellencyLearned = new boolean[excellencies.length];
    for (int i = 0; i < excellencies.length; i++) {
      String charmId = excellencies[i].getName().getId() + "." + traitType.getId();
      excellencyLearned[i] = MagicLearnUtilities.isCharmLearned(allLearnedMagic, charmId);
    }
    return excellencyLearned;
  }

  public abstract String getExcellencyCommentKey();

  public int getTraitMax() {
    IIdentifiedTraitTypeGroup group = getIdentifiedTraitTypeGroups()[0];
    ITraitType traitType = group.getAllGroupTypes()[0];
    ITraitTemplate template = character.getTemplate().getTraitTemplateCollection().getTraitTemplate(traitType);
    return template.getLimitation().getAbsoluteLimit(character);
  }

  @Override
  public boolean hasContent() {
    return true;
  }

  @Override
  public abstract String getHeaderKey();

  public String getGroupLabel(Identified groupId) {
    String groupIdAsString = groupId.getId();
    String resourceKey = groupId instanceof ICasteType ? "Caste." + groupIdAsString : getGroupNamePrefix() + groupIdAsString;
    return getString(resourceKey);
  }

  public boolean hasGroupLabel() {
    return getGroupNamePrefix() == null;
  }

  protected abstract String getGroupNamePrefix();

  public String getTraitLabel(ITraitType traitType) {
    return getString(getTraitTypePrefix() + traitType.getId());
  }

  protected abstract String getTraitTypePrefix();

  public String getMobilityPenaltyText() {
    return " : " + getString(getMarkerCommentKey());
  }

  public abstract String getMarkerCommentKey();

  public String getExcellenciesComment() {
    int nExcellencies = getExcellencies().length;
    StringBuilder numbers = new StringBuilder();
    for (int i = 1; i <= nExcellencies; i++) {
      numbers.append(Integer.toString(i));
    }
    return numbers.toString() + ": " + getString(getExcellencyCommentKey());
  }
}
