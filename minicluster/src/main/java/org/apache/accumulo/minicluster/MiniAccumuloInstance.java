/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.accumulo.minicluster;

import java.io.File;
import java.io.FileNotFoundException;

import org.apache.accumulo.core.conf.Property;
import org.apache.accumulo.core.conf.SiteConfiguration;

/**
 * @since 1.6.0
 * @deprecated since 2.0.0, Use {@link MiniAccumuloCluster#getClientProperties(File)} instead
 */
@Deprecated(since = "2.0.0")
public class MiniAccumuloInstance extends org.apache.accumulo.core.client.ZooKeeperInstance {

  /**
   * Construct an {@link org.apache.accumulo.core.client.Instance} entry point to Accumulo using a
   * {@link MiniAccumuloCluster} directory
   */
  public MiniAccumuloInstance(String instanceName, File directory) throws FileNotFoundException {
    super(org.apache.accumulo.core.client.ClientConfiguration
        .fromFile(new File(new File(directory, "conf"), "client.conf")).withInstance(instanceName)
        .withZkHosts(getZooKeepersFromDir(directory)));
  }

  // Keep this private to avoid bringing it into the public API
  private static String getZooKeepersFromDir(File directory) {
    if (!directory.isDirectory()) {
      throw new IllegalArgumentException("Not a directory " + directory.getPath());
    }
    File configFile = new File(new File(directory, "conf"), "accumulo.properties");
    var conf = SiteConfiguration.fromFile(configFile).build();
    return conf.get(Property.INSTANCE_ZK_HOST);
  }
}
