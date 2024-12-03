import { Component } from '@angular/core';
import { ComidaService } from '../services/comida.service';
import { Comida } from '../model/producto/comida';

@Component({
  selector: 'app-comida',
  imports: [],
  templateUrl: './comida.component.html',
  styleUrl: './comida.component.css'
})
export class ComidaComponent {

  private comidasList: Comida[] = [];

  constructor(
    private ComidaService : ComidaService
  ){}

  ngOnInit():void{
    this.ComidaService.getComidas().subscribe((resp) => {
        this.comidasList = resp;
        console.log(this.comidasList);
    })
  }

}
