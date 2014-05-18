package net.sf.anathema.hero.traits.sheet.content;

import net.sf.anathema.character.main.traits.ITraitTemplate;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.traits.lists.IdentifiedTraitTypeList;
import net.sf.anathema.hero.concept.CasteType;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.sheet.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.hero.traits.TraitMap;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.lib.util.Identifier;

import java.util.List;

public abstract class FavorableTraitContent extends AbstractSubBoxContent {

  private Hero hero;

  public FavorableTraitContent(Hero hero, Resources resources) {
    super(resources);
    this.hero = hero;
  }

  public abstract List<? extends TraitType> getMarkedTraitTypes();

  public abstract IdentifiedTraitTypeList[] getIdentifiedTraitTypeGroups();

  public abstract TraitMap getTraitMap();

  public int getTraitMax() {
    IdentifiedTraitTypeList group = getIdentifiedTraitTypeGroups()[0];
    TraitType traitType = group.getAll().get(0);
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
}
