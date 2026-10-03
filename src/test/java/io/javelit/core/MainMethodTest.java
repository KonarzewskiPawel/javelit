/*
 * Copyright © 2025 Cyril de Catheu (cdecatheu@hey.com)
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
package io.javelit.core;

import java.nio.file.Path;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledForJreRange;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;


public class MainMethodTest {

  @Test
  @EnabledForJreRange(min = JRE.JAVA_25)
  public void testMainNotPublic() {
    final Path appPath = Path.of("src/test/resources/java25/MainNotPublic.java");
    final Server.Builder builder = Server.builder(appPath, 0)
                                         .buildSystem(BuildSystem.RUNTIME);

    final FileReloader fileReloader = new FileReloader(builder);
    final Reloader.AppEntrypoint entrypoint = fileReloader.reload();

    assertThat(entrypoint).isNotNull();
    assertThat(entrypoint.runnable()).isNotNull();
  }

  @Test
  @EnabledForJreRange(min = JRE.JAVA_25)
  public void testMainNotStatic() {
    final Path appPath = Path.of("src/test/resources/java25/MainNotStatic.java");
    final Server.Builder builder = Server.builder(appPath, 0)
                                         .buildSystem(BuildSystem.RUNTIME);
    final FileReloader fileReloader = new FileReloader(builder);
    final Reloader.AppEntrypoint entrypoint = fileReloader.reload();

    assertThat(entrypoint).isNotNull();
    assertThat(entrypoint.runnable()).isNotNull();
  }

  @Test
  @EnabledForJreRange(min = JRE.JAVA_25)
  public void testMainNoArgs() {
    final Path appPath = Path.of("src/test/resources/java25/MainNoArgs.java");
    final Server.Builder builder = Server.builder(appPath, 0)
                                         .buildSystem(BuildSystem.RUNTIME);
    final FileReloader fileReloader = new FileReloader(builder);
    final Reloader.AppEntrypoint entrypoint = fileReloader.reload();

    assertThat(entrypoint).isNotNull();
    assertThat(entrypoint.runnable()).isNotNull();
  }

  @Test
  @EnabledForJreRange(min = JRE.JAVA_25)
  public void testMainMinimal() {
    final Path appPath = Path.of("src/test/resources/java25/MainMinimal.java");

    final Server.Builder builder = Server.builder(appPath, 0)
                                         .buildSystem(BuildSystem.RUNTIME);
    final FileReloader fileReloader = new FileReloader(builder);
    final Reloader.AppEntrypoint entrypoint = fileReloader.reload();

    assertThat(entrypoint).isNotNull();
    assertThat(entrypoint.runnable()).isNotNull();
  }

  @Test
  @EnabledForJreRange(min = JRE.JAVA_25)
  public void testMainNoClass() {
    final Path appPath = Path.of("src/test/resources/java25/MainNoClass.java");
    final Server.Builder builder = Server.builder(appPath, 0)
                                         .buildSystem(BuildSystem.RUNTIME);
    final FileReloader fileReloader = new FileReloader(builder);
    final Reloader.AppEntrypoint entrypoint = fileReloader.reload();

    assertThat(entrypoint).isNotNull();
    assertThat(entrypoint.runnable()).isNotNull();
  }

  @ParameterizedTest
  @ValueSource(strings = {"MainNoStaticNoPublicClass", "Main", "MainNotPublic", "MainNotStatic", "MainMinimal", "MainNoArgs", "MainNoClass"})
  @EnabledForJreRange(min = JRE.JAVA_25)
  public void testMainMethod(String testName) {
    final Path appPath = Path.of("src/test/resources/java25/%s.java".formatted(testName));
    final Server.Builder builder = Server.builder(appPath, 0)
                                         .buildSystem(BuildSystem.RUNTIME);

    final FileReloader fileReloader = new FileReloader(builder);
    final Reloader.AppEntrypoint entrypoint = fileReloader.reload();

    System.out.println(entrypoint.classLoader());
    assertThat(entrypoint).isNotNull();
    assertThat(entrypoint.runnable()).isNotNull();
  }
}
