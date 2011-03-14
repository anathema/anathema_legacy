package net.sf.anathema.character.sidereal.reporting;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.generic.traits.groups.IdentifiedTraitTypeGroup;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.reporting.sheet.common.FavorableTraitEncoder;
import net.sf.anathema.character.sidereal.colleges.SiderealCollegeTemplate;
import net.sf.anathema.character.sidereal.colleges.presenter.IAstrologicalHouse;
import net.sf.anathema.character.sidereal.colleges.presenter.ISiderealCollegeModel;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;

public class SiderealCollegeEncoder extends FavorableTraitEncoder {

  private IExaltedEdition edition;
	
  public SiderealCollegeEncoder(BaseFont baseFont, IResources resources,
		  int essenceMax) {
    this(baseFont, resources, essenceMax, ExaltedEdition.FirstEdition);
  }
  
  public SiderealCollegeEncoder(BaseFont baseFont, IResources resources,
		  int essenceMax, IExaltedEdition edition) {
    super(baseFont, resources, essenceMax);
    this.edition = edition;
  }

  @Override
  protected IIdentifiedTraitTypeGroup[] getIdentifiedTraitTypeGroups(IGenericCharacter character) {
    List<IIdentifiedTraitTypeGroup> groups = new ArrayList<IIdentifiedTraitTypeGroup>();
    ISiderealCollegeModel collegeModel = (ISiderealCollegeModel) character.getAdditionalModel(SiderealCollegeTemplate.ID);
    for (IAstrologicalHouse house : collegeModel.getAllHouses()) {
      List<ITraitType> types = new ArrayList<ITraitType>();
      for (ITrait type : house.getColleges()) {
        types.add(type.getType());
      }
      groups.add(new IdentifiedTraitTypeGroup(types.toArray(new ITraitType[types.size()]), house));
    }
    return groups.toArray(new IIdentifiedTraitTypeGroup[groups.size()]);
  }

  @Override
  protected IGenericTraitCollection getTraitCollection(IGenericCharacter character) {
    ISiderealCollegeModel collegeModel = (ISiderealCollegeModel) character.getAdditionalModel(SiderealCollegeTemplate.ID);
    return new SiderealCollegeTraitCollection(collegeModel);
  }

  public String getHeaderKey() {
    return "Sidereal.Colleges"; //$NON-NLS-1$
  }

  @Override
  protected String getTraitTypePrefix() {
    return "AstrologicalCollege.Label."; //$NON-NLS-1$
  }

  @Override
  protected String getGroupNamePrefix() {
    return "Sheet.Colleges.Houses." +
    (edition == ExaltedEdition.SecondEdition ? "2E." : ""); //$NON-NLS-1$
  }
}