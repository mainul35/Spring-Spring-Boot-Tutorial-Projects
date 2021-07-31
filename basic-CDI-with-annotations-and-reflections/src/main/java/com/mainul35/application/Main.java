package com.mainul35.application;

import com.mainul35.application.test_annotation.TestAnnotation;
import com.mainul35.application.test_annotation.TestAnnotationTester;
import com.mainul35.cdi.ContextHolder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        callingFromCDIContext();

//        annotationAndReflectionUnderstanding();

    }

    private static void annotationAndReflectionUnderstanding() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException {
//        TestAnnotationTester testAnnotationTester = TestAnnotationTester.class.getConstructor(new Class[]{}).newInstance();
        TestAnnotationTester testAnnotationTester = (TestAnnotationTester) Class.forName("com.mainul35.application.test_annotation.TestAnnotationTester")
                .getConstructor(new Class[]{}).newInstance();

        TestAnnotation testAnnotation = testAnnotationTester.getClass().getAnnotation(TestAnnotation.class);
        System.out.println(testAnnotation.getClass().getName() + " @Line 17");
        System.out.println(testAnnotation.purpose() + " @Line 18");

        System.out.println("===========================================================");

        for (Field declaredField : testAnnotationTester.getClass().getDeclaredFields()) {
            if (declaredField.getAnnotation(TestAnnotation.class) != null) {
                System.out.println(declaredField.getAnnotation(TestAnnotation.class).purpose() + " @Line 22");
                System.out.println(declaredField.getType() + " @Line 24");
                System.out.println(declaredField.getClass() + " @Line 25");
            }
        }

        System.out.println("===========================================================");
        for (Method method : testAnnotationTester.getClass().getDeclaredMethods()) {
            if (method.getAnnotation(TestAnnotation.class) != null) {
                System.out.println(method.getAnnotation(TestAnnotation.class).purpose() + " @Line 31");
                System.out.println(method.getReturnType() + " @Line 32");
                System.out.println(method.getClass() + " @Line 33");
            }
        }
    }

    private static void callingFromCDIContext() {
        AppServiceCaller caller1 = (AppServiceCaller) ContextHolder.getContext().getBean("com.mainul35.application.AppServiceCaller");
        AppServiceCaller caller2 = ContextHolder.getContext().getBean(AppServiceCaller.class);

        System.out.println(ContextHolder.getContext());
        System.out.println(ContextHolder.getContext());

        caller1.sayHello1();
        caller2.sayHello2();
    }
}
