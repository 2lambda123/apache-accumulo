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
package org.apache.accumulo.core.client;

import org.apache.accumulo.core.data.TableId;

public class TableOfflineException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  /**
   * @deprecated since 2.0.0, replaced by {@link #TableOfflineException(String)}
   */
  @Deprecated(since = "2.0.0")
  public TableOfflineException(Instance instance, String tableId) {
    super("Table with ID (" + tableId + ") is offline");
  }

  /**
   * @since 2.0.0
   */
  public TableOfflineException(String msg) {
    super(msg);
  }

  /**
   * @since 2.1.0
   */
  public TableOfflineException(TableId tableId, String tableName) {
    // @formatter:off
    super(String.format("Table %s (%s) is offline",
        tableName == null ? "<unknown table>" : tableName,
        tableId == null ? "<unknown id>" : tableId));
    // @formatter:on
  }

  /**
   * @since 2.0.0
   */
  public TableOfflineException(Exception cause) {
    super(cause);
  }
}
