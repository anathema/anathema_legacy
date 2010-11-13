package net.sf.anathema.campaign.music.impl.persistence.search;

import com.db4o.query.Candidate;
import com.db4o.query.Constraint;
import com.db4o.query.Evaluation;
import com.db4o.query.Query;

public class StringFieldSearchParameter implements IExtendedSearchParameter {

  private final String fieldName;
  private final String parameterization;

  public StringFieldSearchParameter(String fieldName, String parameterization) {
    this.fieldName = fieldName;
    this.parameterization = parameterization;
  }

  public Constraint configure(Query query) {
    return query.descend(fieldName).constrain(new Evaluation() {
      public void evaluate(Candidate candidate) {
        String value = (String) candidate.getObject();
        if (value == null) {
          value = ""; //$NON-NLS-1$
        }
        value = value.toLowerCase();
        candidate.include(value != null && value.indexOf(parameterization.toLowerCase().trim()) > -1);
      }
    });
  }
}