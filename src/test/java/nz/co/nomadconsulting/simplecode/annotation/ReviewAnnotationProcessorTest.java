/*
* Copyright 2014 Nomad Consulting Limited
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package nz.co.nomadconsulting.simplecode.annotation;

import static com.google.testing.compile.JavaSourceSubjectFactory.javaSource;
import static org.truth0.Truth.ASSERT;

import com.google.testing.compile.JavaFileObjects;
import org.junit.Test;


public class ReviewAnnotationProcessorTest {

    @Test
    public void testOtherAnnotationsPasses() throws Exception {
        ASSERT.about(javaSource())
        .that(JavaFileObjects.forResource("nz/co/nomadconsulting/simplecode/annotation/sample/OtherAnnotationsClassCompiles.java"))
        .processedWith(new ReviewAnnotationProcessor())
        .compilesWithoutError();
    }
    
    @Test
    public void testNoAnnotationsPasses() throws Exception {
        ASSERT.about(javaSource())
        .that(JavaFileObjects.forResource("nz/co/nomadconsulting/simplecode/annotation/sample/NoAnnotationsClassCompiles.java"))
        .processedWith(new ReviewAnnotationProcessor())
        .compilesWithoutError();
    }
    
    @Test
    public void testAwesomeDateFailure() throws Exception {
        ASSERT.about(javaSource())
        .that(JavaFileObjects.forResource("nz/co/nomadconsulting/simplecode/annotation/sample/AwesomeClassFailsCompilation.java"))
        .processedWith(new ReviewAnnotationProcessor())
        .failsToCompile()
        .withErrorContaining("Awesome code past expiry 01-01-2001 please review!");
    }
    

    @Test
    public void testAwesomeFutureDatePasses() throws Exception {
        ASSERT.about(javaSource())
        .that(JavaFileObjects.forResource("nz/co/nomadconsulting/simplecode/annotation/sample/AwesomeClassFutureDateCompiles.java"))
        .processedWith(new ReviewAnnotationProcessor())
        .compilesWithoutError();
    }
    
    @Test
    public void testTempoaryDateFailure() throws Exception {
        ASSERT.about(javaSource())
        .that(JavaFileObjects.forResource("nz/co/nomadconsulting/simplecode/annotation/sample/TemporaryClassFailsCompilation.java"))
        .processedWith(new ReviewAnnotationProcessor())
        .failsToCompile()
        .withErrorContaining("Temporary code past expiry 01-01-2001 please review!");
    }
    
    @Test
    public void testTempoaryFutureDatePasses() throws Exception {
        ASSERT.about(javaSource())
        .that(JavaFileObjects.forResource("nz/co/nomadconsulting/simplecode/annotation/sample/TemporaryClassFutureDateCompiles.java"))
        .processedWith(new ReviewAnnotationProcessor())
        .compilesWithoutError();
    }
    
    @Test
    public void testDeprecatedReviewDateFailure() throws Exception {
        ASSERT.about(javaSource())
        .that(JavaFileObjects.forResource("nz/co/nomadconsulting/simplecode/annotation/sample/DeprecatedReviewClassFailsCompilation.java"))
        .processedWith(new ReviewAnnotationProcessor())
        .failsToCompile()
        .withErrorContaining("DeprecatedReview code past expiry 01-01-2001 please review!");
    }
    
    @Test
    public void testDeprecatedReviewFutureDatePasses() throws Exception {
        ASSERT.about(javaSource())
        .that(JavaFileObjects.forResource("nz/co/nomadconsulting/simplecode/annotation/sample/DeprecatedReviewClassFutureDateCompiles.java"))
        .processedWith(new ReviewAnnotationProcessor())
        .compilesWithoutError();
    }
}
