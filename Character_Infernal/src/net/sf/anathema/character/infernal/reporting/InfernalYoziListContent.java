package net.sf.anathema.character.infernal.reporting;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.content.traits.FavorableTraitContent;
import net.sf.anathema.lib.resources.IResources;

import java.util.ArrayList;
import java.util.List;

public class InfernalYoziListContent extends FavorableTraitContent {

  public InfernalYoziListContent(IGenericCharacter character, IResources resources) {
    super(character, resources);
  }

  @Override
  public List<? extends ITraitType> getMarkedTraitTypes() {
    return new ArrayList<ITraitType>();
  }

  @Override
  public boolean shouldShowExcellencies() {
    return false;
  }

  @Override
  public IIdentifiedTraitTypeGroup[] getIdentifiedTraitTypeGroups() {
    return getCharacter().getYoziTypeGroups();
  }

  @Override
  public IGenericTraitCollection getTraitCollection() {
    return getCharacter().getTraitCollection();
  }

  @Override
  public String getGroupNamePrefix() {
    // TODO Auto-generated method stub
    return null;
  }

  public String getMarkerCommentKey() {
    return null;
  }

  public String getExcellencyCommentKey() {
    return null;
  }

  public String getTraitTypePrefix() {
    return ""; //$NON-NLS-1$
  }

  @Override
  public String getHeaderKey() {
    return "Infernal.Yozis";
  }
}
