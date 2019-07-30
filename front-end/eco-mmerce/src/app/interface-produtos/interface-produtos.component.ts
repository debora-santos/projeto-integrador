import { Component, OnInit } from '@angular/core';
import {Produto} from '../produto';
import {Observable} from 'rxjs';
import {TokenStorageService} from '../auth/token-storage.service';
import {ProdutoService} from '../produto.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-interface-produtos',
  templateUrl: './interface-produtos.component.html',
  styleUrls: ['./interface-produtos.component.css']
})
export class InterfaceProdutosComponent implements OnInit {


  produtos: Observable<Produto[]>;

  constructor(private token: TokenStorageService,
              private produtoService: ProdutoService,
              private router: Router) { }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.produtos = this.produtoService.getProdutos();
    console.log(this.produtos);
  }

  adicionarProduto() {
    this.router.navigate(['adicionar-pessoa']);
  }

  editProduto(id: number) {
    this.router.navigate(['editar-pessoa', id]);
  }

  visualizarProduto(id: number) {
    this.router.navigate(['visualizar-pessoa', id]);
  }

  deleteProduto(id: number) {
    this.produtoService.deleteProduto(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }

  goToLogin() {
    this.router.navigate(['login']);
  }
}
