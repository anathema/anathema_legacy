package net.sf.anathema.character.sidereal.reporting.content;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.generic.traits.groups.IdentifiedTraitTypeGroup;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.reporting.pdf.content.traits.FavorableTraitContent;
import net.sf.anathema.character.sidereal.colleges.SiderealCollegeTemplate;
import net.sf.anathema.character.sidereal.colleges.presenter.IAstrologicalHouse;
import net.sf.anathema.character.sidereal.colleges.presenter.ISiderealCollegeModel;
import net.sf.anathema.character.sidereal.reporting.rendering.colleges.SiderealCollegeTraitCollection;
import net.sf.anathema.lib.resources.IResources;

import java.util.ArrayList;
import java.util.List;

public class SiderealCollegeContent extends FavorableTraitContent {

  public SiderealCollegeContent(IGenericCharacter character, IResources resources) {
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
    List<IIdentifiedTraitTypeGroup> groups = new ArrayList<IIdentifiedTraitTypeGroup>();
    for (IAstrologicalHouse house : getCollegeModel().getAllHouses()) {
      List<ITraitType> types = new ArrayList<ITraitType>();
      for (ITrait type : house.getColleges()) {
        types.add(type.getType());
      }
      groups.add(new IdentifiedTraitTypeGroup(types.toArray(new ITraitType[types.size()]), house));
    }
    return groups.toArray(new IIdentifiedTraitTypeGroup[groups.size()]);
  }

  @Override
  public IGenericTraitCollection getTraitCollection() {
    ISiderealCollegeModel collegeModel = getCollegeModel();
    return new SiderealCollegeTraitCollection(collegeModel);
  }

  @Override
  public int getTraitMax() {
    return 5;
  }

  private ISiderealCollegeModel getCollegeModel() {
    return (ISiderealCollegeModel) getCharacter().getAdditionalModel(SiderealCollegeTemplate.ID);
  }

  @Override
  public String getTraitTypePrefix() {
    return "AstrologicalCollege.Label."; //$NON-NLS-1$
  }

  @Override
  public String getGroupNamePrefix() {
    String editionString = getCharacter().getTemplate().getEdition() == ExaltedEdition.SecondEdition ? "2E." : "";
    return "Sheet.Colleges.Houses." + editionString;
  }

  @Override
  public String getMarkerCommentKey() {
    return null;
  }

  @Override
  public String getExcellencyCommentKey() {
    return null;
  }

  public String getHeaderKey() {
    return "Sidereal.Colleges"; //$NON-NLS-1$
  }
}
