package net.sf.anathema.campaign.music.impl.persistence.search;

import net.disy.commons.core.util.ArrayUtilities;

import com.db4o.query.Candidate;
import com.db4o.query.Constraint;
import com.db4o.query.Evaluation;
import com.db4o.query.Query;

public abstract class AbstractArrayFieldSearchParameter<P> implements IExtendedSearchParameter {

  private final P[] parameterArray;

  public AbstractArrayFieldSearchParameter(P[] parameterArray) {
    this.parameterArray = parameterArray;
  }

  public Constraint configure(Query query) {
    return query.constrain(new Evaluation() {
      public void evaluate(Candidate candidate) {
        candidate.include(containsAllParameters(candidate));
      }
    });
  }

  protected boolean containsAllParameters(Candidate candidate) {
    P[] candidateValues = getCandidateValues(candidate);
    for (P parameter : parameterArray) {
      if (!ArrayUtilities.contains(candidateValues, parameter)) {
        return false;
      }
    }
    return true;
  }

  protected abstract P[] getCandidateValues(Candidate candidate);
}