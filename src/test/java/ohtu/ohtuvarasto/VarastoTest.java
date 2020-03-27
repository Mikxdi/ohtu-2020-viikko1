package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void varastoonEiVoiLisataYliTilavuuden() {
        varasto.lisaaVarastoon(12);

        double lisattysaldo = varasto.getSaldo();

        assertEquals(10, lisattysaldo, vertailuTarkkuus);
    }
    @Test
    public void ottaminenYliSaldon() {
        varasto.lisaaVarastoon(8);

        double otettu = varasto.otaVarastosta(9);
        assertEquals(8, otettu, vertailuTarkkuus);
    }
    @Test
    public void negatiivistaEiVahenneta() {
        varasto.lisaaVarastoon(5);
        varasto.lisaaVarastoon(-6);

        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }
    @Test
    public void alkusaldoNegatiivinen() {
        Varasto varasto2 = new Varasto(10, -1);
        assertEquals(0, varasto2.getSaldo(), vertailuTarkkuus);
    }
    @Test
    public void kayttokelvotonNolla() {
        Varasto varasto2 = new Varasto(-10);
        assertEquals(0, varasto2.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void kayttokelvotonNolla2() {
        Varasto varasto2 = new Varasto(-10, 0);
        assertEquals(0, varasto2.getTilavuus(), vertailuTarkkuus);
    }
    @Test
    public void toStringTesti() {
        Varasto varasto2 = new Varasto(15, 5);
        assertEquals("saldo = 5.0, vielä tilaa 10.0", varasto2.toString());
    }
    @Test
    public void eiOtetaNegatiivista() {
        varasto.lisaaVarastoon(8);
        varasto.otaVarastosta(-5);


        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }
}