package app.model;

import java.util.Map;
import static java.lang.Math.round;

public class Operations {
    public static Polynomial add(Polynomial x, Polynomial y) {
        Polynomial res = new Polynomial();
        res.getPolynomial().putAll(x.getPolynomial());

        for(Map.Entry<Integer, Double> entry : y.getPolynomial().descendingMap().entrySet()) {
            if(!res.getPolynomial().containsKey(entry.getKey())) {
                res.getPolynomial().put(entry.getKey(), entry.getValue());
            }
            else{
                Double entry2 = res.getPolynomial().get(entry.getKey());
                res.getPolynomial().put(entry.getKey(), entry.getValue() + entry2);
            }
        }

        return res;
    }

    public static Polynomial sub(Polynomial x, Polynomial y) {
        Polynomial aux = new Polynomial();
        aux.getPolynomial().putAll(y.getPolynomial());

        for(Map.Entry<Integer, Double> entry2 : aux.getPolynomial().descendingMap().entrySet()) {
            entry2.setValue(-entry2.getValue());
        }

        Polynomial res;
        res = Operations.add(x, aux);

        return res;
    }

    public static Polynomial mul(Polynomial x, Polynomial y) {
        Polynomial res = new Polynomial();
        Polynomial aux = new Polynomial();

        for(Map.Entry<Integer, Double> entry : x.getPolynomial().descendingMap().entrySet()) {
            for(Map.Entry<Integer, Double> entry2 : y.getPolynomial().descendingMap().entrySet()) {
                aux.getPolynomial().put(entry.getKey() + entry2.getKey(), entry.getValue() * entry2.getValue());
            }
            res = Operations.add(aux, res);
            aux.getPolynomial().clear();
        }

        return res;
    }

    public static Polynomial divide(Polynomial x, Polynomial y) {
        Polynomial res = new Polynomial();
        Polynomial[] result = new Polynomial[2];
        Polynomial aux = new Polynomial();

        Polynomial auxY = new Polynomial();
        for(Map.Entry<Integer, Double> entry : y.getPolynomial().descendingMap().entrySet()) {
            auxY.getPolynomial().put(entry.getKey(), entry.getValue());
            break;
        }

        for(Map.Entry<Integer, Double> entry : x.getPolynomial().descendingMap().entrySet()) {
            for(Map.Entry<Integer, Double> entry2 : auxY.getPolynomial().entrySet()) {
                if(entry.getKey() >= entry2.getKey()) {
                    aux.getPolynomial().put(entry.getKey() - entry2.getKey(), round((entry.getValue() / entry2.getValue()) * 100) / 100.0);
                    res.getPolynomial().put(entry.getKey() - entry2.getKey(), round((entry.getValue() / entry2.getValue()) * 100) / 100.0);

                    Polynomial a;
                    a = Operations.mul(aux, y);
                    x = Operations.sub(x, a);

                    aux.getPolynomial().clear();
                    a.getPolynomial().clear();
                }
            }
        }

        result[0] = res;
        result[1] = x;

        return res;
    }
}
