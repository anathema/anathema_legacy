package net.sf.anathema.character.reporting.pdf.content.abilities;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.template.magic.FavoringTraitType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.content.traits.FavorableTraitContent;
import net.sf.anathema.lib.resources.IResources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AbilitiesContent extends FavorableTraitContent {

  public AbilitiesContent(IGenericCharacter character, IResources resources) {
    super(character, resources);
  }

  @Override
  public List<? extends ITraitType> getMarkedTraitTypes() {
    return Arrays.asList(AbilityType.Athletics, AbilityType.Dodge, AbilityType.Larceny, AbilityType.Ride, AbilityType.Stealth);
  }

  @Override
  public boolean shouldShowExcellencies() {
    return getCharacter().getTemplate().getMagicTemplate().getFavoringTraitType() == FavoringTraitType.AbilityType;
  }

  @Override
  public IIdentifiedTraitTypeGroup[] getIdentifiedTraitTypeGroups() {
    return getCharacter().getAbilityTypeGroups();
  }

  @Override
  public IGenericTraitCollection getTraitCollection() {
    return getCharacter().getTraitCollection();
  }

  @Override
  public String getGroupNamePrefix() {
    return "AbilityGroup."; //$NON-NLS-1$
  }

  @Override
  public String getTraitTypePrefix() {
    return ""; //$NON-NLS-1$
  }

  @Override
  public String getMarkerCommentKey() {
    return "Sheet.Comment.AbilityMobility"; //$NON-NLS-1$
  }

  @Override
  public String getExcellencyCommentKey() {
    return "Sheet.Comment.AbilityExcellency"; //$NON-NLS-1$
  }

  public String getHeaderKey() {
    return "Abilities"; //$NON-NLS-1$
  }
}
