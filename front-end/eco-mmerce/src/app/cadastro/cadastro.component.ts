import { Component, OnInit } from '@angular/core';
import {Usuario} from '../usuario';
import {UsuarioService} from '../usuario.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.css']
})
export class CadastroComponent implements OnInit {

  usuario: Usuario = new Usuario();
  submitted = false;

  constructor(private usuarioService: UsuarioService,
              private router: Router) { }

  ngOnInit() {
  }

  newUsuario(): void {
    this.submitted = false;
    this.usuario = new Usuario();
  }

  save() {
    this.usuarioService.createUsuario(this.usuario)
      .subscribe(
        data => console.log(data),
        error => console.log(error)
      );
    this.usuario = new Usuario();
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }

}
