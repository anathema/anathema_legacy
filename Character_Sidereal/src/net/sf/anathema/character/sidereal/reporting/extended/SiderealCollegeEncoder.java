package net.sf.anathema.character.sidereal.reporting.extended;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.generic.traits.groups.IdentifiedTraitTypeGroup;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.reporting.common.encoder.FavorableTraitEncoder;
import net.sf.anathema.character.sidereal.colleges.SiderealCollegeTemplate;
import net.sf.anathema.character.sidereal.colleges.presenter.IAstrologicalHouse;
import net.sf.anathema.character.sidereal.colleges.presenter.ISiderealCollegeModel;
import net.sf.anathema.lib.resources.IResources;

import java.util.ArrayList;
import java.util.List;

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

  public String getHeaderKey(IGenericCharacter character, IGenericDescription description) {
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
