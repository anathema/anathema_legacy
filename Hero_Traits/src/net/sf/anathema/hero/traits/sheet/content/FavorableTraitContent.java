package net.sf.anathema.hero.traits.sheet.content;

import net.sf.anathema.character.generic.caste.CasteType;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.reporting.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.character.reporting.pdf.content.magic.MagicContentHelper;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class FavorableTraitContent extends AbstractSubBoxContent {

  private Hero hero;

  public FavorableTraitContent(Hero hero, Resources resources) {
    super(resources);
    this.hero = hero;
  }

  public abstract List<? extends TraitType> getMarkedTraitTypes();

  public IMagicStats[] getExcellencies() {
    List<IMagicStats> excellencies = new ArrayList<>();
    if (shouldShowExcellencies()) {
      for (IMagicStats stats : new MagicContentHelper(hero).getGenericCharmStats()) {
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

  public boolean[] hasExcellenciesLearned(TraitType traitType) {
    IMagicStats[] excellencies = getExcellencies();
    List<IMagic> allLearnedMagic = new MagicContentHelper(hero).getAllLearnedMagic();
    boolean[] excellencyLearned = new boolean[excellencies.length];
    for (int i = 0; i < excellencies.length; i++) {
      String charmId = excellencies[i].getName().getId() + "." + traitType.getId();
      excellencyLearned[i] = new MagicContentHelper(hero).isCharmLearned(allLearnedMagic, charmId);
    }
    return excellencyLearned;
  }

  public abstract String getExcellencyCommentKey();

  public int getTraitMax() {
    IIdentifiedTraitTypeGroup group = getIdentifiedTraitTypeGroups()[0];
    TraitType traitType = group.getAllGroupTypes()[0];
    ITraitTemplate template = hero.getTemplate().getTraitTemplateCollection().getTraitTemplate(traitType);
    return template.getLimitation().getAbsoluteLimit(hero);
  }

  @Override
  public boolean hasContent() {
    return true;
  }

  @Override
  public abstract String getHeaderKey();

  public String getGroupLabel(Identifier groupId) {
    String groupIdAsString = groupId.getId();
    String resourceKey = groupId instanceof CasteType ? "Caste." + groupIdAsString : getGroupNamePrefix() + groupIdAsString;
    return getString(resourceKey);
  }

  public boolean hasGroupLabel() {
    return getGroupNamePrefix() == null;
  }

  protected abstract String getGroupNamePrefix();

  public String getTraitLabel(TraitType traitType) {
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
