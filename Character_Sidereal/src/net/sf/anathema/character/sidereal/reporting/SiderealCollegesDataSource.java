package net.sf.anathema.character.sidereal.reporting;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.framework.reporting.datasource.ITraitDataSource;
import net.sf.anathema.character.library.trait.IFavorableDefaultTrait;
import net.sf.anathema.character.sidereal.colleges.SiderealCollegeTemplate;
import net.sf.anathema.character.sidereal.colleges.presenter.IAstrologicalHouse;
import net.sf.anathema.character.sidereal.colleges.presenter.ISiderealCollegeModel;
import net.sf.anathema.framework.reporting.IReportDataSource;
import net.sf.anathema.lib.resources.IResources;

public class SiderealCollegesDataSource implements IReportDataSource, ITraitDataSource {

  private final IFavorableDefaultTrait[] colleges;
  private final IResources resources;

  public SiderealCollegesDataSource(IGenericCharacter character, IResources resources) {
    this.resources = resources;
    ISiderealCollegeModel collegeModels = (ISiderealCollegeModel) character.getAdditionalModel(SiderealCollegeTemplate.ID);
    List<IFavorableDefaultTrait> learnedColleges = new ArrayList<IFavorableDefaultTrait>();
    for (IAstrologicalHouse house : collegeModels.getAllHouses()) {
      for (IFavorableDefaultTrait college : house.getColleges()) {
        if (college.getCurrentValue() > 0) {
          learnedColleges.add(college);
        }
      }
    }
    this.colleges = learnedColleges.toArray(new IFavorableDefaultTrait[learnedColleges.size()]);
  }

  public int getRowCount() {
    return colleges.length;
  }

  public Object getValue(int currentRow, String columnName) {
    if (columnName.equals(COLUMN_PRINT_NAME)) {
      if (currentRow >= getRowCount()) {
        return ""; //$NON-NLS-1$
      }
      return resources.getString("AstrologicalCollege.Label." + colleges[currentRow].getType().getId()); //$NON-NLS-1$
    }
    if (columnName.equals(COLUMN_VALUE)) {
      if (currentRow >= getRowCount()) {
        return 0;
      }
      return colleges[currentRow].getCurrentValue();
    }
    return ""; //$NON-NLS-1$
  }
}