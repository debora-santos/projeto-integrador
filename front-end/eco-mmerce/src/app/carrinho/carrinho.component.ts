import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from '../auth/token-storage.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Produto} from '../produto';
import {ProdutoService} from '../produto.service';

@Component({
  selector: 'app-carrinho',
  templateUrl: './carrinho.component.html',
  styleUrls: ['./carrinho.component.css']
})
export class CarrinhoComponent implements OnInit {

  id: number;
  produto: Produto;

  constructor(private token: TokenStorageService,
              private route: ActivatedRoute,
              private router: Router,
              private produtoService: ProdutoService) { }

  ngOnInit() {
    this.produto = new Produto();

    this.id = this.route.snapshot.params.id;

    this.produtoService.getProduto(this.id)
      .subscribe(data => {
        console.log(data);
        this.produto = data;
      }, error => console.log(error));
  }
}
