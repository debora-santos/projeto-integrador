import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs';
import {Produto} from '../produto';
import {TokenStorageService} from '../auth/token-storage.service';
import {ProdutoService} from '../produto.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-produtos',
  templateUrl: './produtos.component.html',
  styleUrls: ['./produtos.component.css']
})
export class ProdutosComponent implements OnInit {

  produtos: Observable<Produto[]>;
  produto: Produto = new Produto();

  constructor(private token: TokenStorageService, private produtoService: ProdutoService, private router: Router ) { }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.produtos = this.produtoService.getProdutos();
    console.log(this.produtos);
  }

  editProduto(id: number) {
  }

  visualizarProduto(id: number) {
    this.router.navigate(['produto-detalhado', id]);
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

  onSubmit() {
    this.produtoService.createProduto(this.produto)
      .subscribe(data => console.log(data), error => console.log(error));
    this.produto = new Produto();
  }
}
