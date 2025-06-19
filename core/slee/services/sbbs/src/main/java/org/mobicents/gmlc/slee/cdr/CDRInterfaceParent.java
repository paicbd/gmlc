package org.mobicents.gmlc.slee.cdr;

/**
 *
 * @author <a href="mailto:bbaranow@redhat.com"> Bartosz Baranowski </a>
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public interface CDRInterfaceParent {

  void recordGenerationSucceeded();

  void recordGenerationFailed(String message);

  void recordGenerationFailed(String message, Throwable throwable);

  void initFailed(String message, Throwable throwable);

  void initSucceeded();

}
