package net.sf.anathema.character.sidereal.caste;

import net.sf.anathema.character.generic.caste.ICasteType;

public enum SiderealCaste implements ICasteType<ISiderealCasteVisitor> {

  Journeys {
    public void accept(ISiderealCasteVisitor visitor) {
      visitor.visitJourneys(this);
    }
  },
  Serenity {
    public void accept(ISiderealCasteVisitor visitor) {
      visitor.visitSerenity(this);
    }
  },
  Battles {
    public void accept(ISiderealCasteVisitor visitor) {
      visitor.visitBattles(this);
    }
  },
  Secrets {
    public void accept(ISiderealCasteVisitor visitor) {
      visitor.visitSecrets(this);
    }
  },
  Endings {
    public void accept(ISiderealCasteVisitor visitor) {
      visitor.visitEndings(this);
    }
  };

  public String getId() {
    return name();
  }

  @Override
  public String toString() {
    return name();
  }
}