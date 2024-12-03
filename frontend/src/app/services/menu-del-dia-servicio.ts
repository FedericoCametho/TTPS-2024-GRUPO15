import { PlatoDelDia } from "../models/plato-del-dia/plato-del-dia";

export class MenuDelDiaServicio {
    static platosDelDia: PlatoDelDia[] = [
        new PlatoDelDia("Hamburguesa", "Hamburguesa de carne de res con queso y lechuga", 10, "https://cdn.pixabay.com/photo/2016/03/05/19/02/hamburger-1238246_960_720.jpg", "Lunes"),
        new PlatoDelDia("Pizza", "Pizza de peperoni con queso", 12, "https://cdn.pixabay.com/photo/2017/12/09/08/18/pizza-3007395_960_720.jpg", "Martes"),
        new PlatoDelDia("Tacos", "Tacos de carne de res con salsa y limon", 8, "https://cdn.pixabay.com/photo/2017/12/09/08/18/pizza-3007395_960_720.jpg", "Miércoles"),
        new PlatoDelDia("Sushi", "Sushi de salmon con arroz y aguacate", 15, "https://cdn.pixabay.com/photo/2017/12/09/08/18/pizza-3007395_960_720.jpg", "Jueves"),
        new PlatoDelDia("Ensalada", "Ensalada de lechuga con aderezo de mostaza", 5, "https://cdn.pixabay.com/photo/2017/12/09/08/18/pizza-3007395_960_720.jpg", "Viernes")
    ]

    public getPlatos(): PlatoDelDia[] {
        return MenuDelDiaServicio.platosDelDia;
    }
}

