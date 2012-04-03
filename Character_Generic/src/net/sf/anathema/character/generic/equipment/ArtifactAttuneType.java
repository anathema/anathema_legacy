package net.sf.anathema.character.generic.equipment;

public enum ArtifactAttuneType {
  Unattuned, PartiallyAttuned, ExpensivePartiallyAttuned, UnharmoniouslyAttuned, FullyAttuned;
  
  public boolean grantsMaterialBonuses() {
	  switch (this) {
	      case Unattuned:
	      case PartiallyAttuned:
	      case ExpensivePartiallyAttuned:
	        return false;
	      default:
	      case FullyAttuned:
	      case UnharmoniouslyAttuned:
	        return true;
	  }
  }
}
