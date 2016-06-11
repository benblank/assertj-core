/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2016 the original author or authors.
 */
package org.assertj.core.internal.urls;

import static org.assertj.core.error.uri.ShouldHaveParameter.shouldHaveNoParameter;
import static org.assertj.core.test.TestFailures.failBecauseExpectedAssertionErrorWasNotThrown;
import static org.mockito.Mockito.verify;

import java.net.URL;
import java.net.MalformedURLException;

import org.assertj.core.internal.UrlsBaseTest;
import org.junit.Test;

public class Urls_assertHasNoParameter_Test extends UrlsBaseTest {

  @Test
  public void should_pass_if_parameter_is_missing() throws MalformedURLException {
    urls.assertHasNoParameter(info, new URL("http://assertj.org/news"), "article");
  }

  @Test
  public void should_fail_if_parameter_is_present_without_value() throws MalformedURLException {
    URL url = new URL("http://assertj.org/news?article");
    String name = "article";
    String actualValue = null;

    try {
      urls.assertHasNoParameter(info, url, name);
    } catch (AssertionError e) {
      verify(failures).failure(info, shouldHaveNoParameter(url, name, actualValue));
      return;
    }

    failBecauseExpectedAssertionErrorWasNotThrown();
  }

  @Test
  public void should_fail_if_parameter_is_present_with_value() throws MalformedURLException {
    URL url = new URL("http://assertj.org/news?article=10");
    String name = "article";
    String actualValue = "10";

    try {
      urls.assertHasNoParameter(info, url, name);
    } catch (AssertionError e) {
      verify(failures).failure(info, shouldHaveNoParameter(url, name, actualValue));
      return;
    }

    failBecauseExpectedAssertionErrorWasNotThrown();
  }

  @Test
  public void should_pass_if_parameter_without_value_is_missing() throws MalformedURLException {
    urls.assertHasNoParameter(info, new URL("http://assertj.org/news"), "article", null);
  }

  @Test
  public void should_fail_if_parameter_without_value_is_present() throws MalformedURLException {
    URL url = new URL("http://assertj.org/news?article");
    String name = "article";
    String expectedValue = null;
    String actualValue = null;

    try {
      urls.assertHasNoParameter(info, url, name, expectedValue);
    } catch (AssertionError e) {
      verify(failures).failure(info, shouldHaveNoParameter(url, name, expectedValue, actualValue));
      return;
    }

    failBecauseExpectedAssertionErrorWasNotThrown();
  }

  @Test
  public void should_pass_if_parameter_without_value_is_present_with_value() throws MalformedURLException {
    urls.assertHasNoParameter(info, new URL("http://assertj.org/news=10"), "article", null);
  }

  @Test
  public void should_pass_if_parameter_with_value_is_missing() throws MalformedURLException {
    urls.assertHasNoParameter(info, new URL("http://assertj.org/news"), "article", "10");
  }

  @Test
  public void should_pass_if_parameter_with_value_is_present_without_value() throws MalformedURLException {
    urls.assertHasNoParameter(info, new URL("http://assertj.org/news?article"), "article", "10");
  }

  @Test
  public void should_pass_if_parameter_with_value_is_present_with_wrong_value() throws MalformedURLException {
    urls.assertHasNoParameter(info, new URL("http://assertj.org/news?article=11"), "article", "10");
  }

  @Test
  public void should_fail_if_parameter_with_value_is_present() throws MalformedURLException {
    URL url = new URL("http://assertj.org/news?article=10");
    String name = "article";
    String expectedValue = "10";
    String actualValue = "10";

    try {
      urls.assertHasNoParameter(info, url, name, expectedValue);
    } catch (AssertionError e) {
      verify(failures).failure(info, shouldHaveNoParameter(url, name, expectedValue, actualValue));
      return;
    }

    failBecauseExpectedAssertionErrorWasNotThrown();
  }
}
