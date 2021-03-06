/*
 * #%L
 * wcm.io
 * %%
 * Copyright (C) 2016 wcm.io
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package io.wcm.testing.mock.aem.junit;

import java.util.Map;

import org.apache.sling.testing.mock.sling.ResourceResolverType;

/**
 * Builder class for creating {@link AemContext} instances with different sets of parameters.
 */
public final class AemContextBuilder {

  private ResourceResolverType[] resourceResolverTypes;
  private AemContextCallback beforeSetUpCallback;
  private AemContextCallback afterSetUpCallback;
  private AemContextCallback beforeTearDownCallback;
  private AemContextCallback afterTearDownCallback;
  private Map<String, Object> resourceResolverFactoryActivatorProps;

  /**
   * Create builder with default resource resolver type.
   */
  public AemContextBuilder() {
    // use default resource resolver type
  }

  /**
   * Create builder with given resource resolver type.
   * @param resourceResolverTypes Resource resolver type(s).
   */
  public AemContextBuilder(ResourceResolverType... resourceResolverTypes) {
    this.resourceResolverType(resourceResolverTypes);
  }

  /**
   * @param types Resource resolver type(s).
   * @return this
   */
  public AemContextBuilder resourceResolverType(ResourceResolverType... types) {
    this.resourceResolverTypes = types;
    return this;
  }

  /**
   * @param callback Allows the application to register an own callback function that is called before the built-in setup rules are executed.
   * @return this
   */
  public AemContextBuilder beforeSetUp(AemContextCallback callback) {
    this.beforeSetUpCallback = callback;
    return this;
  }

  /**
   * @param callback Allows the application to register an own callback function that is called after the built-in setup rules are executed.
   * @return this
   */
  public AemContextBuilder afterSetUp(AemContextCallback callback) {
    this.afterSetUpCallback = callback;
    return this;
  }

  /**
   * @param callback Allows the application to register an own callback function that is called before the built-in teardown rules are executed.
   * @return this
   */
  public AemContextBuilder beforeTearDown(AemContextCallback callback) {
    this.beforeTearDownCallback = callback;
    return this;
  }

  /**
   * @param callback Allows the application to register an own callback function that is after before the built-in teardown rules are executed.
   * @return this
   */
  public AemContextBuilder afterTearDown(AemContextCallback callback) {
    this.afterTearDownCallback = callback;
    return this;
  }

  /**
   * Allows to override OSGi configuration parameters for the Resource Resolver Factory Activator service.
   * @param props Configuration properties
   * @return this
   */
  public AemContextBuilder resourceResolverFactoryActivatorProps(Map<String, Object> props) {
    this.resourceResolverFactoryActivatorProps = props;
    return this;
  }

  /**
   * @return Build {@link AemContext} instance.
   */
  public AemContext build() {
    return new AemContext(this.beforeSetUpCallback, this.afterSetUpCallback,
        this.beforeTearDownCallback, this.afterTearDownCallback,
        this.resourceResolverFactoryActivatorProps,
        this.resourceResolverTypes);
  }

}
