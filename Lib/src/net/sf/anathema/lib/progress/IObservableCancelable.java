package net.sf.anathema.lib.progress;

public interface IObservableCancelable extends ICancelable {

  void addCanceledListener(ICanceledListener listener);

  void removeCanceledListener(ICanceledListener listener);
}