import { DiaSemana } from "../dia-semana.enum";

export class CartaDelDiaRequest {
    menues: number[] | undefined;
    diaSemana: DiaSemana | undefined;
    isActiva: boolean | undefined;
}