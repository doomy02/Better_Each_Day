package app.model;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polynomial {
    private TreeMap<Integer, Double> polynomial = new TreeMap<>();
    public TreeMap<Integer, Double> getPolynomial() {
        return polynomial;
    }

    public void setPolynomial(TreeMap<Integer, Double> polynomial) {
        this.polynomial = polynomial;
    }

    public String printPolynomial() {
        boolean ok = true;
        StringBuilder res = new StringBuilder();

        for(Map.Entry<Integer, Double> entry : this.getPolynomial().descendingMap().entrySet()) {
            if(ok) {
                if(entry.getKey() == 0) {
                    if(entry.getValue() != 0)
                        res.append(entry.getValue());
                    else
                        res.append("0");
                }
                else if(entry.getKey() == 1){
                    if(entry.getValue() == 1)
                        res.append("x");
                    else if(entry.getValue() == -1)
                        res.append("-x");
                    else if(entry.getValue() == 0)
                        res.append("0");
                    else
                        res.append(entry.getValue()).append("x");
                }
                else{
                    if(entry.getValue() == 1)
                        res.append("x^").append(entry.getKey());
                    else if(entry.getValue() == -1)
                        res.append("-x^").append(entry.getKey());
                    else if(entry.getValue() == 0)
                        res.append("0");
                    else
                        res.append(entry.getValue()).append("x^").append(entry.getKey());
                }

                ok = false;
            }
            else{
                if(entry.getKey() == 0) {
                    if(entry.getValue() > 0)
                        res.append("+").append(entry.getValue());
                    else if(entry.getValue() < 0)
                        res.append(entry.getValue());
                }
                else if(entry.getKey() == 1) {
                    if(entry.getValue() > 0 && entry.getValue() != 1)
                        res.append("+").append(entry.getValue()).append("x");
                    else if(entry.getValue() == 1)
                        res.append("+x");
                    else if(entry.getValue() == -1)
                        res.append("-x");
                    else if(entry.getValue() < 0 && entry.getValue() != -1)
                        res.append(entry.getValue()).append("x");
                }
                else {
                    if(entry.getValue() > 0 && entry.getValue() != 1)
                        res.append("+").append(entry.getValue()).append("x^").append(entry.getKey());
                    else if(entry.getValue() == 1)
                        res.append("+x^").append(entry.getKey());
                    else if(entry.getValue() == -1)
                        res.append("-x^").append(entry.getKey());
                    else if(entry.getValue() < 0 && entry.getValue() != -1)
                        res.append(entry.getValue()).append("x^").append(entry.getKey());
                }
            }
        }

        return res.toString();
    }

    public void stringToPolynomial(String syntax) {
        final String regex = "([-+])?(\\d+)?x(\\^(\\d+))?|([-+])?(\\d+)";

        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(syntax);

        for(int i = 0; i < matcher.groupCount(); i++) {
            if(matcher.find()) {
                int power = 0;
                double coefficient = 0.0;

                if(matcher.group(6) != null) {
                    if(matcher.group(5) == null || Objects.equals(matcher.group(5), "+"))
                        coefficient = Double.parseDouble(matcher.group(6));
                    else if(Objects.equals(matcher.group(5), "-"))
                        coefficient = -Double.parseDouble(matcher.group(6));

                }
                else
                {
                    if(matcher.group(1) == null || Objects.equals(matcher.group(1), "+")) {
                        if(matcher.group(2) != null)
                            coefficient = Double.parseDouble(matcher.group(2));
                        else
                            coefficient = 1.0;
                    }
                    else if(Objects.equals(matcher.group(1), "-")) {
                        if(matcher.group(2) != null)
                            coefficient = -Double.parseDouble(matcher.group(2));
                        else
                            coefficient = -1.0;
                    }

                    if(matcher.group(4) == null) {
                        power = 1;
                    }
                    else{
                        power = Integer.parseInt(matcher.group(4));
                    }
                }

                this.getPolynomial().put(power, coefficient);
            }
        }
    }
}