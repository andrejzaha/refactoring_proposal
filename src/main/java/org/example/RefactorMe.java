package org.example;

import org.example.model.Coffee;
import org.example.model.Pizza;
import org.example.model.Salad;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RefactorMe {

    public static void main(String[] args) {
        RefactorMe refactorMe = new RefactorMe();

        List<String> orders = Arrays.asList(
                "pizza large 1 promotion-none topping:cheese,mushrooms",
                "salad medium 3 promotion-happy_hour",
                "coffee small 1 promotion-special_offer"
        );

        List<Object> processOrders = refactorMe.processOrders(orders);

        String bill = refactorMe.printBill(processOrders);

        // print the bill using the list of objects ???
        // ex :
        //
        // pizza    1   large       10
        // salad    3   medium      12
        // coffee   1   small       2.7
        // total                    24.7
        System.out.println(bill);
    }

    public List<Object> processOrders(List<String> orders) {
        List<Object> things = new ArrayList<>();

        for (int i = 0; i < orders.size(); i++) {
            String[] parts = orders.get(i).split(" ");

            if (parts[0].equals("pizza")) {
                Pizza p = new Pizza();
                p.setSize(parts[1]);
                p.setQuantity(Integer.parseInt(parts[2]));

                String[] toppings = parts[4].split(":")[1].split(",");
                p.setTopping(Arrays.toString(toppings));

                if (parts[3].equals("promotion-none")) {
                    if (parts[1].equals("large")) {
                        // (large-price + nb-toppings * topping-price ) * nb items
                        p.setPrice((12.0 + toppings.length * 3.8) * Integer.parseInt(parts[2]));
                    } else if (parts[1].equals("medium")) {
                        p.setPrice((10.0 + toppings.length * 3.6) * Integer.parseInt(parts[2]));
                    } else if (parts[1].equals("small")) {
                        p.setPrice((8.0 + toppings.length * 3.3) * Integer.parseInt(parts[2]));
                    }
                } else if (parts[3].equals("promotion-happy_hour")) {
                    if (parts[1].equals("large")) {
                        p.setPrice((12.0 + toppings.length * 2.8) * 0.8 * Integer.parseInt(parts[2]));
                    } else if (parts[1].equals("medium")) {
                        p.setPrice((10.0 + toppings.length * 2.6) * 0.8 * Integer.parseInt(parts[2]));
                    } else if (parts[1].equals("small")) {
                        p.setPrice((8.0 + toppings.length * 2.3) * 0.8 * Integer.parseInt(parts[2]));
                    }
                } else if (parts[3].equals("promotion-special_offer")) {
                    if (parts[1].equals("large")) {
                        p.setPrice((12.0 + toppings.length * 1.8) * 0.9 * Integer.parseInt(parts[2]));
                    } else if (parts[1].equals("medium")) {
                        p.setPrice((10.0 + toppings.length * 1.6) * 0.9 * Integer.parseInt(parts[2]));
                    } else if (parts[1].equals("small")) {
                        p.setPrice((8.0 + toppings.length * 1.3) * 0.9 * Integer.parseInt(parts[2]));
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
            } else if (parts[0].equals("coffee")) {
                Coffee c = new Coffee();
                c.setSize(parts[1]);
                c.setQuantity(Integer.parseInt(parts[2]));

                if (parts[3].equals("promotion-none")) {
                    if (parts[1].equals("large")) {
                        c.setPrice(4.5 * Integer.parseInt(parts[2]));
                    } else if (parts[1].equals("medium")) {
                        c.setPrice(3.0 * Integer.parseInt(parts[2]));
                    } else if (parts[1].equals("small")) {
                        c.setPrice(2.0 * Integer.parseInt(parts[2]));
                    }
                } else if (parts[3].equals("promotion-happy_hour")) {
                    if (parts[1].equals("large")) {
                        c.setPrice(4.5 * 0.8 * Integer.parseInt(parts[2]));
                    } else if (parts[1].equals("medium")) {
                        c.setPrice(3.0 * 0.8 * Integer.parseInt(parts[2]));
                    } else if (parts[1].equals("small")) {
                        c.setPrice(2.0 * 0.8 * Integer.parseInt(parts[2]));
                    }
                } else if (parts[3].equals("promotion-special_offer")) {
                    if (parts[1].equals("large")) {
                        c.setPrice(4.5 * 0.9 * Integer.parseInt(parts[2]));
                    } else if (parts[1].equals("medium")) {
                        c.setPrice(3.0 * 0.9 * Integer.parseInt(parts[2]));
                    } else if (parts[1].equals("small")) {
                        c.setPrice(2.0 * 0.9 * Integer.parseInt(parts[2]));
                    }
                }

                things.add(c);
            }
        }

        return things;
    }


    private String printBill(List<Object> processOrders) {
        String bill = "";

        for (int i = 0; i < processOrders.size(); i++) {
            if (processOrders.get(i) instanceof Pizza) {
                Pizza p = (Pizza) processOrders.get(i);
                bill += "pizza     " + p.getQuantity() + "     " + p.getSize() + "     " + p.getTopping() + "     " + p.getPrice();
            }
        }
        return bill;
    }
}