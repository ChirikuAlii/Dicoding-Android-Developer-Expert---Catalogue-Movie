package com.ysn.cataloguemovie.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by yudisetiawan on 10/17/17.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerProvider {
}
