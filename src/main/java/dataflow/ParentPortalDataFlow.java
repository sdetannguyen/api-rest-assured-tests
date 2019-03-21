package dataflow;

import enumerations.dataflowenum.ParentPortalDataSet;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Mark the test method using this annotation if the test needs the data related to Parent Portal application
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ParentPortalDataFlow {

    ParentPortalDataSet init();

}
