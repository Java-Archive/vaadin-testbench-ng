/**
 * Copyright © 2017 Sven Ruppert (sven.ruppert@gmail.com)
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
package org.rapidpm.vaadin.addons.testbench.junit5.extensions.container;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import com.google.auto.service.AutoService;

import java.lang.reflect.Method;


@AutoService(ContainerInitializer.class)
public class SpringBootContainerInitializer implements ContainerInitializer {

  private ApplicationContext applicationContext;

  @Override
  public void beforeAll(Class<?> testClass) throws Exception {
    SpringBootConf springBootConf = AnnotationUtils.getAnnotation(testClass, SpringBootConf.class);
    if (springBootConf == null) {
      throw new IllegalStateException("No @SpringBootConf annotation found");
    }
    Class<?> appClass = springBootConf.source();
    if (appClass == null) {
      throw new IllegalStateException("No app class defined");
    }
    applicationContext = SpringApplication.run(appClass, springBootConf.args());
  }

  @Override
  public void beforeEach(Method testMethod) throws Exception { }

  @Override
  public void afterEach(Method testMethod) throws Exception { }

  @Override
  public void afterAll(Class<?> testClass) throws Exception {
    SpringApplication.exit(applicationContext);
  }
}
