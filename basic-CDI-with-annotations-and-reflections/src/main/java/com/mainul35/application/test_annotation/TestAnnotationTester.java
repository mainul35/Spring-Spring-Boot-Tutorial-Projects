package com.mainul35.application.test_annotation;

@TestAnnotation(purpose = "marker over TestAnnotationTester type")
public class TestAnnotationTester {

    @TestAnnotation(purpose = "marker over TestAnnotationTester field")
    TestAnnotationTester testAnnotationTester;

    public TestAnnotationTester () {
        testAnnotationTester = this;
    }

    @TestAnnotation(purpose = "marker over method")
    public void print() {
        System.out.println("Printing from TestAnnotationTester");
    }
}
