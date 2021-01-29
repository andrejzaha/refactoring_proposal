package org.example;

import org.example.model.Pizza;
import org.example.model.Salad;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    Promotion types that can be received in the input string :
        promotion-none          => discount  0.0 %
        promotion-happy_hour    => discount 20.0 %
        promotion-special_offer => discount 10.0 %

    Products sizes (with corresponding prices) that can be received in the input string :
        small   => [ pizza =   8.0, a topping of any type = 1.5 ], salad = 4.0
        medium  => [ pizza =  10.0, a topping of any type = 2.5 ], salad = 5.0
        large   => [ pizza =  12.0, a topping of any type = 3.5 ], salad = 7.0

    The test should not be modified.
    The processOrders and printBill actions must exist (they should not be merged).

    The bill has the following format :
        for each product, separated by a space and ending with new line :
                            product-type quantity size price
                       ex.: pizza 1 large 19
        last line is the total in the following format :
                            total: total-price
                       ex.: total: 123
 */
public class RefactorMe {

    public static void main(String[] args) {
        RefactorMe refactorMe = new RefactorMe();

        List<String> orders = Arrays.asList(
                "pizza large 1 promotion-none topping:cheese,mushrooms",
                "salad medium 3 promotion-happy_hour",
                "salad small 1 promotion-special_offer"
        );

        List<Object> processOrders = refactorMe.processOrders(orders);

        String bill = refactorMe.printBill(processOrders);

        // print the bill using the list of objects
        // ex :
        //
        //pizza 1 large 19.0
        //salad 3 medium 12.0
        //salad 1 small 3.6
        //total: 34.6
        System.out.println(bill);
    }

    public String getBillFromOrder(List<String> orders) {
        List<Object> processOrders = processOrders(orders);

        return printBill(processOrders);
    }

    private List<Object> processOrders(List<String> orders) {
        List<Object> things = new ArrayList<>();

        for (int i = 0; i < orders.size(); i++) {
            String[] parts = orders.get(i).split(" ");

            if (parts[0].equals("pizza")) {
                Pizza p = new Pizza();
                p.setSize(parts[1]);
                p.setQuantity(Integer.parseInt(parts[2]));

                String[] toppings = parts[4].split(":")[1].split(",");

                if (parts[3].equals("promotion-none")) {
                    if (parts[1].equals("large")) {
                        // (large-price + nb-toppings * topping-price ) * nb items
                        p.setPrice((12.0 + toppings.length * 3.5) * Integer.parseInt(parts[2]));
                    } else if (parts[1].equals("medium")) {
                        p.setPrice((10.0 + toppings.length * 2.5) * Integer.parseInt(parts[2]));
                    } else if (parts[1].equals("small")) {
                        p.setPrice((8.0 + toppings.length * 1.5) * Integer.parseInt(parts[2]));
                    }
                } else if (parts[3].equals("promotion-happy_hour")) {
                    if (parts[1].equals("large")) {
                        p.setPrice((12.0 + toppings.length * 3.5) * 0.8 * Integer.parseInt(parts[2]));
                    } else if (parts[1].equals("medium")) {
                        p.setPrice((10.0 + toppings.length * 2.5) * 0.8 * Integer.parseInt(parts[2]));
                    } else if (parts[1].equals("small")) {
                        p.setPrice((8.0 + toppings.length * 1.5) * 0.8 * Integer.parseInt(parts[2]));
                    }
                } else if (parts[3].equals("promotion-special_offer")) {
                    if (parts[1].equals("large")) {
                        p.setPrice((12.0 + toppings.length * 3.5) * 0.9 * Integer.parseInt(parts[2]));
                    } else if (parts[1].equals("medium")) {
                        p.setPrice((10.0 + toppings.length * 2.5) * 0.9 * Integer.parseInt(parts[2]));
                    } else if (parts[1].equals("small")) {
                        p.setPrice((8.0 + toppings.length * 1.5) * 0.9 * Integer.parseInt(parts[2]));
                    }
                }

                things.add(p);
            } else if (parts[0].equals("salad")) {
                Salad s = new Salad();
                s.setSize(parts[1]);
                s.setQuantity(Integer.parseInt(parts[2]));

                if (parts[3].equals("promotion-none")) {
                    if (parts[1].equals("large")) {
                        s.setPrice(7.0 * Integer.parseInt(parts[2]));
                    } else if (parts[1].equals("medium")) {
                        s.setPrice(5.0 * Integer.parseInt(parts[2]));
                    } else if (parts[1].equals("small")) {
                        s.setPrice(4.0 * Integer.parseInt(parts[2]));
                    }
                } else if (parts[3].equals("promotion-happy_hour")) {
                    if (parts[1].equals("large")) {
                        s.setPrice(7.0 * 0.8 * Integer.parseInt(parts[2]));
                    } else if (parts[1].equals("medium")) {
                        s.setPrice(5.0 * 0.8 * Integer.parseInt(parts[2]));
                    } else if (parts[1].equals("small")) {
                        s.setPrice(4.0 * 0.8 * Integer.parseInt(parts[2]));
                    }

                } else if (parts[3].equals("promotion-special_offer")) {
                    if (parts[1].equals("large")) {
                        s.setPrice(7.0 * 0.9 * Integer.parseInt(parts[2]));
                    } else if (parts[1].equals("medium")) {
                        s.setPrice(5.0 * 0.9 * Integer.parseInt(parts[2]));
                    } else if (parts[1].equals("small")) {
                        s.setPrice(4.0 * 0.9 * Integer.parseInt(parts[2]));
                    }
                }

                things.add(s);
            }

        }

        return things;
    }


    private String printBill(List<Object> processOrders) {
        String bill = "";

        double s = 0;
        for (int i = 0; i < processOrders.size(); i++) {
            if (processOrders.get(i) instanceof Pizza) {
                Pizza p = (Pizza) processOrders.get(i);
                bill += "pizza " + p.getQuantity() + " " + p.getSize() + " " + p.getPrice() + "\n";
                s += p.getPrice();
            } else if (processOrders.get(i) instanceof Salad) {
                Salad p = (Salad) processOrders.get(i);
                bill += "salad " + p.getQuantity() + " " + p.getSize() + " " + p.getPrice() + "\n";
                s += p.getPrice();
            }
        }
        bill += "total: " + s;
        return bill;
    }
}
