package org.generation.brazil.ecommerce.pedido;

import com.github.javafaker.Faker;
import org.generation.brazil.ecommerce.produto.Produto;
import org.generation.brazil.ecommerce.produto.ProdutoMock;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class PedidoMock {
    public static Pedido getPedidoMock(){
        Faker faker = new Faker();
        Pedido pedido = new Pedido();
        pedido.setPrecoTotal(BigDecimal.valueOf(faker.number().numberBetween(1, 300)));
        pedido.setFrete(BigDecimal.valueOf(faker.number().numberBetween(1, 300)));

        Set<Produto> produtos = new HashSet<>();
        produtos.add(ProdutoMock.getProdutoMock());
        pedido.setIdProduto(produtos);
        pedido.setNumeroPedido(faker.numerify("######"));
        return pedido;
    }
}
