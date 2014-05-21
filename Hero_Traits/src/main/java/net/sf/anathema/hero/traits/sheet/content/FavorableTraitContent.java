package net.sf.anathema.hero.traits.sheet.content;

import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.traits.lists.IdentifiedTraitTypeList;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.concept.CasteType;
import net.sf.anathema.hero.sheet.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.hero.traits.TraitMap;
import net.sf.anathema.hero.traits.model.TraitListModel;
import net.sf.anathema.lib.util.Identifier;

import java.util.List;

public abstract class FavorableTraitContent extends AbstractSubBoxContent {

  private TraitListModel model;

  public FavorableTraitContent(TraitListModel model, Resources resources) {
    super(resources);
    this.model = model;
  }

  public abstract List<? extends TraitType> getMarkedTraitTypes();

  public abstract IdentifiedTraitTypeList[] getIdentifiedTraitTypeGroups();

  public abstract TraitMap getTraitMap();

  public int getTraitMax() {
    return model.getTraitMaximum();
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
}
