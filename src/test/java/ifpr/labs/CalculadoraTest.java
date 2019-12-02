package ifpr.labs;

import ifpr.models.Calculadora;
import org.junit.Assert;
import org.junit.Test;

public class CalculadoraTest {

    @Test
    public void deveSomarDoisNumeros(){

        //cenario
        int a = 5;
        int b = 3;
        Calculadora calc = new Calculadora();

        //acao
        int resultado = calc.soma(a, b);

        //verificacao
        Assert.assertEquals(resultado, 8);
    }

    @Test(expected = ArithmeticException.class)
    public void NaoDeveDividirPorZero(){

        //cenario
        int a = 5;
        int b = 0;
        Calculadora calc = new Calculadora();

        //acao
        int resultado = calc.dividir(a, b);

        //verificacao
        Assert.assertEquals(resultado, 5);

    }

}
