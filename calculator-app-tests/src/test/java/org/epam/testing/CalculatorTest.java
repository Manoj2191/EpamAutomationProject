package org.epam.testing;

import com.epam.tat.module4.Calculator;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CalculatorTest {
    private final String SMOKE = "smoke";
    private Calculator calculator;


    @BeforeClass(alwaysRun = true)
    public void setUp() {
        calculator = new Calculator();
    }

    //Positive cases

    //Test with data provider
    @Test(description = "Addition test", dataProvider = "addTestDataProvider", groups = {SMOKE})
    public void test1(long a, long b, long result) {
        Assertions.assertThat(calculator.sum(a, b))
                .isEqualTo(result);
    }

    @Test(description = "Subtraction test", dependsOnGroups = {SMOKE})
    public void test2() {
        Assertions.assertThat(calculator.sub(10L, 5L))
                .isEqualTo(5L);
    }

    //Bug
    @Test(description = "Multiplication test")
    public void test3() {
        Assertions.assertThat(calculator.mult(3.3, 3.3))
                .isEqualTo(10.89);
    }

    //Bug
    @Test(description = "Cos test")
    public void test4() {
        Assertions.assertThat(calculator.cos(10))
                .isEqualTo(0.9848);
    }

    @Test(description = "Division test 1")
    public void test5() {
        Assertions.assertThat(calculator.div(10L, 2L))
                .isEqualTo(5L);
    }

    //Negative case
    @Test(description = "Expected exception test 1", expectedExceptions = NumberFormatException.class)
    public void test6() {
        calculator.div(10L, 0L);
    }

    //Bug
    @Test(description = "Expected exception test 2")
    public void test7() {
        Assertions.assertThatThrownBy(() -> calculator.div(3L, 0L))
                .hasCauseExactlyInstanceOf(ArithmeticException.class);
    }

    //Bug
    @Test(description = "Square root of negative numbers test")
    public void test8() {
        Assertions.assertThat(Double.isNaN(calculator.sqrt(-25L))).isTrue();
    }

    @Test(description = "Subtraction of 2 negative numbers test")
    public void test9() {
        Assertions.assertThat(calculator.sub(-20L, -20L)).isEqualTo(0L);
    }

    @Test(description = "Power of 0 test")
    public void test10() {
        Assertions.assertThat(calculator.pow(0, 2)).isEqualTo(0); // Bug due to Math.floor
    }

    @DataProvider(name = "addTestDataProvider")
    public Object[][] addTestDataProvider() {
        return new Object[][]{
                {-1, 1, 0},
                {-2, 1, -1},
                {10, 10, 20},
                {230, 100, 330}
        };
    }
}
