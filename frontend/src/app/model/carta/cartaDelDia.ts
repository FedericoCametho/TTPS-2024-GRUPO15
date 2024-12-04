import { Menu } from './producto/menu';
import { DiaSemana } from './dia-semana.enum';

export class CartaDelDia {
    id: number;
    menus: Array<Menu> | undefined;
    diaSemana: DiaSemana;
    activa: boolean;

  constructor(
    id: number,
    menus: Array<Menu>,
    diaSemana: DiaSemana,
    activa: boolean,
  ) {
    this.id = id;
    this.diaSemana = diaSemana;
    this.activa = activa;
    this.menus = menus;
  }
}