/**
 * Copyright 2014 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.netflix.spectator.api;

/** Helper methods for accessing configuration settings. */
final class Config {

  private static final String PREFIX = "spectator.api.";

  /** Value for registry class to explicitly indicate the service loader should be used. */
  static final String SERVICE_LOADER = "service-loader";

  private Config() {
  }

  /** Should an exception be thrown for warnings? */
  static boolean propagateWarnings() {
    return Boolean.valueOf(System.getProperty(PREFIX + "propagateWarnings", "false"));
  }

  /** Class implementing the {@link Registry} interface that should be loaded. */
  static String registryClass() {
    return System.getProperty(PREFIX + "registryClass", SERVICE_LOADER);
  }

  /**
   * For classes based on {@link AbstractRegistry} this setting is used to determine the maximum
   * number of registered meters permitted. This limit is used to help protect the system from a
   * memory leak if there is a bug or irresponsible usage of registering meters.
   *
   * @return
   *     Maximum number of distinct meters that can be registered at a given time. The default is
   *     {@link java.lang.Integer#MAX_VALUE}.
   */
  static int maxNumberOfMeters() {
    final String v = System.getProperty(PREFIX + "maxNumberOfMeters");
    return (v == null) ? Integer.MAX_VALUE : Integer.parseInt(v);
  }
}