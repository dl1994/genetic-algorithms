// Copyright (C) Domagoj Latečki
// You may use, distribute and modify this code under the terms of the MIT license.
package hr.dlatecki.algorithms.gen_alg.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that a method of a class must always be overridden, even when extending a non-abstract class which already
 * implements it.
 * 
 * @author Domagoj Latečki
 * @version 1.0
 * @since 1.8
 */
@Target(value = { ElementType.METHOD })
@Retention(value = RetentionPolicy.SOURCE)
public @interface AlwaysOverride {}
