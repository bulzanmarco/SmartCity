package com.example.myapplication.Domain;

import static android.graphics.Insets.add;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Streets {
    BulevardulMihaiViteazu,
    StradaFeldioara,
    StradaCluj,
    BulevardulLiviuRebreanu,
    //B
    BulevardulMirceaEliade,
    BulevardulPrimaverii,
    StradaRacari,
    CaleaDorobantilor,
    BulevardulCamilRessu;
    public static final Map<CityName, List<Streets>> CITY_STREETS = new HashMap<CityName, List<Streets>>() {
        {
            put(CityName.Timisoara, new ArrayList<Streets>() {{
                add(Streets.BulevardulMihaiViteazu);
                add(Streets.StradaFeldioara);
                add(Streets.StradaCluj);
                add(Streets.BulevardulLiviuRebreanu);
            }});
            put(CityName.Bucharest, new ArrayList<Streets>() {{
                add(Streets.BulevardulMirceaEliade);
                add(Streets.BulevardulPrimaverii);
                add(Streets.StradaRacari);
                add(Streets.CaleaDorobantilor);
                add(Streets.BulevardulCamilRessu);
            }});
        }
    };
}
