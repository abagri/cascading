/*
 * Copyright (c) 2007-2010 Concurrent, Inc. All Rights Reserved.
 *
 * Project and contact information: http://www.cascading.org/
 *
 * This file is part of the Cascading project.
 *
 * Cascading is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Cascading is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Cascading.  If not, see <http://www.gnu.org/licenses/>.
 */

package cascading.tap;

import java.beans.ConstructorProperties;
import java.io.IOException;

import cascading.flow.Flow;
import cascading.scheme.Scheme;
import cascading.tuple.Fields;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.mapred.JobConf;

/**
 * Class Lfs is a {@link Tap} class that provides access to the Local File System via Hadoop.
 * <p/>
 * Note that using a Lfs {@link Tap} instance in a {@link Flow} will force a portion of not the whole Flow to be executed
 * in "local" mode forcing the Flow to execute in the current JVM. Mixing with {@link Dfs} and other Tap
 * types is possible, providing a means to implement complex file/data management functions.
 * <p/>
 * Use {@link Hfs} if you need a Tap instance that inherits the default {@link FileSystem} used by Hadoop.
 */
public class Lfs extends Hfs
  {
  @ConstructorProperties({"scheme"})
  Lfs( Scheme scheme )
    {
    super( scheme );
    }

  /**
   * Constructor Lfs creates a new Lfs instance.
   *
   * @param fields     of type Fields
   * @param stringPath of type String
   */
  @ConstructorProperties({"fields", "stringPath"})
  public Lfs( Fields fields, String stringPath )
    {
    super( fields, stringPath );
    }

  /**
   * Constructor Lfs creates a new Lfs instance.
   *
   * @param scheme     of type Scheme
   * @param stringPath of type String
   */
  @ConstructorProperties({"scheme", "stringPath"})
  public Lfs( Scheme scheme, String stringPath )
    {
    super( scheme, stringPath );
    }

  /**
   * Constructor Lfs creates a new Lfs instance.
   *
   * @param scheme     of type Scheme
   * @param stringPath of type String
   * @param replace    of type boolean
   */
  @ConstructorProperties({"scheme", "stringPath", "replace"})
  public Lfs( Scheme scheme, String stringPath, boolean replace )
    {
    super( scheme, stringPath, replace );
    }

  /**
   * Constructor Lfs creates a new Lfs instance.
   *
   * @param fields     of type Fields
   * @param stringPath of type String
   * @param sinkMode   of type SinkMode
   */
  @ConstructorProperties({"fields", "stringPath", "sinkMode"})
  public Lfs( Fields fields, String stringPath, SinkMode sinkMode )
    {
    super( fields, stringPath, sinkMode );
    }

  /**
   * Constructor Lfs creates a new Lfs instance.
   *
   * @param scheme     of type Scheme
   * @param stringPath of type String
   * @param sinkMode   of type SinkMode
   */
  @ConstructorProperties({"scheme", "stringPath", "sinkMode"})
  public Lfs( Scheme scheme, String stringPath, SinkMode sinkMode )
    {
    super( scheme, stringPath, sinkMode );
    }

  protected void setStringPath( String stringPath )
    {
    if( stringPath.matches( ".*://.*" ) && !stringPath.startsWith( "file://" ) )
      throw new IllegalArgumentException( "uri must use the file scheme" );

    super.setStringPath( stringPath );
    }

  protected FileSystem getFileSystem( JobConf conf ) throws IOException
    {
    return FileSystem.getLocal( conf );
    }
  }